package br.ufrn.imd.sa.sa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufrn.imd.sa.sa.model.Professor;
import br.ufrn.imd.sa.sa.model.Turma;
import br.ufrn.imd.sa.sa.service.ProfessorService;
import br.ufrn.imd.sa.sa.service.TurmaService;

@Controller
@RequestMapping("/turma")
public class TurmaController {

	@Autowired
	TurmaService turmaService;
	
	@Autowired
	ProfessorService professorService;
	
	public List<Professor> semTurma() {
		List<Turma> turmas = turmaService.findAll();
		List<Long> idProfs = new ArrayList<Long>();
		for(Turma turma : turmas) {
			idProfs.add(turma.getProfessor().getId());
		}
		if(idProfs.isEmpty()) {
			return professorService.findAll();
		} else {
			return professorService.semTurma(idProfs);
		}
	}
	
	@GetMapping
	public ModelAndView listaTurmas() {
		ModelAndView mv = new ModelAndView("Turma/turma");
		mv.addObject("turmas", turmaService.findAll());
		System.out.println(semTurma());
		mv.addObject("professores", semTurma());
		mv.addObject("turmaObj", new Turma());
		return mv;
	}
	
	@GetMapping("/={id}")
	public ModelAndView editaTurma(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView("Turma/turma");
		mv.addObject("turmas", turmaService.findAll());
		Optional<Turma> turma = turmaService.findById(id);
		List<Professor> profs = semTurma();
		profs.add(turma.get().getProfessor());
		mv.addObject("turmaObj", turma);
		mv.addObject("professores", profs);
		return mv;
	}
	
	@PostMapping
	public String salvarTurma(Turma turma) {
		Professor prof = professorService.findByNome(turma.getProfessor().getNome());
		if(prof != null) {
			turma.setProfessor(prof);
			turmaService.save(turma);
			prof.setTurma(turma);
			professorService.save(prof);
		}
		return "redirect:/turma";
	}
	
	@GetMapping("delete/{id}")
	public String apagaTurma(@PathVariable(name = "id") long id) {
		Optional<Turma> turma = turmaService.findById(id);
		if(turma.isPresent()) {
			turmaService.delete(id);
		}
		return "redirect:/turma";
	}
	
}
