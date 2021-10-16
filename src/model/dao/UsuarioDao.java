package model.dao;

import model.entities.Usuario;

public interface UsuarioDao {
	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteById(Usuario obj);
	Usuario findById(Integer idusuario);
	Usuario findByName(String nome);
}
