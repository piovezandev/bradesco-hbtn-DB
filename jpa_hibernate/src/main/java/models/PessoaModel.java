import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Pessoa;

public class PessoaModel {

	public void create(Pessoa p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transa��o");
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Pessoa criado com sucesso !!!");
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao criar Pessoa !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transa��o");
		}
	}

	public void update(Pessoa p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
			System.out.println("Pessoa atualizada: "+p.toString());
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar o Pessoa: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}
	}

	public void delete(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			Pessoa pessoa = em.find(Pessoa.class, id);
			em.remove(pessoa);
			em.getTransaction().commit();
			System.out.println("Pessoa deletada: " + pessoa.toString());
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao deletar o Pessoa: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}
	}

	public Pessoa findById(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();
		Pessoa pessoa = null;

		try {
			pessoa = em.find(Pessoa.class, id);
			System.out.println("Pessoa encontrada: "+pessoa);
		} catch (Exception e) {
			System.err.println("Erro ao buscar o Pessoa: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}

		return pessoa;
	}

	public List<Pessoa> findAll() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			pessoas = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
		} catch (Exception e) {
			System.err.println("Erro ao buscar os Pessoas: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}
		return pessoas;
	}
}
