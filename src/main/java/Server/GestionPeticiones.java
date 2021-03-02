package Server;

import Entity.Jugador;

import java.io.*;
import java.net.Socket;

/**
 * Aqui hay que gestionar si el clienteesta enviando el cierre de una partida o quiere jugar una nueva
 */
public class GestionPeticiones implements Runnable {

    Socket soket;
    Jugador jugador = new Jugador();

    GestionPeticiones(Socket socket) {
        this.soket = socket;
    }

    ;

    //Hilo de conexion con el cliente
    @Override
    public void run() {
        try (InputStream is = soket.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader bfr = new BufferedReader(isr);
             OutputStream os = soket.getOutputStream();
             PrintWriter pw = new PrintWriter(os)) {

            int tipo = leerPeticion(bfr.readLine());
            if (tipo == 0) {
                //Creanmos un nuevo juego
            } else if (tipo == 1) {
                //cerramos la partida
                cerrarJuego();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

    /**
     * Leemos la primera cadena de la petición y devolvevos un valor
     * 0-> Nuevo juego
     * 1-> Cerrrar una partida
     * -1-> Error parametro recivido no valido
     * -2 -> No se han recivido parametros
     *
     * @param peticion el mensajemandado por el cliente
     * @return un valor de selecion numerico
     */
    private int leerPeticion(String peticion) {
        int tipo = -2;
        if (peticion.equals("cerrar")) {
            tipo = 1;
        } else if (peticion.equals("jugar")) {
            tipo = 0;
        } else {
            tipo = -1;
        }
        return tipo;
    }

    //Buscamos el juego y lo cerramos de la lista
    private void cerrarJuego( /*La identificación de la partida*/) {

    }

    //Generamos un Jugador nuevo y lo mandamos al emparejador de partidas--> "BuscarPartida" Class
    private void nuevaPartida() {

    }

    //Crear un Jugador
    private Jugador nuevoJugador(String nickname, String juego, String ip, String puerto) {
        Jugador j = new Jugador();
        j.setNickname(nickname);
        j.setJuego(juego);
        j.setIp(ip);
        j.setPuerto(puerto);
        return j;
    }

}
