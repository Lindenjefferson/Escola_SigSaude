package br.ufrn.imd.sa.sa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.sa.sa.model.Turma;
import br.ufrn.imd.sa.sa.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository turmaRepository;

	public List<Turma> findAll() {
		return turmaRepository.findAll();
	}
	
	public Optional<Turma> findById(long id) {
		return turmaRepository.findById(id);
	}
	
	public Turma findBySala(String sala) {
		return turmaRepository.findBySala(sala);
	}
	
	public Turma save(Turma turma) {
		return turmaRepository.save(turma);
	}
	
	public void delete(long id) {
		turmaRepository.deleteById(id);
	}
	
}
