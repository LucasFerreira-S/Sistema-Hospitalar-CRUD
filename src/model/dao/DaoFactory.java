package model.dao;

import db.DB;
import model.dao.impl.ConsultaDaoJDBC;
import model.dao.impl.MedicoDaoJDBC;
import model.dao.impl.PacienteDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {
	public static PacienteDao createPacienteDao() {
		return new PacienteDaoJDBC(DB.getConnection());
	}
	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
	public static MedicoDao createMedicoDao() {
		return new MedicoDaoJDBC(DB.getConnection());
	}
	public static ConsultaDao createConsultaDao() {
		return new ConsultaDaoJDBC(DB.getConnection());
	}
}
