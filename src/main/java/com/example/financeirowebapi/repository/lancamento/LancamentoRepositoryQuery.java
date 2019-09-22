package com.example.financeirowebapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.financeirowebapi.model.Lancamento;
import com.example.financeirowebapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
