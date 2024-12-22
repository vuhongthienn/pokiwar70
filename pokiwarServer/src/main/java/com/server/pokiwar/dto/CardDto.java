package com.server.pokiwar.dto;

import lombok.Data;

import java.util.List;

@Data
public class CardDto {

    private Long id;

    private String name;

    private String description;

    private int value;

    private int maxLever;

    private int lever;
    private Long conditionUse;
    private List<ImageDto> image;
}
