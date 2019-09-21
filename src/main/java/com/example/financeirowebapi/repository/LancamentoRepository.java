package com.example.financeirowebapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financeirowebapi.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
