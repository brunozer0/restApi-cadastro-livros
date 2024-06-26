package com.bruno.livraria.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public class CriarLivroRequest {

	@NotBlank(message = "Titulo precisa ser informado")
	private String titulo;
	@NotBlank(message = "Autor precisa ser informado")
	private String autor;
	@NotBlank(message = "Editora precisa ser informada")
	private String editora;
	@NotBlank(message = "Descricao precisa ser informado")
	private String descricao;
	@NotBlank(message = "genero precisa ser informado")
	private String genero;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate data;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LocalDate getDate() {
		return data;
	}

	public void setDate(LocalDate date) {
		this.data = date;
	}

}
