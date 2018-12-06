package ejercicio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class ClienteUDP implements Runnable {
@SuppressWarnings("unused")
    private int puertoRemoto;
    private DatagramSocket socketUDP;
    private InetAddress direccionServidor;
    private int puertoLocal;
    private Scanner teclado;
	
//---------------- Atributos para el trabajo con hilos -----------------//
    private HiloClienteUDP hiloCliente = null;
    private Thread hilo                = null;
    private FataInputStream console    = null;
    private DataOutputStream streamOut = null;
	
   
    private ClienteUDP(String direccionDestino, int remoto, int local) throws IOException {
        this.direccionServidor = InetAddress.getByName(direccionDestino);
        this.puertoRemoto = 7077;
        this.puertoLocal = local;
        socketUDP = new DatagramSocket(this.puertoLocal);
        teclado = new Scanner(System.in);
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {        
    	ClienteUDP sesion = new ClienteUDP(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        System.out.println("-- Sesión ClienteUDP inicializada, la dirección local es: " + InetAddress.getLocalHost() + " --");
        sesion.iniciarSesion();
    }
    
    private int iniciarSesion() throws IOException {
        String in;
        while (true) {
            in = teclado.nextLine();
            
            DatagramPacket p = new DatagramPacket(
                in.getBytes(), in.getBytes().length, direccionServidor, 7077);
            
            this.socketUDP.send(p);                    
        }
    }

	public DatagramSocket getUdpSocket() {
		return socketUDP;
	}

	public void setUdpSocket(DatagramSocket socketDatagrama) {
		this.socketUDP = socketDatagrama;
	}

	public InetAddress getServerAddress() {
		return direccionServidor;
	}

	public void setServerAddress(InetAddress ipv4) {
		this.direccionServidor = ipv4;
	}

	public int getPort() {
		return puertoLocal;
	}

	public void setPort(int puerto) {
		this.puertoLocal = puerto;
	}

	public Scanner getScanner() {
		return teclado;
	}

	public void setScanner(Scanner scanner) {
		this.teclado = scanner;
	}

}//Iniciar con java -cp udgv.sockets.css.jar ejercicio.ClienteUDP <IPv4Destino> <PuertoRemoto> <PuertoLocal>
