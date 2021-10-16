package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

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
import model.dao.DaoFactory;
import model.dao.PacienteDao;
import model.dao.impl.PacienteDaoJDBC;
import model.entities.Paciente;

public class PacienteController implements Initializable {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@FXML
	private TableView<Paciente> Paciente_Table;
	@FXML
	private TableColumn<Paciente, Integer> Col_IdPaciente;
	@FXML
	private TableColumn<Paciente, String> Col_Nome;

	@FXML
	private TableColumn<Paciente, Date> Col_DNasc;

	@FXML
	private TableColumn<Paciente, String> Col_RG;

	@FXML
	private TableColumn<Paciente, String> Col_CPF;

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
    private TextField dataAtualizar;

    @FXML
    private TextField rgAtualizar;

    @FXML
    private TextField cpfAtualizar;

    @FXML
    private TextField IdDel;

    @FXML
    private Button delbtn;

    @FXML
    private TextField NomeAD;

    @FXML
    private TextField rgAD;

    @FXML
    private TextField cpfAD;

    @FXML
    private Button Addbtn;

    @FXML
    private TextField DnascAD;

	@FXML
	void onMenuAction(ActionEvent event) {
		Main.changeScreen("Menu");
	}

	PacienteDao pDao = DaoFactory.createPacienteDao();
	Paciente pa = new Paciente();
	ObservableList<Paciente> listA;

	int index = -1;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement st = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Col_IdPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("idPaciente"));
		Col_Nome.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nomePaciente"));
		Col_DNasc.setCellValueFactory(new PropertyValueFactory<Paciente, Date>("dataNasc"));
		Col_RG.setCellValueFactory(new PropertyValueFactory<Paciente, String>("RG"));
		Col_CPF.setCellValueFactory(new PropertyValueFactory<Paciente, String>("CPF"));
		
		listA = PacienteDaoJDBC.getDataPaciente();
		Paciente_Table.setItems(listA);
	}

	public void Adicionar() {
		try {
			String nome = NomeAD.getText();
			String cpf = cpfAD.getText();
			String rg = rgAD.getText();
			java.util.Date datanasc = sdf.parse(DnascAD.getText());
			Paciente pa = new Paciente(null, nome, datanasc, rg, cpf);
			pDao.insert(pa);
			Refresh();
			NomeAD.setText("");
			cpfAD.setText("");
			rgAD.setText("");
			DnascAD.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Buscar() {
        ObservableList<Paciente> pacienteBusca = FXCollections.observableArrayList();
        for (int ind = 0; ind < listA.size(); ind++) {
            if (listA.get(ind).getNomePaciente().toLowerCase().contains(SelectNome.getText().toLowerCase())) {
                pacienteBusca.add(listA.get(ind));
            }
        }
       Paciente_Table.setItems(pacienteBusca);
       SelectNome.setText("");
	}
	public void Atualizar() {
		try {
			pa.setIdPaciente(Integer.parseInt(idAtualizar.getText()));
			pa.setNomePaciente(nomeAtualizar.getText());
			pa.setCPF(cpfAtualizar.getText());
			pa.setRG(rgAtualizar.getText());
			pa.setDataNasc(sdf.parse(dataAtualizar.getText()));
			pDao.update(pa);
			Refresh();
			idAtualizar.setText("");
			nomeAtualizar.setText("");
			cpfAtualizar.setText("");
			rgAtualizar.setText("");
			dataAtualizar.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void Deletar() {
		Integer id = Integer.parseInt(IdDel.getText());
		pDao.deleteById(id);
		Refresh();
		IdDel.setText("");
	}
	public void Refresh() {
		listA.clear();
		listA = PacienteDaoJDBC.getDataPaciente();
		Paciente_Table.setItems(listA);
	}
}
