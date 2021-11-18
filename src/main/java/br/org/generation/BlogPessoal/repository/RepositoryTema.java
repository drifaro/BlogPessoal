package br.org.generation.BlogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.BlogPessoal.model.ModelTema;

@Repository
public interface RepositoryTema extends JpaRepository<ModelTema, Long> {
	public List<ModelTema> findAllByDescricaoContainingIgnoreCase( String Descricao);
}
