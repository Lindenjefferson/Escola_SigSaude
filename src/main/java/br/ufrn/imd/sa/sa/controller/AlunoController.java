package br.ufrn.imd.sa.sa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufrn.imd.sa.sa.model.Aluno;
import br.ufrn.imd.sa.sa.model.Turma;
import br.ufrn.imd.sa.sa.service.AlunoService;
import br.ufrn.imd.sa.sa.service.TurmaService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	AlunoService alunoService;
	
	@Autowired
	TurmaService turmaService;
	
	@GetMapping
	public ModelAndView listaAlunos() {
		ModelAndView mv = new ModelAndView("Aluno/aluno");
		mv.addObject("alunos", alunoService.findAll());
		mv.addObject("turmas", turmaService.findAll());
		mv.addObject("alunoObj", new Aluno());
		return mv;
	}
	
	@GetMapping("/={id}")
	public ModelAndView editaAluno(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView("Aluno/aluno");
		mv.addObject("alunos", alunoService.findAll());
		mv.addObject("turmas", turmaService.findAll());
		mv.addObject("alunoObj", alunoService.findById(id));
		return mv;
	}
	
	@GetMapping("/turma/{id}")
	public ModelAndView turmaAlunos(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView("Aluno/aluno");
		mv.addObject("alunos", alunoService.findByTurmaId(id));
		mv.addObject("turmas", turmaService.findAll());
		mv.addObject("alunoObj", new Aluno());
		return mv;
	}
	
	@PostMapping
	public String salvarAluno(Aluno aluno) {
		Turma turma = turmaService.findBySala(aluno.getTurma().getSala());
		if(turma != null) {
			aluno.setTurma(turma);
			alunoService.save(aluno);
		}
		return "redirect:/aluno";
	}
	
	@GetMapping("delete/{id}")
	public String apagaAluno(@PathVariable(name = "id") long id) {
		Optional<Aluno> aluno = alunoService.findById(id);
		if(aluno.isPresent()) {
			alunoService.delete(id);
		}
		return "redirect:/aluno";
	}
	
	
}
