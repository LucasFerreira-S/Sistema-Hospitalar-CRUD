package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dao.MedicoDao;
import model.entities.Medico;

public class MedicoDaoJDBC implements MedicoDao {

	private Connection conn;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public MedicoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Medico obj) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO medico" + "(nomeMed, area)" + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomeMed());
			st.setString(2, obj.getArea());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdMed(id);
				}
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);

		}
	}

	@Override
	public void update(Medico obj) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("UPDATE medico" + " SET nomeMed = ?, area = ? " + "WHERE idMed = ?");

			stmt.setString(1, obj.getNomeMed());
			stmt.setString(2, obj.getArea());
			stmt.setInt(3, obj.getIdMed());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(stmt);
		}
	}

	@Override
	public void deleteById(Integer idMed) {
		PreparedStatement stmt = null;
		try {
			conn = DB.getConnection();

			stmt = conn.prepareStatement("DELETE FROM medico " + "WHERE " + "idMed = ?");

			stmt.setInt(1, idMed);
			
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(stmt);
		}
	}

	@Override
	public Medico findById(Integer idMed) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT *" + "FROM medico" + "WHERE medico.idMed = ?");

			st.setInt(1, idMed);
			rs = st.executeQuery();
			if (rs.next()) {
				Medico obj = instantiateMedico(rs);
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
	public Medico findByName(String nomeMed) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT medico.*" + "FROM medico" + "WHERE medico.idMed = ?");

			st.setString(1, nomeMed);
			rs = st.executeQuery();
			if (rs.next()) {
				Medico obj = instantiateMedico(rs);
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

	public static ObservableList<Medico> getDataMedico() {

		Connection conn = DB.getConnection();
		ObservableList<Medico> list = FXCollections.observableArrayList();

		try {
			PreparedStatement ps = conn.prepareStatement("select * from medico");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Medico(Integer.parseInt(rs.getString("idMed")), rs.getString("nomeMed"),
						rs.getString("area")));
			}
		} catch (Exception e) {

		}
		return list;

	}

	private Medico instantiateMedico(ResultSet rs) throws SQLException {
		Medico obj = new Medico();
		obj.setIdMed(rs.getInt("idMed"));
		obj.setNomeMed(rs.getString("nomeMed"));
		obj.setArea(rs.getString("area"));
		return null;
	}

}
