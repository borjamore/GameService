package Entity;

import java.net.Socket;

public class Jugador {

    private String nickname;

    private String juego;

    private String ip;

    private String puerto;

    public Socket getJugador() {
        return jugador;
    }

    public void setJugador(Socket jugador) {
        this.jugador = jugador;
    }

    private Socket jugador;

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
