package com.bruno.livraria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.bruno.livraria.controllers.LivrosController;
import com.bruno.livraria.dto.CriarLivroRequest;
import com.bruno.livraria.model.Livros;
import com.bruno.livraria.services.LivrosService;

@SpringBootTest
class LivrariaApplicationTests {

	@Mock
	LivrosService livrosService;

	@InjectMocks
	LivrosController livrosController;

	@Test
	void testRegistrarLivro() {
		CriarLivroRequest criarLivroRequest = new CriarLivroRequest();

		Livros livroSalvo = new Livros();
		livroSalvo.setId(123L);
		when(livrosService.salvarLivro(criarLivroRequest)).thenReturn(livroSalvo);

		ResponseEntity<Livros> outputLivro = livrosController.cadastrarLivro(criarLivroRequest);
		assertNotNull(outputLivro);
		assertNotNull(outputLivro.getBody().getId());
		assertEquals(123, outputLivro.getBody().getId());
		verify(livrosService).salvarLivro(criarLivroRequest);
	}

}
