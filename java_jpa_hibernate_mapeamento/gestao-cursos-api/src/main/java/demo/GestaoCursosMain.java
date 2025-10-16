import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import entities.Aluno;
import entities.Curso;
import entities.Endereco;
import entities.MaterialCurso;
import entities.Professor;
import entities.Telefone;
import models.AlunoModel;
import models.CursoModel;

public class GestaoCursosMain {

	public static void main(String[] args) throws ParseException {
		Endereco endereco1 = new Endereco();
		Endereco endereco2 = new Endereco();

		Telefone telefone1 = new Telefone();
		Telefone telefone2 = new Telefone();

		Curso curso1 = new Curso();
		Curso curso2 = new Curso();
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno();

		endereco1.setLogradouro("Rua Dom pedro I");
		endereco1.setEndereco("Condom�nio Venetto");
		endereco1.setBairro("centro");
		endereco1.setNumero("211");
		endereco1.setEstado("SP");
		endereco1.setCep(12345678);

		endereco2.setLogradouro("Rua das acacias");
		endereco2.setEndereco("Condom�nio das Pedras");
		endereco2.setBairro("rio abaixo");
		endereco2.setNumero("21");
		endereco2.setEstado("SP");
		endereco2.setCep(01011011);

		telefone1.setDDD("11");
		telefone1.setNumero("91111-1111");

		telefone2.setDDD("21");
		telefone2.setNumero("91111-2121");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("01/01/2000");

		aluno1.setNomeCompleto("FULANO BELTRANO DA SILVA");
		aluno1.setNascimento(date);
		aluno1.setEnderecos(Arrays.asList(endereco1));
		aluno1.setTelefones(Arrays.asList(telefone1));
		aluno1.setMatricula("1010");

		aluno2.setNomeCompleto("MARIANA NORTENHA");
		aluno2.setNascimento(date);
		aluno2.setEnderecos(Arrays.asList(endereco2));
		aluno2.setTelefones(Arrays.asList(telefone2));
		aluno2.setMatricula("1011");

		MaterialCurso material1 = new MaterialCurso();
		material1.setUrl("materialCurso/Java");

		MaterialCurso material2 = new MaterialCurso();
		material2.setUrl("materialCurso/DataBase");

		curso1.setMaterial(material1);
		curso1.setNome("Java");

		curso2.setMaterial(material2);
		curso2.setNome("DataBase");

		Professor professor = new Professor();
		professor.setNomeCompleto("Albert einstein");
		professor.setEmail("albert@cursoMaterial.com");
		professor.setMatricula("1001");
		professor.setCursos(Arrays.asList(curso1, curso2));

		curso1.setProfessor(professor);
		curso1.setAlunos(Arrays.asList(aluno1, aluno2));
		curso1.setSigla("J");

		curso2.setProfessor(professor);
		curso2.setAlunos(Arrays.asList(aluno2));
		curso2.setSigla("D");

		material1.setCurso(curso1);
		material2.setCurso(curso2);

		aluno1.setCursos(Arrays.asList(curso1));
		aluno2.setCursos(Arrays.asList(curso1, curso2));

		CursoModel cursoModel = new CursoModel();

//		// 1) Criando um curso
		cursoModel.create(curso1);
		cursoModel.create(curso2);
//
//		// 2) Buscando todos os cursos na base de dados
		List<Curso> todosCursos = cursoModel.findAll();
		System.out.println("Qtde de cursos encontrados : " + todosCursos.size());
//		// 3) Buscando curso por Id
// 		cursoModel.findById(todosCursos.get(0).getId());
//
//		// 4) Atualizando curso
//		
//		material1.setUrl("materialCurso/Java2");
//		
//		curso1.setMaterial(material1);
//		curso1.setNome("Java2");
//		cursoModel.update(curso1);
//		
//		// 5) Deletando curso
//		cursoModel.delete(todosCursos.get(1));
		
		AlunoModel alunoModel = new AlunoModel();

		// 1) Criando um Aluno
		alunoModel.create(aluno1);
		alunoModel.create(aluno2);

		// 2) Buscando todos os Aluno na base de dados
		List<Aluno> todosAlunos = alunoModel.findAll();
		System.out.println("Qtde de alunos encontrados : " + todosAlunos.size());
		// 3) Buscando Aluno por Id
		alunoModel.findById(todosAlunos.get(0).getId());

		// 4) Atualizando Aluno
		
		aluno2.setCursos(todosCursos);
		aluno2.setTelefones(Arrays.asList(telefone1));
		alunoModel.update(aluno2);
		
		// 5) Deletando Aluno
		alunoModel.delete(todosAlunos.get(1));
	}

}
