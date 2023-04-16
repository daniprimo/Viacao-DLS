package com.viacao.gatopreto.garagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viacao.gatopreto.garagem.model.Garagem;

@Repository
public interface GaragemRepository extends JpaRepository<Garagem, Long>{

}
