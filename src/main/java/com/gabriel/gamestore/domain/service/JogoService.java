package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.EntidadeEmUsoException;
import com.gabriel.gamestore.domain.exception.JogoNaoEncontradoException;
import com.gabriel.gamestore.domain.exception.NegocioException;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.model.Plataforma;
import com.gabriel.gamestore.domain.repository.JogoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JogoService {

    private JogoRepository repository;

    private DesenvolvedoraService desenvolvedoraService;

    private PlataformaService plataformaService;
    private CategoriaService categoriaService;


    @Transactional(readOnly = true)
    public List<Jogo> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Jogo buscarPorId(Long jogoId) {
        return repository.findById(jogoId).orElseThrow(() -> new JogoNaoEncontradoException(jogoId));
    }
    @Transactional(readOnly = true)
    public Jogo buscarPorUriNome(String uriNome) {
        return repository.findByUriNome(uriNome).orElseThrow(() -> new JogoNaoEncontradoException(uriNome));
    }
    @Transactional(readOnly = true)
    public List<Jogo> buscarVariosPorId(List<Long> jogosIds) {
        return jogosIds.stream()
                .map(this::buscarPorId)
                .collect(Collectors.toList());
    }
    @Transactional
    public Jogo salvar(Jogo jogo, Set<Long> plataformaIds, Set<Long> categoriaIds) {

        var desenvolvedora = desenvolvedoraService.buscarPorId(jogo.getDesenvolvedora().getId());
        var uriNome = transformarNomeToUriNome(jogo.getNome());
        verificarUriNomeJaCadastrado(uriNome, jogo.getId());

        adicionarPlataformas(jogo, plataformaIds);
        adicionarCategorias(jogo, categoriaIds);

        jogo.setUriNome(uriNome);
        jogo.setDesenvolvedora(desenvolvedora);

        return repository.save(jogo);
    }

    @Transactional
    public void remover(Long jogoId) {
        try {
            var jogo = buscarPorId(jogoId);
            repository.delete(jogo);
            repository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format("Jogo de id: #%s está em uso. Remova-o da conta dos usuários e tente novamente.", jogoId));
        }
    }

    @Transactional
    public void adicionarPlataformas(Jogo jogo, Set<Long> plataformaIds) {
        jogo.setPlataformas(new HashSet<>());
        var plataformas = plataformaService.buscarVariosPorId(plataformaIds);

        plataformas.forEach(jogo::addPlataforma);
    }

    @Transactional
    public void adicionarCategorias(Jogo jogo, Set<Long> categoriaIds) {
        jogo.setCategorias(new HashSet<>());
        var categorias = categoriaService.buscarVariosPorIds(categoriaIds);

        categorias.forEach(jogo::addCategoria);
    }

    private String transformarNomeToUriNome(String nome) {
        String stringTransformada = nome.toLowerCase();
        stringTransformada = stringTransformada.replaceAll("[^a-z A-Z0-9]", "");
        stringTransformada = stringTransformada.replaceAll(" +", " ");
        stringTransformada = stringTransformada.replaceAll(" ", "-");
        return stringTransformada;
    }

    private void verificarUriNomeJaCadastrado(String uriNome, Long jogoId) {
        var jogo = repository.findByUriNome(uriNome);
        if (jogo.isPresent() && !Objects.equals(jogo.get().getId(), jogoId)) {
            throw new NegocioException(String.format("Jogo '%s' já está cadastrado.", jogo.get().getNome()));
        }
    }
}
