package cl.inacap.carwashmodelo.dao;

import cl.inacap.carwashmodelo.dto.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
	private static List<Cliente> clientes = new ArrayList<>(); //La lista es estática para que no se creen mas listas.
	
	public void save(Cliente c) {
		clientes.add(c);
	}
	
	public List<Cliente> getAll() {
		return clientes;
	}
}
