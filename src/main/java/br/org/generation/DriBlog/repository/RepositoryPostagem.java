package br.org.generation.DriBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.DriBlog.model.ModelPostagem;

@Repository
public interface RepositoryPostagem extends JpaRepository<ModelPostagem, Long> {

	public List<ModelPostagem> findAllByTituloContainingIgnoreCase(String titulo);

}
