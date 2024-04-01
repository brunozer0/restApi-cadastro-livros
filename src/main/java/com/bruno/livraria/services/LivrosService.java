package com.bruno.livraria.services;

import java.util.List;

import com.bruno.livraria.dto.CriarLivroRequest;
import com.bruno.livraria.model.Livros;

public interface LivrosService {

	Livros salvarLivro(CriarLivroRequest criarLivroRequest);

	Livros updateLivro(Livros livro);

	void deleteLivro(Livros livro);

	Livros getLivroById(Long id);

	List<Livros> getLivros();

}
