package com.example.financeirowebapi.resource;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.financeirowebapi.event.RecursoCriadoEvent;
import com.example.financeirowebapi.exceptionhandler.ExceptionHandler.Erro;
import com.example.financeirowebapi.model.Lancamento;
import com.example.financeirowebapi.repository.LancamentoRepository;
import com.example.financeirowebapi.repository.filter.LancamentoFilter;
import com.example.financeirowebapi.service.LancamentoService;
import com.example.financeirowebapi.service.exception.PessoaInexistenteOuInativaException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "lancamento")
@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository repository;

	@Autowired
    private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoService service;
	
	@Autowired
	private MessageSource messageSource;

	
	
	@ApiOperation(value = "Find all lançamentos")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lancamento> findAll() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Find lançamentos by criteria - metamodelo")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/pesquisar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return repository.filtar(lancamentoFilter, pageable);
	}

	@ApiOperation(value = "Find lançamento by id")
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Lancamento> findById(@PathVariable Long id) {		
		Lancamento lancamento = repository.findOne(id);
		return lancamento == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(lancamento);
	}
	
	@ApiOperation(value = "Create lançamento")
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Lancamento> create(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {	
		Lancamento lancamentoSalvo = service.salvar(lancamento);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	@ApiOperation(value = "Delete an existing lançamento")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public  ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {	
		final Date dataAtual = new Date();
		final Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		final String data = formatter.format(dataAtual);
		String info = "Lançamento id: "+id+" removed in " + data;
		repository.delete(id);
		
		return ResponseEntity.ok(info);
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		String msgUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}
