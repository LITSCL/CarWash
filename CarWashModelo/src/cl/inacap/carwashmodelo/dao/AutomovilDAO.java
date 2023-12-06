package cl.inacap.carwashmodelo.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.carwashmodelo.dto.Automovil;

public class AutomovilDAO {
	private static List<Automovil> automoviles = new ArrayList<>(); //La lista es est�tica para que no se creen mas listas.
	
	public void save(Automovil a) {
		automoviles.add(a);
	}
	
	public List<Automovil> getAll() {
		return automoviles;
	}
	
	public List<Automovil> find(String clienteRut) {
		List<Automovil> automovilesCliente = new ArrayList<Automovil>();
		
		for (Automovil a : automoviles) {
			if (a.getClienteFK().equals(clienteRut)) {
				automovilesCliente.add(a);
			}
		}
		
		if (automovilesCliente.size() == 0) {
			return automoviles;
		}
		else {
			return automovilesCliente;
		}		
	}
}
