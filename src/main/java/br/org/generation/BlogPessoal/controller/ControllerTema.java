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

import br.org.generation.BlogPessoal.model.ModelTema;
import br.org.generation.BlogPessoal.repository.RepositoryTema;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerTema {

	@Autowired
	private RepositoryTema repositoryTema;

	@GetMapping
	public ResponseEntity<List<ModelTema>> getALL() {
		return ResponseEntity.ok(repositoryTema.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModelTema> gteByIdTema(@PathVariable long idTema) {
		return repositoryTema.findById(idTema).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<ModelTema>> getByName(@PathVariable String descricao) {
		return ResponseEntity.ok(repositoryTema.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping
	public ResponseEntity<ModelTema> postTema(@RequestBody ModelTema tema) {
		return ResponseEntity.ok(repositoryTema.save(tema));
	}

	@PutMapping
	public ResponseEntity<ModelTema> putTema(@Valid @RequestBody ModelTema tema) {
		return repositoryTema.findById(tema.getIdTema())
				.map(resp -> ResponseEntity.ok().body(repositoryTema.save(tema)))
				.orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTema(@PathVariable long id) {
		
		return repositoryTema.findById(id)
				.map(resp -> {
					repositoryTema.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}