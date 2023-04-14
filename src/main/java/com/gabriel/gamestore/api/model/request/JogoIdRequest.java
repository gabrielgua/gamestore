package com.gabriel.gamestore.api.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JogoIdRequest {

    @NotNull
    private Long id;
}
