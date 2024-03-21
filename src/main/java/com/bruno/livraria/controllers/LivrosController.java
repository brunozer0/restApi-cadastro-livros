package com.bruno.livraria.controllers;

import java.net.URI;
import java.util.List;

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

import com.bruno.livraria.dto.CriarLivroRequest;
import com.bruno.livraria.model.Livros;
import com.bruno.livraria.services.LivrosServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livros")
public class LivrosController {

	private final LivrosServiceImpl livrosServiceImpl;

	public LivrosController(LivrosServiceImpl livrosServiceImpl) {
		this.livrosServiceImpl = livrosServiceImpl;
	}

	@GetMapping

	public List<Livros> getLivros() {
		return livrosServiceImpl.getLivros();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livros> getLivroById(@PathVariable("id") Long id) {
		Livros livroId = livrosServiceImpl.getLivroById(id);
		if (livroId != null) {
			return ResponseEntity.ok(livroId);

		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping

	public ResponseEntity<Livros> cadastrarLivro(@Valid @RequestBody CriarLivroRequest criarLivroRequest) {

		Livros livro = new Livros();

		livro.setTitulo(criarLivroRequest.getTitulo());
		livro.setAutor(criarLivroRequest.getAutor());
		livro.setGenero(criarLivroRequest.getGenero());
		livro.setEditora(criarLivroRequest.getEditora());
		livro.setDescricao(criarLivroRequest.getDescricao());

		livro.setDate(criarLivroRequest.getDate());

		livrosServiceImpl.salvarLivro(livro);
		return ResponseEntity.created(URI.create("/api/livros/" + livro.getId())).build();
	}

	@PutMapping
	public ResponseEntity<Livros> updateLivro(@RequestBody Livros livro) {

		Livros livroId = livrosServiceImpl.getLivroById(livro.getId());
		if (livroId != null) {
			Livros updatedLivro = livrosServiceImpl.updateLivro(livro);

			return ResponseEntity.ok(updatedLivro);
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Livros> deleteLivro(@PathVariable("id") Long id) {
		Livros livro = livrosServiceImpl.getLivroById(id);
		if (livro != null) {
			livrosServiceImpl.deleteLivro(livro);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
