package br.ufrn.imd.sa.sa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.sa.sa.model.Aluno;
import br.ufrn.imd.sa.sa.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepository;
	
	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}
	
	public Optional<Aluno> findById(long id) {
		return alunoRepository.findById(id);
	}
	
	public List<Aluno> findByTurmaId(Long id) {
		return alunoRepository.findByTurmaId(id);
	}
	
	public Aluno save(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public void delete(long id) {
		alunoRepository.deleteById(id);
	}
	
}
