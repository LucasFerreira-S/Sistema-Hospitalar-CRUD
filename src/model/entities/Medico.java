package model.entities;

import java.io.Serializable;

public class Medico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idMed;
	private String nomeMed;
	private String area;
	
	public Medico() {
		
	}

	public Medico(Integer idMed, String nomeMed, String area) {
		this.idMed = idMed;
		this.nomeMed = nomeMed;
		this.area = area;
	}

	public int getIdMed() {
		return idMed;
	}

	public void setIdMed(Integer idMed) {
		this.idMed = idMed;
	}

	public String getNomeMed() {
		return nomeMed;
	}

	public void setNomeMed(String nomeMed) {
		this.nomeMed = nomeMed;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMed;
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
		Medico other = (Medico) obj;
		if (idMed != other.idMed)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medico [idMed=" + idMed + ", nomeMed=" + nomeMed + ", area=" + area + "]";
	}
	
	
	
	
}
