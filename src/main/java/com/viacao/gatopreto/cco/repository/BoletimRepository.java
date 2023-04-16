package com.viacao.gatopreto.cco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viacao.gatopreto.cco.model.Boletim;

@Repository
public interface BoletimRepository extends JpaRepository<Boletim, Long>{

}
