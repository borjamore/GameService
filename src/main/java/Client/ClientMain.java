package Client;

import java.util.Scanner;

public class ClientMain{
    /**
     * Starting thread creations to send messages
     * @param args main args
     */
    public static void main(String[] args) {

            MessagePetition play = new MessagePetition();
            CreatePlayer player = new CreatePlayer();

           try(Scanner sc = new Scanner(System.in)){
            play.sendPetition(player.newPlayer(sc));

        }
    }}



