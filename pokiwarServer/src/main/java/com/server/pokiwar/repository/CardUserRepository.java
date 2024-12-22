package com.server.pokiwar.repository;

import com.server.pokiwar.dto.CardUserDto;
import com.server.pokiwar.model.CardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardUserRepository extends JpaRepository<CardUser,Long> {

    @Query(value = "SELECT * " +
            "FROM tbl_card_user " +
            "WHERE id_user =:idUser", nativeQuery = true)
    List<CardUser> listCardUser(Long idUser);

    @Query(value = "SELECT new com.server.pokiwar.dto.CardUserDto(cu.id, cu.idCard, cu.idUser, cu.lever, cu.count, cu.value, c.conditionUse) " +
            "FROM CardUser cu, Card c " +
            "WHERE cu.id = :id AND cu.idCard = c.id")
    CardUserDto findByIdWhere(Long id);

}
