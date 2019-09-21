package com.example.financeirowebapi.resource;

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
import com.example.financeirowebapi.model.Lancamento;
import com.example.financeirowebapi.repository.LancamentoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "lancamento")
@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	LancamentoRepository repository;
	

	@Autowired
    private ApplicationEventPublisher publisher;
	
	
	@ApiOperation(value = "Find all lançamentos")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lancamento> findAll() {
		return repository.findAll();
	}

	@ApiOperation(value = "Find lançamento by id")
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable Long id) {		
		Lancamento lancamento = repository.findOne(id);
		return lancamento == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(lancamento);
	}
	

	@ApiOperation(value = "Create lançamento")
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {	
		Lancamento lancamentoSalvo = repository.save(lancamento);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
}
