package com.gabriel.gamestore.api.assembler;

import com.gabriel.gamestore.api.model.JogoModel;
import com.gabriel.gamestore.api.model.request.JogoRequest;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.model.Jogo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JogoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public JogoModel toModel(Jogo jogo) {
        return modelMapper.map(jogo, JogoModel.class);
    }

    public Jogo toEntity(JogoRequest request) {
        return modelMapper.map(request, Jogo.class);
    }

    public List<JogoModel> toCollectionModel(List<Jogo> jogos) {
        return jogos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}