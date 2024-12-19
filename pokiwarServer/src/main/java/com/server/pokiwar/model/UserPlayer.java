package com.server.pokiwar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
public class UserPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "user")
    private String user;

    @Column(name = "energy")
    private int energy = 0;

    @Column(name = "energy_full")
    private int energyFull;

    @Column(name = "lever")
    private int lever;

    @Column(name = "code")
    private String code;

    @Column(name = "id_pet_user")
    private Long idPetUser;

    @Column(name = "attack")
    private int attack = 0;

    @Column(name = "mana")
    private int mana = 0;

    @Column(name = "blood")
    private int blood = 0;

    @Column(name = "gold")
    private int gold = 10000;

    @Column(name = "playerId")
    private Long playerId;

    @Column(name = "money")
    private int money = 10000;

    @Column(name = "dame_type_pet_use")
    private int dameTypePetUser = 0;

}
