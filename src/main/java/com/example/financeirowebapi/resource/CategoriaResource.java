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
import com.example.financeirowebapi.model.Categoria;
import com.example.financeirowebapi.repository.CategoriaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	CategoriaRepository repository;
	
	@Autowired
    private ApplicationEventPublisher publisher;

	@ApiOperation(value = "Find all categorias")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	@ApiOperation(value = "Create categoria")
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {

		Categoria categoriaSalva = repository.save(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@ApiOperation(value = "Find categoria by id")
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable Long id) {		
		Categoria categoria = repository.findOne(id);
		return categoria == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(categoria);
	}
}
