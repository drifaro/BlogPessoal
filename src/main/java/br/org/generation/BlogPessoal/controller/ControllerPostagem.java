package br.org.generation.BlogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.org.generation.BlogPessoal.model.ModelPostagem;
import br.org.generation.BlogPessoal.repository.RepositoryPostagem;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ControllerPostagem {

	@Autowired
	private RepositoryPostagem repositoryPostagem;

	@GetMapping
	public ResponseEntity<List<ModelPostagem>> getAll() {
		return ResponseEntity.ok(repositoryPostagem.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModelPostagem> GetByid(@PathVariable long id) {
		return repositoryPostagem.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<ModelPostagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repositoryPostagem.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping
	public ResponseEntity<ModelPostagem> postPostagem(@Valid @RequestBody ModelPostagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryPostagem.save(postagem));

	}

	@PutMapping
	public ResponseEntity<ModelPostagem> putPostagem(@Valid @RequestBody ModelPostagem postagem ) {
		return repositoryPostagem.findById(postagem.getIdPost())
				.map(resp -> ResponseEntity.ok().body(repositoryPostagem.save(postagem)))
				.orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		
		return repositoryPostagem.findById(id)
				.map(resp -> {
					repositoryPostagem.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}