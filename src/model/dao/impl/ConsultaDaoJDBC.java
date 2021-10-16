package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dao.ConsultaDao;
import model.entities.Consulta;

public class ConsultaDaoJDBC implements ConsultaDao{
	
	private Connection conn;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public ConsultaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	
	@Override
	public void insert(Consulta obj) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"INSERT INTO consulta"
					+ "(Paciente, Medico, Data, Especializacao)"
					+ " VALUES (?, ?, ?, ?)");
			
			st.setInt(1, obj.getIdPaciente());
			st.setInt(2, obj.getIdMedico());
			st.setDate(3, new java.sql.Date(obj.getData().getTime()));
			st.setString(4, obj.getArea());
		    st.executeUpdate();



		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);

		}
	}

	@Override
	public void update(Consulta obj) {
		PreparedStatement stmt = null;
		try {

			stmt = conn.prepareStatement("UPDATE consulta" + " SET Data = ? "
					+ "WHERE CodConsulta = ?");

			stmt.setDate(1, new java.sql.Date(obj.getData().getTime()));
			stmt.setInt(2, obj.getCodConsulta());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(stmt);
		}
		
	}

	@Override
	public void deleteById(Integer idConsulta) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			conn = DB.getConnection();

			stmt = conn.prepareStatement("DELETE FROM consulta " + "WHERE " + "CodConsulta = ?");

			stmt.setInt(1, idConsulta);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(stmt);
		}
	}
	

	@Override
	public Consulta findById(Integer idConsulta) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM paciente WHERE CodConsulta = ?");

			st.setInt(1, idConsulta);
			rs = st.executeQuery();
			if (rs.next()) {
			Consulta obj = instantiateConsulta(rs);
				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	
	@Override
	public Consulta findByName(String nomeMed) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ObservableList<Consulta> getDataConsulta() {

		Connection conn = DB.getConnection();
		ObservableList<Consulta> list = FXCollections.observableArrayList();

		try {
			PreparedStatement ps = conn.prepareStatement("select * from consulta");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Consulta(rs.getInt("CodConsulta"), rs.getInt("Paciente"), rs.getInt("Medico"), rs.getDate("Data"), rs.getString("Especializacao")));
			}
		} catch (Exception e) {

		}
		return list;

	}
	
	private Consulta instantiateConsulta(ResultSet rs) throws SQLException {
		Consulta obj = new Consulta();
		obj.setCodConsulta(rs.getInt("CodConsulta"));
		obj.setIdPaciente(rs.getInt("Paciente"));
		obj.setIdMedico(rs.getInt("Medico"));
		obj.setData(rs.getDate("Data"));
		obj.setArea(rs.getString("Especializacao"));
		return null;
	}
	
}
