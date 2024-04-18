package com.bruno.livraria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		assertEquals(123L, outputLivro.getBody().getId());
		verify(livrosService).salvarLivro(criarLivroRequest);
	}

	@Test
	void testUpdateLivros() {
		Livros livro = new Livros();
		livro.setId(123L);

		when(livrosService.getLivroById(123L)).thenReturn(livro);

		when(livrosService.updateLivro(livro)).thenReturn(livro);

		ResponseEntity<Livros> outputLivros = livrosController.updateLivro(livro);

		assertNotNull(outputLivros);

		assertNotNull(outputLivros.getBody());
		assertEquals(123L, outputLivros.getBody().getId());
		verify(livrosService).updateLivro(livro);
	}

	@Test
	void testGetLivros() {
		ArrayList<Livros> livros = new ArrayList<Livros>();
		livros.add(new Livros());
		livros.add(new Livros());
		when(livrosService.getLivros()).thenReturn(livros);
		List<Livros> outputLivros = livrosController.getLivros();
		assertNotNull(outputLivros);
		assertEquals(2, outputLivros.size());
		verify(livrosService).getLivros();
	}

	@Test
	void testDeleteLivros() {
		Livros livroId = new Livros();
		livroId.setId(123L);

		when(livrosService.getLivroById(123L)).thenReturn(livroId);

		doNothing().when(livrosService).deleteLivro(livroId);

		livrosController.deleteLivro(123L);

		verify(livrosService).deleteLivro(livroId);
	}
	
	
	@Test
	void testGetLivroById() {
		Livros livro = new Livros();
		livro.setId(123L);
		livro.setAutor("teste");
		
		when(livrosService.getLivroById(123L)).thenReturn(livro);


		livrosController.getLivroById(123L);

		verify(livrosService).getLivroById(123L);
	}
}
