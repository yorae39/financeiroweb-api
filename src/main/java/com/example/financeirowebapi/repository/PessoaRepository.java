package com.example.financeirowebapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financeirowebapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
