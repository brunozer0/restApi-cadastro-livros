package com.bruno.livraria.services;

import java.util.List;

import com.bruno.livraria.model.Livros;

public interface LivrosService {

	Livros salvarLivro(Livros livro);

	Livros updateLivro(Livros livro);

	void deleteLivro(Livros livro);

	Livros getLivroById(Long id);


	List<Livros> getLivros();
}
