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
import model.dao.PacienteDao;
import model.entities.Paciente;

public class PacienteDaoJDBC implements PacienteDao {

	private Connection conn;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public PacienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Paciente obj) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"INSERT INTO paciente" + "(nomePaciente, dataNasc, RG, CPF)" + "VALUES " + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomePaciente());
			st.setDate(2, new java.sql.Date(obj.getDataNasc().getTime()));
			st.setString(3, obj.getRG());
			st.setString(4, obj.getCPF());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdPaciente(id);
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
	public void update(Paciente obj) {
		PreparedStatement stmt = null;
		try {

			stmt = conn.prepareStatement("UPDATE paciente" + " SET nomePaciente = ?, dataNasc = ?, RG = ?, CPF = ? "
					+ "WHERE idPaciente = ?");

			stmt.setString(1, obj.getNomePaciente());
			stmt.setDate(2, new java.sql.Date(obj.getDataNasc().getTime()));
			stmt.setString(3, obj.getRG());
			stmt.setString(4, obj.getCPF());
			stmt.setInt(5, obj.getIdPaciente());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(stmt);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement stmt = null;
		try {
			conn = DB.getConnection();

			stmt = conn.prepareStatement("DELETE FROM paciente " + "WHERE " + "idPaciente = ?");

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(stmt);
		}
	}

	@Override
	public Paciente findById(Integer idPaciente) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM paciente WHERE idPaciente = ?");

			st.setInt(1, idPaciente);
			rs = st.executeQuery();
			if (rs.next()) {
				Paciente obj = instantiatePaciente(rs);
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
	public Paciente findByName(String nomePaciente) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT paciente.*" + "FROM paciente" + " WHERE idPaciente = ?");

			st.setString(1, nomePaciente);
			rs = st.executeQuery();
			if (rs.next()) {
				Paciente obj = instantiatePaciente(rs);
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

	public static ObservableList<Paciente> getDataPaciente() {

		Connection conn = DB.getConnection();
		ObservableList<Paciente> list = FXCollections.observableArrayList();

		try {
			PreparedStatement ps = conn.prepareStatement("select * from paciente");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Paciente(Integer.parseInt(rs.getString("idPaciente")), rs.getString("nomePaciente"),
						rs.getDate("dataNasc"), rs.getString("RG"), rs.getString("CPF")));
			}
		} catch (Exception e) {

		}
		return list;

	}

	private Paciente instantiatePaciente(ResultSet rs) throws SQLException {
		Paciente obj = new Paciente();
		obj.setIdPaciente(rs.getInt("idPaciente"));
		obj.setNomePaciente(rs.getString("nomePaciente"));
		obj.setCPF(rs.getString("CPF"));
		obj.setRG(rs.getString("RG"));
		return null;
	}

}
