package model.dao;

import model.entities.Paciente;

public interface PacienteDao {
	void insert(Paciente obj);
	void update(Paciente obj);
	void deleteById(Integer id);
	Paciente findById(Integer idPaciente);
	Paciente findByName(String nomePaciente);


}


