package com.bruno.livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.livraria.model.Livros;
import com.bruno.livraria.repository.LivroRepository;

@Service
public class LivrosServiceImpl implements LivrosService {

	@Autowired
	LivroRepository livroRepository;

	@Override
	public Livros salvarLivro(Livros livro) {
		return livroRepository.save(livro);
	}

	@Override
	public Livros updateLivro(Livros livro) {
		return livroRepository.save(livro);
	}

	@Override
	public void deleteLivro(Livros livro) {

		livroRepository.delete(livro);
	}

    @Override
    public Livros getLivroById(Long id) {
        return livroRepository.findById(id).orElse(null);
    }


	@Override
	public List<Livros> getLivros() {
		return livroRepository.findAll();
	}

}
