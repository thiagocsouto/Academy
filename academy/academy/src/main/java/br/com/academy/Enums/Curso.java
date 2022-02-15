package br.com.academy.Enums;


public enum Curso {
	
	ADMINISTRACAO("administração"),
	INFORMATICA("informatica"),
	CONTABILIDADE("contabilidade"),
	PROGRAMACAO("progração"),
	ENFERMAGEM("enfermagem");
	
	private String curso;
	private Curso(String curso) {
		this.curso = curso;
	}

} 
