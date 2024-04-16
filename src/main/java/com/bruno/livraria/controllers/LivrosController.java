package com.bruno.livraria.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bruno.livraria.Exceptions.IdNotFoundException;
import com.bruno.livraria.dto.CriarLivroRequest;
import com.bruno.livraria.model.Livros;
import com.bruno.livraria.services.LivrosService;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "http://localhost:4200")
public class LivrosController {

	private final LivrosService livrosService;

	public LivrosController(LivrosService livrosService) {
		this.livrosService = livrosService;
	}

	@GetMapping
	public List<Livros> getLivros() {
		return livrosService.getLivros();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livros> getLivroById(@PathVariable("id") Long id) {
		try {
			Livros livroId = livrosService.getLivroById(id);
			return ResponseEntity.ok(livroId);
		} catch (IdNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}

	@PostMapping
	public ResponseEntity<Livros> cadastrarLivro(@RequestBody CriarLivroRequest criarLivroRequest) {
		try {
			Livros livroSalvo = livrosService.salvarLivro(criarLivroRequest);
			return ResponseEntity.created(URI.create("/api/livros/" + livroSalvo.getId())).body(livroSalvo);
		} catch (ConstraintViolationException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
		}
	}

	@PutMapping
	public ResponseEntity<Livros> updateLivro(@RequestBody Livros livro) {
		try {
			Livros updatedLivro = livrosService.updateLivro(livro);
			return ResponseEntity.ok(updatedLivro);
		} catch (IdNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		} catch (ConstraintViolationException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLivro(@PathVariable("id") Long id) {
		try {
			Livros livro = livrosService.getLivroById(id);
			livrosService.deleteLivro(livro);
			return ResponseEntity.noContent().build();
		} catch (IdNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

}
