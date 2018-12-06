package ejemplo;

import java.util.concurrent.TimeUnit;
import ejemplo.Conex_CLI;
import ejemplo.Conex_SVR;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Main {
	private static Conex_SVR svr;
	private static Conex_CLI cli;
	public  Scanner teclado;
	
	public static void main(String[] args) {
		
		
		if (args.length < 1 || args.length > 1){
			System.out.println("Iniciciando como Servidor");
			sesionServidor();
		} else {
			
				System.out.println("Iniciando sesión como Cliente");
				sesionCliente();
				
		}
		
		
	}

	
	public static void sesionCliente(){
		cli = new Conex_CLI();
		cli.initClient();
	} 
	
	public static  void sesionServidor() {		
		 svr =  new Conex_SVR();
		 svr.initServer();
	}
}
// iniciar desde consola con: java -cp udgv.sockets.css.ejemplo.jar ejemplo.Main 