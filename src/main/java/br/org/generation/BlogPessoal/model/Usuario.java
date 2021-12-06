package br.org.generation.BlogPessoal.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;

	@NotBlank
	@Size(min = 2, max = 100)
	private String nome;
	
	@ApiModelProperty(example = "email@email.com.br")
	@NotBlank(message = "O atributo usuário é obrigatório!")
	@Email(message = "O formato do usuário é e-mail.")
	private String usuario;

	@NotBlank
	@Size(min=8)
	private String senha;
	
	private String foto;
	
	private String tipo;

	@OneToMany (mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties ("usuario")
	private List<ModelPostagem> postagem;
	
	public Usuario(long idUsuario, String nome, String usuario, String senha) {
		
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario() {
		
	}		
	

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<ModelPostagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<ModelPostagem> postagem) {
		this.postagem = postagem;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
