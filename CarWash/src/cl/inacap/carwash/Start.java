package cl.inacap.carwash;

import cl.inacap.carwashmodelo.dao.AutomovilDAO;
import cl.inacap.carwashmodelo.dao.ClienteDAO;
import cl.inacap.carwashmodelo.dto.Automovil;
import cl.inacap.carwashmodelo.dto.Cliente;

import java.util.List;
import java.util.Scanner;


public class Start {
	static ClienteDAO daoCliente = new ClienteDAO(); //Objeto de tipo ClienteDAO.
	static AutomovilDAO daoAutomovil = new AutomovilDAO(); //Objeto de tipo AutomovilDAO.
	static Scanner sc = new Scanner(System.in);
	static String compruebaLetras[] = {"A", "B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "�", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
	static String compruebaNumeros[] = {"0","1","2","3","4","5","6","7","8","9"}; //validar como lenngth-1 , -2, -3 (el -1 es la ultima posicion)
	static String compruebaRut[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "k"};
	static String patente;
	static int kilometraje;
	static boolean revisionTecnica;
	static String marca, modelo;
	static boolean salirMenuMarca;
	static String nombre, apellido, rut;
	static String clienteRut;
	static int rutInt;
	static String cadenaRut;
	static String stringTelefono;
	static int telefono;

	//Menu de navegaci�n.
	public static boolean menu() { 
		boolean continuar = true;
		System.out.println("1. Registrar Automovil");
		System.out.println("2. Registro de Cliente");
		System.out.println("3. Mostrar Automoviles");
		System.out.println("4. Mostrar Clientes");
		System.out.println("5. Buscar Automovil");
		System.out.println("0. Salir");
		switch (sc.nextLine().trim()) {
		case "1":
			registrarAutomovil();
			break;
		case "2":
			registroDeCliente();
			break;
		case "3":
			mostrarAutomoviles();
			break;
		case "4":
			mostrarClientes();
			break;
		case "5":
			buscarAutomovil();
			break;
		case "0":
			continuar = false;
			break;
		default:
			System.out.println("Opcion incorrecta");
			break;
		}
		return continuar;
	}
		
	//Proceso de ingreso y validaci�n de datos.
	public static void registrarAutomovil() {
			
		//Pide ingresar la patente del automovil (Tambi�n es validado el ingreso de datos).
		int contador = 0;
		do {
			
			contador = 0;
			do {
				System.out.println("Ingrese la Patente del automovil, ejemplo: CCCC-99");
				patente = sc.nextLine().trim();
				
				if (patente.length() < 7) {
					System.out.println("La patente es demasiado corta, ingresela nuevamente");
				}
				if (patente.length() > 7) {
					System.out.println("La patente es demasiado larga, ingresela nuevamente");
				}
			} while(patente.length() != 7);

			
			//Se valida que los primeros 4 caracteres sean letras.
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < compruebaLetras.length; j++) {
					if (compruebaLetras[j].equals(Character.toString(patente.charAt(i)))) {
						contador++;
					}
				}
			}
			
			//Se valida el quinto caracter sea un gui�n.
			if ("-".equals(Character.toString(patente.charAt(4)))){
				contador++;
			}
			
			//Se validan que los dos ultimos indices del String sean numeros.
			for (int i = 5; i < 7; i++) {
				for (int j = 0; j < compruebaNumeros.length; j++) {
					if (compruebaNumeros[j].equals(Character.toString(patente.charAt(i)))){
						contador++;
					}
				}
			}
			
			if (contador != 7) {
				System.out.println("La patente que ingresaste no es valida, ingresala nuevamente");
			}
			
		} while (contador != 7);
		
		//Pide ingresar el kilometraje del automovil (Tambi�n es validado el ingreso de datos).
		do {
			
			try {
				System.out.println("Ingrese el kilometraje del automovil");
				kilometraje = Integer.parseInt(sc.nextLine().trim());
				if (kilometraje < 0) {
					System.out.println("El kilometraje que ingresaste no debe ser menor que 0, ingresalo nuevamente");
				}
					
			} catch (Exception ex) {
				kilometraje = -1;
				System.out.println("El kilometraje ingresado no es valido, ingresa el kilometraje nuevamente");
			}
			
		} while (kilometraje<0);

		//Pide ingresar la revisi�n t�cnica del automovil (Tambi�n es validado el ingreso de datos).
		String respuestaRevision;
		boolean respondido = false;
		do {
			System.out.println("�la revisi�n t�cnica del automovil est� al d�a? (Si/No)");
			respuestaRevision = sc.nextLine().trim();
				
			if (respuestaRevision.equalsIgnoreCase("Si")) {
				revisionTecnica = true;
				respondido = true;
			}
			if (respuestaRevision.equalsIgnoreCase("No")) {
				revisionTecnica = false;
				respondido = true;
			}
		} while (respondido != true);
		
		//Pide ingresar la marca del autom�vil (Tambi�n es validado el ingreso de datos).
		while (salirMenuMarca == false) {
			System.out.println("Seleccione una marca");
			System.out.println("1. Chevrolet");
			System.out.println("2. Hyundai");
			System.out.println("3. Toyota");
			System.out.println("4. Mercedes Benz");
			switch (sc.nextLine().trim()) {
			case "1":
				marca = "Chevrolet";
				salirMenuMarca = true;
				break;
			case "2":
				marca = "Hyundai";
				salirMenuMarca = true;
				break;
			case "3":
				marca = "Toyota";
				salirMenuMarca = true;
				break;
			case "4":
				marca = "Mercedes Benz";
				salirMenuMarca = true;
				break;
			default:
				System.out.println("Usted Eligi� una opci�n no valida, eliga nuevamente");
				salirMenuMarca = false;
			}
		}
		
		modelo = "";
		
		while (modelo.isEmpty() == true) {
			System.out.println("Ingrese el modelo");
			modelo = sc.nextLine().trim();
		}
		
		List<Cliente> clientes = daoCliente.getAll();
		
		int clienteSeleccionado = -1;
		contador = 0;
		while (clienteSeleccionado == -1) {
			try {
				System.out.println("Seleccione el due�o del automovil");
				
				for (Cliente c : clientes) {
					contador++;
					System.out.println(contador + ". " + c.toString());
				}
				
				clienteSeleccionado = Integer.parseInt(sc.nextLine().trim());
				
				clienteRut = (clientes.get(clienteSeleccionado - 1)).getRut();
				
			}
			catch (Exception ex) {
				contador = 0;
				clienteSeleccionado = -1;
			}
		}	
			
		//Creaci�n del objeto.
		Automovil automovil = new Automovil();
		automovil.setPatente(patente);
		automovil.setKilometraje(kilometraje);
		automovil.setRevisionTecnica(revisionTecnica);
		automovil.setMarca(marca);
		automovil.setModelo(modelo);
		automovil.setClienteFK(clienteRut);
		
		daoAutomovil.save(automovil);
	}
			
	public static void registroDeCliente() {
		
		//Ingreso de del nombre del cliente y validaci�n de datos.
		do {
			System.out.println("Ingrese el nombre del cliente");
			nombre = sc.nextLine().trim();
			if (nombre.length() < 10) {
				System.out.println("El nommbre debe contener como m�nimo 10 caracteres, ingrese el nombre nuevamente");
			}
		} while (nombre.length() < 10);
		
		//Ingreso de del apellido del cliente y validaci�n de datos.
		do {
			System.out.println("Ingrese el apellido del cliente");
			apellido = sc.nextLine().trim();
			if (apellido.length() < 10) {
				System.out.println("El apellido debe contener como m�nimo 10 caracteres, ingrese el apellido nuevamente");
			}
		} while (apellido.length() < 10);
		
		
		//Ingreso de del rut del cliente y validaci�n de datos.
		boolean seguir = false;
		do {
			
			System.out.println("Ingrese el rut del cliente");
			rut = sc.nextLine().trim();
			
			if (rut.length() < 9) {
				System.out.println("El rut es demasiado corto, ingreselo nuevamente");
			}
			if (rut.length() > 10) {
				System.out.println("El rut es demasiado largo, ingreselo nuevamente");
			}
					
			if (rut.length() == 9) {
				if ("-".equals(Character.toString(rut.charAt(7))) == true) { //Si el rut tiene 7 digitos, se comprueba que todos sean n�meros mediante una excepci�n.
					
					try {
						cadenaRut = rut.substring(0, 7); //En esta instrucci�n se almacenan los primeros 7 digitos.
						rutInt = Integer.parseInt(cadenaRut); //En esta instrucci�n se convierten los n�meros almacenados en la variable RutInt y se almacenan en una variable de tipo int (Si el programa no captura un error es porque todos los digitos eran n�meros).
						
						for (int j = 0; j < compruebaRut.length; j++) {
							if (compruebaRut[j].equalsIgnoreCase(Character.toString(rut.charAt(8)))) { //En esta instrucci�n se comprueba que el ultimo digito del rut sea correcto.
								seguir = true;
								break;
							}
						}
						
					} catch (Exception ex) {
						System.out.println("El rut ingresado no es valido, ingreselo nuevamente");
						cadenaRut = "";
						rutInt = 1;
						seguir = false;
					}
				}
			}
			
			if (rut.length() == 10){
				if ("-".equals(Character.toString(rut.charAt(8))) == true) { //Si el rut tiene 8 digitos, se comprueba que todos sean n�meros mediante una excepci�n.
					
					try {
						cadenaRut = rut.substring(0, 8); //En esta instrucci�n se almacenan los primeros 8 digitos.
						rutInt = Integer.parseInt(cadenaRut); //En esta instrucci�n se convierten los n�meros almacenados en la variable RutInt y se almacenan en una variable de tipo int (Si el programa no captura un error es porque todos los digitos eran n�meros).
						
						for (int j = 0; j < compruebaRut.length; j++) {
							if (compruebaRut[j].equalsIgnoreCase(Character.toString(rut.charAt(9)))) { //En esta instrucci�n se comprueba que el ultimo digito del rut sea correcto.
								seguir = true;
								break;
							}
						}
						
					} catch (Exception ex) {
						System.out.println("El rut ingresado no es valido, ingreselo nuevamente");
						cadenaRut = "";
						rutInt = 1;
						seguir = false;
					}
				}
			}
			
		} while (seguir == false);
		
		//Ingreso de del telefono del cliente y validaci�n de datos (Se validan los datos mediante la captura de una excepci�n).
		boolean validado = false;
		do {
			
			do {
				
				System.out.println("Ingrese el telefono del cliente");
				stringTelefono = sc.nextLine().trim();
				
				if (stringTelefono.length() < 9) {
					System.out.println("El n�mero que ingresaste tiene menos de 9 d�gitos, ingresalo nuevamente");
				}
				
				if (stringTelefono.length() > 10) {
					System.out.println("El n�mero que ingresaste tiene m�s de 10 d�gitos, ingresalo nuevamente");
				}
				
			} while (stringTelefono.length() != 10 && stringTelefono.length() != 9);
			
			try {
				telefono = Integer.parseInt(stringTelefono); //Si el programa se cae es porque no pudo convertir el tipo de dato (El usuario ingreso caracteres, los cuales no se pueden almacenar en una variable de tipo int).
				validado = true; //Si el programa no se cae se lee esta l�nea.
			} catch (Exception ex) {
				telefono = 0;
				System.out.println("El numero que ingresaste contiene caracteres, ingresalo nuevamente");
			}
			
		} while (validado == false); //Repite mientras la variable se falsa.
		
		//Creaci�n del objeto.
		Cliente Cliente = new Cliente();
		Cliente.setNombre(nombre);
		Cliente.setApellido(apellido);
		Cliente.setRut(rut);
		Cliente.setTelefono(telefono);
		
		daoCliente.save(Cliente); //En esta instrucci�n se almacena el objeto anteriormente creado en la lista.
		
	}
		
	public static void mostrarAutomoviles() {
		daoAutomovil.getAll().forEach(System.out::println); //Esta instrucci�n imprime la lista que almacena objetos de tipo Automovil.
	}
		
	public static void mostrarClientes() {
		
		daoCliente.getAll().forEach(System.out::println); //Esta instrucci�n imprime la lista que almacena objetos de tipo Cliente.
		
	}
		
	public static void buscarAutomovil() {
		System.out.println("Ingrese el rut del cliente");
		
		String rut = sc.nextLine().trim();
		
		List<Automovil> automoviles = daoAutomovil.find(rut);
		automoviles.forEach(System.out::println);
	}
		
	public static void main(String[] args) {
		while (menu()); //Se repite el menu hasta que deje de ser true.
	}

}
