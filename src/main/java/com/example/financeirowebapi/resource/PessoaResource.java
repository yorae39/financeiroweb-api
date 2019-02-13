package com.example.financeirowebapi.resource;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.financeirowebapi.event.RecursoCriadoEvent;
import com.example.financeirowebapi.model.Pessoa;
import com.example.financeirowebapi.repository.PessoaRepository;
import com.example.financeirowebapi.service.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "pesssoa")
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
	PessoaRepository repository;
    
    @Autowired
    PessoaService service;

    @Autowired
    private ApplicationEventPublisher publisher;
    
	@ApiOperation(value = "Find all pessoas")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	@ApiOperation(value = "Create pessoa")
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {

		Pessoa pessoaSalva = repository.save(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@ApiOperation(value = "Find pessoa by id")
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable Long id) {		
		Pessoa pessoa = repository.findOne(id);
		return pessoa == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(pessoa);
	}
	
	
	@ApiOperation(value = "Update an existing pessoa")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		
		Pessoa pessoaSalva = service.pessoaAtualizar(id, pessoa);
		
		return ResponseEntity.ok(pessoaSalva);
	}
	
	
	@ApiOperation(value = "Update status an existing pessoa")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/update/{id}/ativo", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		
		service.atualizarPropriedadeAtivo(id, ativo);
	}
	
	@ApiOperation(value = "Delete an existing pessoa")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {

		final Date dataAtual = new Date();
		final Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		final String data = formatter.format(dataAtual);
		String info = "Pessoa id: "+id+" removed in " + data;
		repository.delete(id);
		
		return ResponseEntity.ok(info);
	}
	
}
