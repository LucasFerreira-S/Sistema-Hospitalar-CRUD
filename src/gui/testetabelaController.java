package gui;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

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
import model.dao.PacienteDao;
import model.dao.impl.PacienteDaoJDBC;
import model.entities.Paciente;

public class testetabelaController implements Initializable {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@FXML
	private TableView<Paciente> view;

	@FXML
	private TableColumn<Paciente, Integer> col_id;

	@FXML
	private TableColumn<Paciente, String> col_nome;

	@FXML
	private TableColumn<Paciente, Date> col_datanasc;

	@FXML
	private TableColumn<Paciente, String> col_rg;

	@FXML
	private TableColumn<Paciente, String> col_cpf;

	@FXML
	private TextField Nome;

	@FXML
	private TextField Data;

	@FXML
	private TextField Cpf;

	@FXML
	private TextField Rg;

	@FXML
	private Button Add;

    @FXML
    private Button Delete;

    @FXML
    private TextField IdDel;
    
    @FXML
    private Button Refresh;
    
    @FXML
    private TextField NomeAtualizar;

    @FXML
    private TextField DataNascAtualizar;

    @FXML
    private TextField CpfAtualizar;

    @FXML
    private TextField RgAtualizar;

    @FXML
    private Button Atualizar;

    @FXML
    private TextField idAlt;

    @FXML
    private TextField IdSelect;

    @FXML
    private TextField NameSelect;

    @FXML
    private Button Procurar;
    
	ObservableList<Paciente> listA;

	int index = -1;


	ResultSet rs = null;
	PreparedStatement st = null;


	PacienteDao pDao = DaoFactory.createPacienteDao();
	Paciente pa = new Paciente();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		col_id.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("idPaciente"));
		col_nome.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nomePaciente"));
		col_datanasc.setCellValueFactory(new PropertyValueFactory<Paciente, Date>("dataNasc"));
		col_rg.setCellValueFactory(new PropertyValueFactory<Paciente, String>("RG"));
		col_cpf.setCellValueFactory(new PropertyValueFactory<Paciente, String>("CPF"));

		listA = PacienteDaoJDBC.getDataPaciente();
		view.setItems(listA);
	}

	public void Adicionar() throws ParseException {
		
		String nome = Nome.getText();
		String cpf = Cpf.getText();
		String rg = Rg.getText();
		java.util.Date datanasc = sdf.parse(Data.getText());
		
		Paciente pa = new Paciente(null, nome, datanasc, rg, cpf);	
		pDao.insert(pa);
		
		Refresh();
		
	}
	
	public void Deletar() {
		Integer id = Integer.parseInt(IdDel.getText());
		pDao.deleteById(id);
		
		Refresh();
	}
	
	public void Refresh() {

		listA.clear();
		listA = PacienteDaoJDBC.getDataPaciente();
		view.setItems(listA);
		
	}
	
	public void Atualizar(){
		
		try {
		
			pa.setIdPaciente(Integer.parseInt(idAlt.getText()));
			pa.setNomePaciente(NomeAtualizar.getText());
			pa.setCPF(CpfAtualizar.getText());
			pa.setRG(RgAtualizar.getText());
			pa.setDataNasc(sdf.parse(DataNascAtualizar.getText()));
			
			pDao.update(pa);
			Refresh();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	 public void buscar() {
        ObservableList<Paciente> pacienteBusca = FXCollections.observableArrayList();
        for (int ind = 0; ind < listA.size(); ind++) {
            if (listA.get(ind).getNomePaciente().toLowerCase().contains(IdSelect.getText().toLowerCase())) {
                pacienteBusca.add(listA.get(ind));
            }
        }
       view.setItems(pacienteBusca);
    }
    

	
	
	
}


