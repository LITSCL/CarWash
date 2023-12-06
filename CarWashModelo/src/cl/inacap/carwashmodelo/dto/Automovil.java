package cl.inacap.carwashmodelo.dto;

public class Automovil {
	private String patente;
	private int kilometraje;
	private boolean revisionTecnica;
	private String marca;
	private String modelo;
	private String clienteFK;
	
	public String getPatente() {
		return patente;
	}
	
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	public int getKilometraje() {
		return kilometraje;
	}
	
	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}
	
	public boolean isRevisionTecnica() {
		return revisionTecnica;
	}
	
	public void setRevisionTecnica(boolean revisionTecnica) {
		this.revisionTecnica = revisionTecnica;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getClienteFK() {
		return clienteFK;
	}
	
	public void setClienteFK(String clienteFK) {
		this.clienteFK = clienteFK;
	}
	
	public String toString() {
		return "Patente: " + this.patente + " Kilometraje: " + this.kilometraje + " Revisión Técnica: " + this.revisionTecnica + " Marca: " + this.marca + " Modelo: " + this.modelo + " Cliente: " + this.clienteFK;
	}
}
