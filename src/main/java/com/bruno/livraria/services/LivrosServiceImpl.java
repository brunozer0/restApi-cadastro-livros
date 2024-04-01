package com.bruno.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.livraria.Exceptions.IdNotFoundException;
import com.bruno.livraria.dto.CriarLivroRequest;
import com.bruno.livraria.model.Livros;
import com.bruno.livraria.repository.LivroRepository;

@Service
public class LivrosServiceImpl implements LivrosService {

	@Autowired
	LivroRepository livroRepository;

	@Override
	public Livros salvarLivro(CriarLivroRequest criarLivroRequest) {
		Livros livro = new Livros();
		livro.setTitulo(criarLivroRequest.getTitulo());
		livro.setAutor(criarLivroRequest.getAutor());
		livro.setGenero(criarLivroRequest.getGenero());
		livro.setEditora(criarLivroRequest.getEditora());
		livro.setDescricao(criarLivroRequest.getDescricao());
		livro.setDate(criarLivroRequest.getDate());

		return livroRepository.save(livro);
	}

	@Override
	public Livros updateLivro(Livros livro) {
		Optional<Livros> livroOptional = livroRepository.findById(livro.getId());
		if (livroOptional.isPresent()) {
			return livroRepository.save(livro);
		} else {
			throw new IdNotFoundException("Livro com ID " + livro.getId() + " não encontrado");
		}
	}

	@Override
	public void deleteLivro(Livros livro) {
		Optional<Livros> livroOptional = livroRepository.findById(livro.getId());
		if (livroOptional.isPresent()) {
			livroRepository.delete(livro);
		} else {
			throw new IdNotFoundException("Livro com ID " + livro.getId() + " não encontrado");
		}
	}

	@Override
	public Livros getLivroById(Long id) {
		Optional<Livros> livroOptional = livroRepository.findById(id);
		if (livroOptional.isPresent()) {
			return livroOptional.get();
		} else {
			throw new IdNotFoundException("Livro com ID " + id + " não encontrado");
		}

	}

	@Override
	public List<Livros> getLivros() {
		return livroRepository.findAll();
	}

}
