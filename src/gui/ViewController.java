package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import db.DB;
import db.DbException;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entities.Usuario;

public class ViewController {

	@FXML
	private TextField txtNome;

	@FXML
	private DatePicker dateNasc;

	@FXML
	private PasswordField psdCpf;

	@FXML
	private Button btTest;

	@FXML
	public void onbtTestAction() {

		

		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;

		try {

			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from usuario");

			String nome = txtNome.getText();
			String cpf = psdCpf.getText();

			Usuario usu = new Usuario(nome, cpf);

			while (rs.next()) {
				if (rs.getString("nome").equals(usu.getNome()) && rs.getString("cpf").equals(usu.getCpf())) {
					System.out.println("Logado com Sucesso!");
					Main.changeScreen("Menu");
				} else {
					Alerts.showAlert("Falha ao Conectar", "Nome ou senha incorretos(as)", null, AlertType.ERROR);
				}
			}
		} catch (DbException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);

		}
	}
}