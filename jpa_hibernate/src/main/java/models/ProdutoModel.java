import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Produto;

public class ProdutoModel {

	public void create(Produto p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transa��o");
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Produto criado com sucesso !!!");
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao criar o produto !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transa��o");
		}
	}

	public void update(Produto p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
			System.out.println("Produto atualizado: "+p.toString());
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar o produto: " + e.getMessage());
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

			Produto produto = em.find(Produto.class, id);
			em.remove(produto);
			em.getTransaction().commit();
			System.out.println("Produto deletado: " + produto.toString());
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao deletar o produto: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}
	}

	public Produto findById(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();
		Produto produto = null;

		try {
			produto = em.find(Produto.class, id);
			System.out.println("Produto encontrado: "+produto);
		} catch (Exception e) {
			System.err.println("Erro ao buscar o produto: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}

		return produto;
	}

	public List<Produto> findAll() {
		List<Produto> produtos = new ArrayList<Produto>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			produtos = em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
		} catch (Exception e) {
			System.err.println("Erro ao buscar os produtos: " + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a opera��o");
		}
		return produtos;
	}
}