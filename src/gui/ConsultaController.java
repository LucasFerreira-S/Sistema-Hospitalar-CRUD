package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import org.w3c.dom.Text;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.ConsultaDao;
import model.dao.DaoFactory;
import model.dao.impl.ConsultaDaoJDBC;
import model.entities.Consulta;

public class ConsultaController implements Initializable {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@FXML
	private TableView<Consulta> Consulta_Table;

	@FXML
	private TableColumn<Consulta, Integer> Col_CodConsulta;

	@FXML
	private TableColumn<Consulta, Integer> Col_Paciente;

	@FXML
	private TableColumn<Consulta, Integer> Col_Medico;

	@FXML
	private TableColumn<Consulta, Date> Col_Data;

	@FXML
	private TableColumn<Consulta, String> Col_Area;

	@FXML
	private TextField idPaciente;

	@FXML
	private Button btnadicionar;

	@FXML
	private TextField idMedico;

	@FXML
	private TextField especializacao;

	@FXML
	private TextField dataconsulta;

	@FXML
	private TextField idConDel;

	@FXML
	private Button btndel;

	@FXML
	private TextField idConsultaBuscar;

	@FXML
	private Button btnbuscar;

	@FXML
	private TextField codConsultaAtualizar;

	@FXML
	private TextField DataAtualizar;

	@FXML
	private Button btnremarcar;

	@FXML
	void onMenuAction(ActionEvent event) {
		Main.changeScreen("Menu");
	}

	ObservableList<Consulta> listA;

	ConsultaDao cDao = DaoFactory.createConsultaDao();
	Consulta cons = new Consulta();

	int index = -1;
	Connection conn = null;

	ResultSet rs = null;

	PreparedStatement st = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Col_CodConsulta.setCellValueFactory(new PropertyValueFactory<Consulta, Integer>("codConsulta"));
		Col_Paciente.setCellValueFactory(new PropertyValueFactory<Consulta, Integer>("idPaciente"));
		Col_Medico.setCellValueFactory(new PropertyValueFactory<Consulta, Integer>("idMedico"));
		Col_Area.setCellValueFactory(new PropertyValueFactory<Consulta, String>("area"));
		Col_Data.setCellValueFactory(new PropertyValueFactory<Consulta, Date>("data"));

		listA = ConsultaDaoJDBC.getDataConsulta();
		Consulta_Table.setItems(listA);
	}
	
	public void Adicionar() {
		try {
			Integer idPac = Integer.parseInt(idPaciente.getText());
			Integer idMed = Integer.parseInt(idMedico.getText());
			java.util.Date datadaConsulta = sdf.parse(dataconsulta.getText());
			String area = especializacao.getText();
			Consulta cos = new Consulta(null, idPac, idMed, datadaConsulta, area);
			cDao.insert(cos);
			Refresh();
			idPaciente.setText("");
			idMedico.setText("");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Refresh() {
		listA.clear();
		listA = ConsultaDaoJDBC.getDataConsulta();
		Consulta_Table.setItems(listA);
		
	}

	public void Deletar() {
		Integer id = Integer.parseInt(idConDel.getText());
		cDao.deleteById(id);
	Refresh();
	idConDel.setText("");
	}

	public void Buscar() {
 
		ObservableList<Consulta> consultaBusca = FXCollections.observableArrayList();
        for (int ind = 0; ind < listA.size(); ind++) {
            if (listA.get(ind).getCodConsulta().equals(Integer.parseInt(idConsultaBuscar.getText()))){
                consultaBusca.add(listA.get(ind));
            }
        }
       Consulta_Table.setItems(consultaBusca);
       idConsultaBuscar.setText("");
	}
	
	public void Remarcar() {
		try {
			cons.setCodConsulta(Integer.parseInt(codConsultaAtualizar.getText()));
			cons.setData(sdf.parse(DataAtualizar.getText()));
			cDao.update(cons);
			Refresh();
			codConsultaAtualizar.setText("");
			DataAtualizar.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}