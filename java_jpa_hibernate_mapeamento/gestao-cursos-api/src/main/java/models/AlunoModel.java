import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Aluno;
import entities.Curso;

public class AlunoModel {

	public void create(Aluno aluno) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transa��o");
			em.getTransaction().begin();
			em.merge(aluno);
			em.getTransaction().commit();
			System.out.println("Aluno criado com sucesso !!!");
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao criar um Aluno !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transa��o");
		}
	}

	public Aluno findById(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();
		Aluno aluno = null;

		try {
			aluno = em.find(Aluno.class, id);
			System.out.println("Aluno encontrado: " + aluno);
		} catch (Exception e) {
			System.err.println("Erro ao buscar o Aluno: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}

		return aluno;
	}

	public List<Aluno> findAll() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			alunos = em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
		} catch (Exception e) {
			System.err.println("Erro ao buscar os Alunos: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}
		return alunos;
	}

	public void update(Aluno aluno) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(aluno);
			em.getTransaction().commit();
			System.out.println("Aluno atualizada: " + aluno.toString());
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar o Aluno: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}
	}

	public void delete(Aluno aluno) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();
		Aluno alunoEncontrado = null;

		try {
			em.getTransaction().begin();
			alunoEncontrado = em.find(Aluno.class, aluno.getId());
			if (alunoEncontrado != null) {
				em.remove(alunoEncontrado);
				em.getTransaction().commit();
				System.out.println("Aluno deletado: " + alunoEncontrado.getNomeCompleto());
			} else {
				System.out.println("Aluno n�o encontrado para deletar.");
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao deletar o aluno: " + e.getMessage());
		} finally {
			em.close();
		}
	}

}
