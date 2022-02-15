package br.com.academy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.academy.Enums.Curso;
import br.com.academy.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="aluno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nome")
	@Size(min=3, max=80, message="O nome deverá conter no mínimo 3 letras para continuar")
	@NotBlank(message="O nome não pode ser vazio")
	private String nome;
	
	@Column(name="curso")
	@Enumerated(EnumType.STRING)
	@NotNull(message="Selecione um curso para continuar")
	private Curso curso;
	
	@Column(name="matricula")
	@NotNull(message="Clique no botão gerar para continuar")
	@Size(min = 3, message="Clique no botão para gerar uma matricula")
	private String matricula;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	@NotNull(message="O status precisa ser selecionado para continuar")
	private Status status;
	
	@Column(name="turno")
	@NotBlank(message = "O turno deverá ter ter 5 letras para continuar")
	private String turno;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	
	
	

}
