package br.com.academy.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.Aluno;
import br.com.academy.model.Usuario;
import br.com.academy.repository.AlunoRepository;

@Controller
public class AlunoController {
	
	@Autowired 
	private AlunoRepository alunorepositorio;
	
	@GetMapping("teste")
	public ModelAndView teste() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/Teste");
		return mv;
	}
	
	@GetMapping("/inserir-alunos")
	public ModelAndView insertAlunos(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/formAluno");
		mv.addObject("aluno", new Aluno());
		return mv;
		
	}
	
	@PostMapping("insert-alunos")
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
	
	@GetMapping("filtro-alunos")
	public ModelAndView filtroAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/filtroAlunos");
        return mv;	 
 	}
	
	@GetMapping("alunos-ativos")
	public ModelAndView listarAtivos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunosAtivos");
		mv.addObject("alunosAtivos", alunorepositorio.findByStatusAtivos());
		return mv;
	}
	
	@GetMapping("alunos-inativos")
	public ModelAndView listarInativos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunosInativos");
		mv.addObject("alunosInativos", alunorepositorio.findByStatusInativos());
		return mv;
	}
	
	@GetMapping("alunos-trancados")
	public ModelAndView listarTrancados() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunosTrancados");
		mv.addObject("alunosTrancados", alunorepositorio.findByStatusTrancados());
		return mv;
	}
	
	@GetMapping("alunos-cancelados")
	public ModelAndView listarCancelados() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunosCancelados");
		mv.addObject("alunosCancelados", alunorepositorio.findByStatusCancelados());
		return mv;
	}
	
	@GetMapping("alunos-totais")
	public ModelAndView listarTodos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunosTotais");
		mv.addObject("alunosTotais", alunorepositorio.findAll());
		return mv;
	}
	
	@PostMapping("pesquisar-aluno")
	public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView();
		List<Aluno> listaAlunos;
		if(nome == null || nome.trim().isEmpty()) {
			listaAlunos = alunorepositorio.findAll();
		} else {
			listaAlunos = alunorepositorio.findByNomeContainingIgnoreCase(nome);
		}
		mv.addObject("listaDeAlunos", listaAlunos);
		mv.setViewName("aluno/pesquisaResultado");
		return mv; 

}
	
}
