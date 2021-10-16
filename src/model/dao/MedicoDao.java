package model.dao;

import model.entities.Medico;

public interface MedicoDao {
	void insert(Medico obj);
	void update(Medico obj);
	void deleteById(Integer idMed);
	Medico findById(Integer idMed);
	Medico findByName(String nomeMed);
	}


