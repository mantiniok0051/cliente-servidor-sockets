package ejercicio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;


public class ServidorUDP {

    private DatagramSocket socketUDP;    
    private int puertoLocal;
 
    public ServidorUDP(int port) throws SocketException, IOException {
        this.puertoLocal = port;
        this.socketUDP = new DatagramSocket(this.puertoLocal);
    }
    
    public ServidorUDP(String IPv4, int puerto) throws SocketException, IOException {
    	this.puertoLocal = puerto;
    	InetSocketAddress address = new InetSocketAddress(IPv4, puertoLocal);
    	this.socketUDP = new DatagramSocket(address);
    }
    
    
    private void iniciarServicio(String IPv4) throws Exception {
    	
        System.out.println("-- Servicio en linea, la dirección local es: " + IPv4 + ":" + puertoLocal + " --");
        String mensaje;
        
        while (true) {
            
            byte[] buf = new byte[256];
            DatagramPacket packete = new DatagramPacket(buf, buf.length);
            
            // blocks until a packet is received
            socketUDP.receive(packete);
            mensaje = new String(packete.getData()).trim();
            
            System.out.println(
                "Mesaje de " + packete.getPort() + "@" + packete.getAddress().getHostAddress() + "> " + mensaje);
        }
    }
    
    public static void main(String[] args) throws Exception {
    	ServidorUDP SesionUDP;
    	String IPv4;
    	if (args.length == 1) {
    		SesionUDP = new ServidorUDP(Integer.parseInt(args[0]));
    		IPv4 = InetAddress.getLocalHost().toString();
		} else {
			SesionUDP = new ServidorUDP(args[0], Integer.parseInt(args[1]));
			IPv4 = args[0];
		}
    	
    	SesionUDP.iniciarServicio(IPv4);
       }
    
}
//Iniciar con java -cp udgv.sockets.css.ejercicio.jar ejercicio.ServidorUDP <PuertoLocal>
//Iniciar con java -cp udgv.sockets.css.ejercicio.jar ejercicio.ServidorUDP <IPv4Local> <PuertoLocal>

