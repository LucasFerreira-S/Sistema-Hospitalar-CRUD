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
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao{

		private Connection conn;
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		public UsuarioDaoJDBC(Connection conn) {
			this.conn = conn;
		}
		
		@Override
		public void insert(Usuario obj) {
			PreparedStatement st = null;
			try {
				conn = DB.getConnection();
				
				st = conn.prepareStatement(
					"INSERT INTO Med"
						+ "(idusuario, nome, cpf)"
						+ "VALUES "
						+ "(?, ?, ?)");
				
				st.setInt(1, obj.getIdusuario());
				st.setString(2, obj.getNome());
				st.setString(3, obj.getCpf());
				
				int rowsAffected = st.executeUpdate();
				
				if(rowsAffected > 0) {
					ResultSet rs = st.getGeneratedKeys();
					if(rs.next()){
						int id = rs.getInt(1);
						obj.setIdusuario(id);
					}
				}
				else {
					throw new DbException("Erro inesperado! Nenhuma linha foi afetada");
				}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			
		}
			}
		
		@Override
		public void update(Usuario obj) {
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(
						"UPDATE usuario"
						+ "SET idusuario = ?, nome = ?, cpf = ?"
						+ "WHERE"
						+ "(idusuario = ?)");
						
				stmt.setInt(1, obj.getIdusuario());
				stmt.setString(2, obj.getNome());
				stmt.setString(3, obj.getCpf());
				stmt.setInt(4, obj.getIdusuario());
				
					
				stmt.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(stmt);
		}
		}

		@Override
		public void deleteById(Usuario obj) {
			PreparedStatement stmt = null;
			try {
				conn = DB.getConnection();
				
				stmt = conn.prepareStatement(
						"DELETE FROM usuario "
						+ "WHERE "
						+ "idusuario = ?");
				
				stmt.setInt(1, obj.getIdusuario());
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				DB.closeStatement(stmt);
			}
		}

		@Override
		public Usuario findById(Integer idusuario) {
			PreparedStatement st = null;
			ResultSet rs = null;
			
			try {
				st = conn.prepareStatement(
						"SELECT *"
						+ "FROM usuario"
						+ "WHERE usuario.idusuario = ?");
				
				st.setInt(1, idusuario);
				rs = st.executeQuery();
				if(rs.next()) {
					Usuario obj = instantiateUsuario(rs);
					return obj;
				}
				return null;
				
			}
			catch(SQLException e){
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeResultSet(rs);
				DB.closeStatement(st);
			}
		}

		@Override
		public Usuario findByName(String nome) {
			PreparedStatement st = null;
			ResultSet rs = null;
			
			try {
				st = conn.prepareStatement(
						"SELECT usuario.*"
						+ "FROM usuario"
						+ "WHERE usuario.idusuario = ?");
				
				st.setString(1, nome);
				rs = st.executeQuery();
				if(rs.next()) {
					Usuario obj = instantiateUsuario(rs);
					return obj;
				}
				return null;
				
			}
			catch(SQLException e){
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeResultSet(rs);
				DB.closeStatement(st);
			}
		}
		

		public static ObservableList<Usuario> getDataUsuario(){
			ObservableList<Usuario> list = FXCollections.observableArrayList();
			Connection conn = DB.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("Select * from usuario");
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					list.add(new Usuario(null,rs.getString("nome"),(rs.getString("cpf"))));
				}
				
			} catch(Exception e) {
				
			}
			return list;		
			
		}
		
		
		private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
			Usuario obj = new Usuario();
			obj.setIdusuario(rs.getInt("idusuario"));
			obj.setNome(rs.getString("nome"));
			obj.setCpf(rs.getString("cpf"));
			return null;
		}


	}

	

