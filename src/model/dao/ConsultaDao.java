package model.dao;

import model.entities.Consulta;

public interface ConsultaDao {
		void insert(Consulta obj);
		void update(Consulta obj);
		void deleteById(Integer idConsulta);
		Consulta findById(Integer idConsulta);
		Consulta findByName(String nomeConsulta);
		}
