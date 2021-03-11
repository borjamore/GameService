package Server;

import Entity.Jugador;

import java.io.*;
import java.net.Socket;

/**
 * Aqui hay que gestionar si el clienteesta enviando el cierre de una partida o quiere jugar una nueva
 */
public class GestionPeticiones implements Runnable {

    Socket soket;
    GestionPartidas gestionPartidas = new GestionPartidas();

    GestionPeticiones(Socket socket) {
        this.soket = socket;
    }

    //Hilo de conexion con el cliente
    @Override
    public void run() {
        try (InputStream is = soket.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader bfr = new BufferedReader(isr);
             OutputStream os = soket.getOutputStream();
             PrintWriter pw = new PrintWriter(os)) {
            //Separamos el mensaje de la peticion enviado, separado por |
            String[] pericion = bfr.readLine().split("\\|");
            //Comparamos el primer parametro que indica el tipo de petición
            if (pericion[0].equals("0")) {
                //Generamos un nuevo jugador para buscar una nueva partida
                gestionPartidas.buscarEnLista(
                        nuevoJugador(soket, pericion[1], pericion[2], soket.getInetAddress().toString(), soket.getPort()));
            } else if (pericion[0].equals("1")) {
                //cerramos la partid
                try {
                    gestionPartidas.cerrarJuego(pericion[1]);
                    pw.println("Partida acabada");
                } catch (Exception e) {
                    pw.println("Error, no se ha podido borrar");
                    e.printStackTrace();
                }
            } else {
                System.err.println("error tipo no valido");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generamos un nuevo jugador
     *
     * @param socket socket del jugador clietne
     * @param nick   su nick
     * @param juego  su tipo de juego
     * @param ip     su ip
     * @param puerto su puerto
     * @return jugador contruido
     */
    private Jugador nuevoJugador(Socket socket, String nick, String juego, String ip, int puerto) {
        Jugador j = new Jugador();
        j.setNickname(nick);
        j.setJuego(juego);
        j.setIp(ip);
        j.setPuerto(String.valueOf(puerto));
        j.setJugador(socket);
        return j;
    }

}
