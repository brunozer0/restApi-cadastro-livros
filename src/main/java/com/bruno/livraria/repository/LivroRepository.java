package com.bruno.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.livraria.model.Livros;


public interface LivroRepository extends JpaRepository<Livros, Long> {

}
