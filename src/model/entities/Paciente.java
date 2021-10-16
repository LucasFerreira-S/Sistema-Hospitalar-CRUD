package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Paciente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private Integer idPaciente;
	private String nomePaciente;
	private Date dataNasc;
	private String RG;
	private String CPF;
	
	
	public Paciente() {
		
	}

	public Paciente(Integer idPaciente, String nomePaciente, Date dataNasc, String rG, String cPF) {
		this.idPaciente = idPaciente;
		this.nomePaciente = nomePaciente;
		this.dataNasc = dataNasc;
		RG = rG;
		CPF = cPF;
	}
	

	public Integer getIdPaciente() {
		return idPaciente;
	}


	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}


	public String getNomePaciente() {
		return nomePaciente;
	}


	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}


	public Date getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}


	public String getRG() {
		return RG;
	}


	public void setRG(String rG) {
		RG = rG;
	}


	public String getCPF() {
		return CPF;
	}


	public void setCPF(String cPF) {
		CPF = cPF;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPaciente == null) ? 0 : idPaciente.hashCode());
		result = prime * result + ((nomePaciente == null) ? 0 : nomePaciente.hashCode());
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
		Paciente other = (Paciente) obj;
		if (idPaciente == null) {
			if (other.idPaciente != null)
				return false;
		} else if (!idPaciente.equals(other.idPaciente))
			return false;
		if (nomePaciente == null) {
			if (other.nomePaciente != null)
				return false;
		} else if (!nomePaciente.equals(other.nomePaciente))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", nomePaciente=" + nomePaciente + ", DataNasc=" + dataNasc
				+ ", RG=" + RG + ", CPF=" + CPF + "]";
	}
}
