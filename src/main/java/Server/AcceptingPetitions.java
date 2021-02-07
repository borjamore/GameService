package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class AcceptingPetitions implements Runnable {

    Socket socket;
    public AcceptingPetitions(Socket socket){
        this.socket= socket;
    }
    public void run(){
        try(InputStream is = socket.getInputStream();
            InputStreamReader imps = new InputStreamReader(is);
            BufferedReader bfr = new BufferedReader(imps)){
            String msg = bfr.readLine();
            ArrayList<String> player = new ArrayList<>();

            while (msg != null) {
               player.add(msg);
                System.out.println("Received: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
