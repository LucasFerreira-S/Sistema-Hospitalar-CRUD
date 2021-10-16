package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Stage stage;
	
	private static Scene viewScene;
	private static Scene MenuScene;
	private static Scene PacienteScene;
	private static Scene MedicoScene;
	private static Scene ConsultaScene;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		try {
			
			stage = primaryStage;
			Parent fxmlView = FXMLLoader.load(getClass().getResource("/gui/view.fxml"));
			viewScene = new Scene(fxmlView);
			
			Parent fxmlMenu = FXMLLoader.load(getClass().getResource("/gui/MenuView.fxml"));
			MenuScene = new Scene(fxmlMenu);
			
			Parent fxmlPaciente = FXMLLoader.load(getClass().getResource("/gui/PacienteView.fxml"));
			PacienteScene = new Scene(fxmlPaciente);
			
			Parent fxmlMedico = FXMLLoader.load(getClass().getResource("/gui/MedicoView.fxml"));
			MedicoScene = new Scene(fxmlMedico);
			
			Parent fxmlConsulta = FXMLLoader.load(getClass().getResource("/gui/ConsultaView.fxml"));
			ConsultaScene = new Scene(fxmlConsulta);
			
			
			stage.setTitle("Login");
			primaryStage.setScene(viewScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void changeScreen(String scr) {
		switch(scr) {
		case "view":

			stage.setScene(viewScene);
			break;
		case "Menu":
			stage.setScene(MenuScene);
			stage.setTitle("Menu");
			break;
		case "Paciente":
			stage.setScene(PacienteScene);
			stage.setTitle("Paciente");
			stage.setMinWidth(900);
			stage.setMinHeight(547);
			break;
		case "Medico":
			stage.setScene(MedicoScene);
			stage.setTitle("Medico");
			stage.setMinWidth(900);
			stage.setMinHeight(547);
			break;
		case "Consulta":
			stage.setScene(ConsultaScene);
			stage.setTitle("Consulta");
			stage.setMinWidth(900);
			stage.setMinHeight(547);
			break;
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
