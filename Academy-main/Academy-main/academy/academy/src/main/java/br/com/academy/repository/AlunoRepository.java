package br.com.academy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	
	@Query("select a from Aluno a where a.status = 'ATIVO' ")
	public List<Aluno> findByStatusAtivos();
	
	@Query("select a from Aluno a where a.status = 'INATIVO' ")
	public List<Aluno> findByStatusInativos();
	
	@Query("select a from Aluno a where a.status = 'TRANCADO' ")
	public List<Aluno> findByStatusTrancados();
	
	@Query("select a from Aluno a where a.status = 'CANCELADO' ")
	public List<Aluno> findByStatusCancelados();
	
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
 
}
