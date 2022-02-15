package br.com.academy.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.Aluno;
import br.com.academy.repository.AlunoRepository;

@Controller
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunorepositorio;
	
	@GetMapping("/inserirAlunos")
	public ModelAndView insertAlunos(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/formAluno");
		mv.addObject("aluno", new Aluno());
		return mv;
		
	}
	
	@PostMapping("InsertAlunos")
	public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		if(br.hasErrors()) {
			mv.setViewName("aluno/formAluno");
		    mv.addObject(aluno);
		} else {
			mv.setViewName("redirect:/alunos-adicionados");
			alunorepositorio.save(aluno);
		}
		return mv;
	}
	
	@GetMapping("alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/listAlunos");
		mv.addObject("alunosList", alunorepositorio.findAll());
		return mv;
	}
	
	@GetMapping("alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alterar");
		Optional<Aluno> aluno = alunorepositorio.findById(id);
		mv.addObject("aluno", aluno);
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterando(Aluno aluno) {
		ModelAndView mv = new ModelAndView(); 
		alunorepositorio.save(aluno);
		mv.setViewName("redirect:/alunos-adicionados");
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirAluno(@PathVariable("id") Integer id) {
		alunorepositorio.deleteById(id);
		return "redirect:/alunos-adicionados";
	}

}
