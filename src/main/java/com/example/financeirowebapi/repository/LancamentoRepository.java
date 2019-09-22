package com.example.financeirowebapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financeirowebapi.model.Lancamento;
import com.example.financeirowebapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
