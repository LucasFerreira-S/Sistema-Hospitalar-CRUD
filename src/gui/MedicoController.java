package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.DaoFactory;
import model.dao.MedicoDao;
import model.dao.impl.MedicoDaoJDBC;
import model.entities.Medico;

public class MedicoController implements Initializable {

	@FXML
	private TableView<Medico> Medico_Table;

	@FXML
	private TableColumn<Medico, Integer> Col_IdMedico;

	@FXML
	private TableColumn<Medico, String> Col_Nome;

	@FXML
	private TableColumn<Medico, String> Col_Esp;

	@FXML
	private Button btnBusca;

	@FXML
	private TextField SelectNome;

	@FXML
	private TextField idAtualizar;

	@FXML
	private Button btnAtualizar;

	@FXML
	private TextField nomeAtualizar;

	@FXML
	private TextField espAtualizar;

	@FXML
	private TextField IdDel;

	@FXML
	private Button delbtn;

	@FXML
	private TextField NomeAD;

	@FXML
	private TextField EspAD;

	@FXML
	private Button Addbtn;

	ObservableList<Medico> listA;

	int index = -1;
	
	Medico me = new Medico();
	MedicoDao mDao = DaoFactory.createMedicoDao();
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement st = null;
	
	@FXML
	public void onMenuAction() {
		Main.changeScreen("Menu");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Col_IdMedico.setCellValueFactory(new PropertyValueFactory<Medico, Integer>("idMed"));
		Col_Nome.setCellValueFactory(new PropertyValueFactory<Medico, String>("nomeMed"));
		Col_Esp.setCellValueFactory(new PropertyValueFactory<Medico, String>("area"));

		listA = MedicoDaoJDBC.getDataMedico();
		Medico_Table.setItems(listA);

	}

	public void Atualizar() {

		try {
			me.setIdMed(Integer.parseInt(idAtualizar.getText()));
			me.setNomeMed(nomeAtualizar.getText());
			me.setArea(espAtualizar.getText());
			mDao.update(me);
			Refresh();
			idAtualizar.setText("");
			nomeAtualizar.setText("");
			espAtualizar.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Adicionar() {
		try {
			String nome = NomeAD.getText();
			String area = EspAD.getText();
			Medico me = new Medico(null, nome, area);
			mDao.insert(me);
			Refresh();
			NomeAD.setText("");
			EspAD.setText("");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Buscar() {
        ObservableList<Medico> medicoBusca = FXCollections.observableArrayList();
        for (int ind = 0; ind < listA.size(); ind++) {
            if (listA.get(ind).getNomeMed().toLowerCase().contains(SelectNome.getText().toLowerCase())) {
                medicoBusca.add(listA.get(ind));
            }
        }
       Medico_Table.setItems(medicoBusca);
       SelectNome.setText("");
	}

	public void Deletar() {
		Integer id = Integer.parseInt(IdDel.getText());
		mDao.deleteById(id);
		Refresh();
		IdDel.setText("");
	}

	public void Refresh() {
		listA.clear();
		listA = MedicoDaoJDBC.getDataMedico();
		Medico_Table.setItems(listA);
	}

}