package com.server.pokiwar.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CardUserDto {

    private Long id;

    private Long idUser;

    private Long idCard;

    private int lever;

    private int count;

    private int value;

    private LocalDateTime createAt;

    private String name;

    private String thumbnail;
    private Long conditionUse;

    private List<ImageDto> imageCard;

    public CardUserDto(Long id, Long idCard, Long idUser, Integer lever, Integer count, int value, Long conditionUse) {
        this.id = id;
        this.idCard = idCard;
        this.idUser = idUser;
        this.lever = lever;
        this.count = count;
        this.value = value;
        this.conditionUse = conditionUse;
    }
}


