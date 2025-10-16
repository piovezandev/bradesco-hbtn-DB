import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String sigla;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "professor_id")
    private Professor professor;
    
    @OneToOne(mappedBy = "curso", cascade = CascadeType.ALL)
    private MaterialCurso material;
	
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "curso_aluno",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public MaterialCurso getMaterial() {
		return material;
	}

	public void setMaterial(MaterialCurso material) {
		this.material = material;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", professor=" + professor + ", material="
				+ material + ", alunos=" + alunos + "]";
	}
	
}
