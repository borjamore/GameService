package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
    public static void main(String[] args) throws IOException {

        System.out.println("Server starting...");
        Socket newSocket = null;
        try(ServerSocket serverSocket = new ServerSocket()){
            System.out.println("binding...");
            InetSocketAddress address = new InetSocketAddress("localhost",6060);
            serverSocket.bind(address);
            System.out.println("Waiting petitions");
            //Create a loop to wait petitions
            while (true) {
                newSocket = serverSocket.accept();
                System.out.println("Connection done");
                AcceptingPetitions accept = new AcceptingPetitions(newSocket);
                Thread thread = new Thread(accept);
                thread.start();
                System.out.println("Waiting new connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(newSocket != null){
                newSocket.close();
            }
        }
    }
}


