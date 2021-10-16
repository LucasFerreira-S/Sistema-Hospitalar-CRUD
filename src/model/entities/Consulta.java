package model.entities;

import java.util.Date;

public class Consulta {
	private Integer codConsulta;
	private Integer idPaciente;
	private Integer idMedico;
	private Date data;
	private String area;
	
	public Consulta() {
		
	}
	
	public Consulta(Integer codConsulta, Integer idPaciente, Integer idMedico, Date data, String area) {
	
		this.codConsulta = codConsulta;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		this.data = data;
		this.area = area;
	}

	public Integer getCodConsulta() {
		return codConsulta;
	}

	public void setCodConsulta(Integer codConsulta) {
		this.codConsulta = codConsulta;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((codConsulta == null) ? 0 : codConsulta.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((idMedico == null) ? 0 : idMedico.hashCode());
		result = prime * result + ((idPaciente == null) ? 0 : idPaciente.hashCode());
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
		Consulta other = (Consulta) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (codConsulta == null) {
			if (other.codConsulta != null)
				return false;
		} else if (!codConsulta.equals(other.codConsulta))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (idMedico == null) {
			if (other.idMedico != null)
				return false;
		} else if (!idMedico.equals(other.idMedico))
			return false;
		if (idPaciente == null) {
			if (other.idPaciente != null)
				return false;
		} else if (!idPaciente.equals(other.idPaciente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Consulta [codConsulta=" + codConsulta + ", idPaciente=" + idPaciente + ", idMedico=" + idMedico
				+ ", data=" + data + ", area=" + area + "]";
	}
	
	

}