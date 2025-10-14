import java.util.List;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

public class AdministrativoApp {

	public static void main(String[] args) {
		ProdutoModel produtoModel = new ProdutoModel();

		Produto p1 = new Produto();
		p1.setNome("TV");
		p1.setPreco(300.0);
		p1.setQuantidade(100);
		p1.setStatus(true);

		// 1) Criando um produto
		produtoModel.create(p1);

		// 2) Buscando todos os produtos na base de dados
		List<Produto> produtos = produtoModel.findAll();
		System.out.println("Qtde de produtos encontrados : " + produtos.size());

		// 3) Buscando produto por Id
		produtoModel.findById(produtos.get(0).getId());

		// 4) Atualizando produto
		p1.setNome("Computador");
		p1.setStatus(false);
		produtoModel.update(p1);

		// 5) Deletando produto
		produtoModel.delete(produtos.get(0).getId());

		// PESSOA

		PessoaModel pessoaModel = new PessoaModel();

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("ALAN ");
		pessoa.setIdade(32);
		pessoa.setCpf("111.111.111-11");
		pessoa.setEmail("alan@teste.com");
		pessoa.setDataNascimento("01/01/2000");

		// 1) Criando um produto
		pessoaModel.create(pessoa);

		// 2) Buscando todos os produtos na base de dados
		List<Pessoa> pessoas = pessoaModel.findAll();
		System.out.println("Qtde de pessoas encontradas : " + pessoas.size());

		// 3) Buscando produto por Id
		pessoaModel.findById(pessoas.get(0).getId());

		// 4) Atualizando produto
		pessoa.setNome("Javeiro");
		pessoa.setIdade(22);
		pessoaModel.update(pessoa);

		// 5) Deletando produto
		pessoaModel.delete(pessoas.get(0).getId());
	}
}