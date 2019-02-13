package com.example.financeirowebapi.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.financeirowebapi.model.Pessoa;
import com.example.financeirowebapi.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository repository;

	public Pessoa pessoaAtualizar(Long id, Pessoa pessoa) {

		Pessoa pessoaSalva = repository.findOne(id);

		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

		final Date dataAtual = new Date();
		final Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		final String data = formatter.format(dataAtual);
		pessoaSalva.setInfo("Pessoa updated on this date : " + data);

		return repository.save(pessoaSalva);
	}

}
