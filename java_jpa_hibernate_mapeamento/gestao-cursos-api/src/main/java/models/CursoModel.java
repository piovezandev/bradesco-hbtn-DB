import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Curso;

public class CursoModel {

	public void create(Curso curso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transa��o");
			em.getTransaction().begin();
			em.merge(curso);
			em.getTransaction().commit();
			System.out.println("Curso criado com sucesso !!!");
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao criar um Curso !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transa��o");
		}
	}

	public Curso findById(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();
		Curso curso = null;

		try {
			curso = em.find(Curso.class, id);
			System.out.println("Curso encontrado: " + curso);
		} catch (Exception e) {
			System.err.println("Erro ao buscar o Curso: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}

		return curso;
	}

	public List<Curso> findAll() {
		List<Curso> cursos = new ArrayList<Curso>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
		} catch (Exception e) {
			System.err.println("Erro ao buscar os Cursos: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}
		return cursos;
	}

	public void update(Curso curso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(curso);
			em.getTransaction().commit();
			System.out.println("Curso atualizado: " + curso.toString());
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar o Curso: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}
	}

	public void delete(Curso curso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();
		Curso cursoEncontrado = null;

		try {
			em.getTransaction().begin();
			cursoEncontrado = em.find(Curso.class, curso.getId());
			if (cursoEncontrado != null) {
				em.remove(cursoEncontrado);
				em.getTransaction().commit();
				System.out.println("Curso deletado: " + cursoEncontrado.getNome());
			} else {
				System.out.println("Curso n�o encontrado para deletar.");
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao deletar o Curso: " + e.getMessage());
		} finally {
			em.close();
		}
	}
}
