package br.ufrn.imd.sa.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.imd.sa.sa.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	List<Aluno> findByTurmaId(Long id);
	
}
