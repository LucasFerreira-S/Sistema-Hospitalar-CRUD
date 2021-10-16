package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {
	
	@FXML 
	private Label Paciente;
	
	@FXML 
	private Label Medico;
	
	@FXML
	private Label Consulta;
	
	@FXML
	public void onPacienteAction() {
		Main.changeScreen("Paciente");
		
	}
	
	@FXML
	public void onMedicoAction() {
		Main.changeScreen("Medico");
	}
	
	@FXML
	public void onConsultaAction() {
		Main.changeScreen("Consulta");
	}

}
