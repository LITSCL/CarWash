package cl.inacap.carwashmodelo.dto;

public class Cliente {
	private String nombre;
	private String apellido;
	private String rut;
	private int telefono;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public String toString() {
		String telefonoString = Integer.toString(telefono);
		return "Nombre: " + this.nombre + " Apellido: " + this.apellido + " RUT: " + this.rut + " Telefono: " + telefonoString;
	}
	
}
