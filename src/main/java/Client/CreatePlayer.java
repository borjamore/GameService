package Client;

import Entity.Player;

import java.util.Scanner;

public class CreatePlayer {


    public Player newPlayer(Scanner sc){
        Player player = new Player();

        //Nuevo jugador
        player.setNewgame(true);

        System.out.println("Introduce un Nickname");
        player.setNickname("Borjabubu99");
        //player.setNickname(sc.nextLine());

        System.out.println("¿A que quieres Jugar?");
        player.setGametype("dados");
        //player.setGametype(sc.next());

        player.setIp("localhost");
        player.setPort("8989");

        return player;
    }
}
