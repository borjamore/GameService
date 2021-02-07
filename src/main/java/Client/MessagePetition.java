package Client;

import Entity.Player;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MessagePetition {

        public void sendPetition(Player player){

        System.out.println("Creating client Socket");
        try(Socket clientSocket = new Socket()){
            System.out.println("Attempting to connect...");
            InetSocketAddress address = new InetSocketAddress("localhost",6060);
            clientSocket.connect(address);

            try(OutputStream os = clientSocket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                PrintWriter pw = new PrintWriter(osw)){


                System.out.println("Sending Player");
                //mandamos nickname
                pw.print(player.getNickname());
                //mandamos tipo de juego
                pw.print(player.getGametype());
                //mandamos la ip
                pw.print(player.getIp());
                //mandamos el puerrto
                pw.print(player.getPort());
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


