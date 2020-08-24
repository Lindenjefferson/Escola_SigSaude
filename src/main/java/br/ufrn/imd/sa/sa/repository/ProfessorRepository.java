package br.ufrn.imd.sa.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.imd.sa.sa.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	Professor findByNome(String nome);
	List<Professor> findByidNotIn(List<Long> comTurma);
	
}
