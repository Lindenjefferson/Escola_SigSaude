package br.ufrn.imd.sa.sa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufrn.imd.sa.sa.model.Professor;
import br.ufrn.imd.sa.sa.service.ProfessorService;

@Controller
@RequestMapping("/prof")
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	
	@GetMapping
	public ModelAndView listaProfessores() {
		ModelAndView mv = new ModelAndView("Professor/prof");
		mv.addObject("professores", professorService.findAll());
		mv.addObject("profObj", new Professor());
		return mv;
	}
	
	@GetMapping("/={id}")
	public ModelAndView detalhaProfessor(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView("Professor/prof");
		mv.addObject("professores", professorService.findAll());
		mv.addObject("profObj", professorService.findById(id));
		return mv;
	}
	
	@PostMapping
	public String salvarProfessor(Professor professor) {
		professorService.save(professor);
		return "redirect:/prof";
	}
	
	@GetMapping("delete/{id}")
	public String apagaProfessor(@PathVariable(name = "id") long id) {
		Optional<Professor> professor = professorService.findById(id);
		if(professor.isPresent()) {
			professorService.delete(id);
		}
		return "redirect:/prof";
	}
	
}
