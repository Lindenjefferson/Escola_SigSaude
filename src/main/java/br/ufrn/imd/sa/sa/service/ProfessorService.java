package br.ufrn.imd.sa.sa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.sa.sa.model.Professor;
import br.ufrn.imd.sa.sa.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	ProfessorRepository profRepository;
	
	public List<Professor> findAll() {
		return profRepository.findAll();
	}
	
	public List<Professor> semTurma(List<Long> comTurma) {
		return profRepository.findByidNotIn(comTurma);
	}
	
	public Optional<Professor> findById(long id) {
		return profRepository.findById(id);
	}
	
	public Professor findByNome(String nome) {
		return profRepository.findByNome(nome);
	}
	
	public Professor save(Professor prof) {
		return profRepository.save(prof);
	}
	
	public void delete(long id) {
		profRepository.deleteById(id);
	}
	
}
