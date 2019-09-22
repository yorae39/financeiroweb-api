package com.example.financeirowebapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financeirowebapi.model.Lancamento;
import com.example.financeirowebapi.model.Pessoa;
import com.example.financeirowebapi.repository.LancamentoRepository;
import com.example.financeirowebapi.repository.PessoaRepository;
import com.example.financeirowebapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa p = pessoaRepository.findOne(lancamento.getPessoa().getId());
		if(p == null || p.isInativo()) {
		  throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}
	
}
