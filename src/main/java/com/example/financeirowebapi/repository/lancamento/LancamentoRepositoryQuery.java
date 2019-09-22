package com.example.financeirowebapi.repository.lancamento;

import java.util.List;

import com.example.financeirowebapi.model.Lancamento;
import com.example.financeirowebapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtar(LancamentoFilter lancamentoFilter);
}
