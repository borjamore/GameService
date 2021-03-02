package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class IniciarServidor {



    public static void main(String[] args) {
        IniciarServidor servidor = new IniciarServidor();
        servidor.iniciar();
    }


    //Aqui generamos un bucle de escucha infinito que lanzara un hilo para gestionar cada peticion
    private void iniciar() {
        System.out.println("Creando socket servidor");
        Socket newSocket = null;
        try (ServerSocket serverSocket = new ServerSocket();) {
            System.out.println("Realizando el bind");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            // asigna el socket a una dirección y puerto
            serverSocket.bind(addr);
            System.out.println("Aceptando conexiones");
            while (true) {
                newSocket = serverSocket.accept();
                System.out.println("Conexion recibida");
                Peticion p = new Peticion(newSocket);
                Thread hilo = new Thread(p);
                hilo.start();
                System.out.println("Esperando nueva conexión");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (newSocket != null) {
                newSocket.close();
            }
        }
    }
}




