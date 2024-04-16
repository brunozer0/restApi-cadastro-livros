package com.bruno.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.livraria.model.Livros;

@Repository
public interface LivroRepository extends JpaRepository<Livros, Long> {

}
