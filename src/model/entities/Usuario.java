package model.entities;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idusuario;
	private String nome;
	private String cpf;
	
	public Usuario(){
		
	}
	
	public Usuario(Integer idusuario, String nome, String cpf) {
		this.idusuario = idusuario;
		this.nome = nome;
		this.cpf = cpf;
	}

	public Usuario(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idusuario;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idusuario != other.idusuario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", nome=" + nome + ", cpf=" + cpf + "]";
	}
	
	
	
}
