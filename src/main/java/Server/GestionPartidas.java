package Server;

import Entity.Jugador;
import Entity.Partida;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import static Server.IniciarServidor.partidasEnCurso;
import static Server.IniciarServidor.partidasEnEspera;

/**
 * Aqui vemos el tipo de partida que quiere el jugador, buscamos si hay alguna creada a la espera o
 * creamos una a la espera
 */
public class GestionPartidas {
    //Buscamos en la lista del tipo de juego que quiere el jugador a ver si hay alguna disponible
    public synchronized void buscarEnLista(Jugador jugador) {
        //Busacamos en la lista por si hau una partida disponible
        for (Partida partida : partidasEnEspera) {
            if (partida.getTipo().equals(jugador.getJuego())) {
                partida.getJugadores().add(jugador);
                //miramos si la partida esta llena
                if (partida.getJugadores().size() == 2) {
                    partida.setAbierta(false);
                    comprobarPartidaCompleta();
                }
            } else {
                //si no hay creamos una nueva en la lista
                nuevaPartida(jugador);
            }
        }
    }

    /**
     * Creamos una partida si no hay una en espera
     *
     * @param jugador jugador que crea la partida
     */
    public synchronized void nuevaPartida(Jugador jugador) {
        Partida partida = new Partida();
        partida.setId(partidasEnCurso.size() + 1);
        partida.setTipo(jugador.getJuego());
        partida.getJugadores().add(jugador);
        partida.setAbierta(true);
        partidasEnEspera.add(partida);
    }

    public synchronized void lanzarPartida(List<Jugador> jugadores) {
        //Lanzamos la partida diciendole al jugador que el va ser el host y quien se va unir
        //cerramos la conxion
        lanzarHost(jugadores.get(0));
        lanzarCliente(jugadores.get(1));

    }

    /**
     * devolvemos a  el host que tiene que generar la partida
     *
     * @param jugador que va ser host
     */
    public void lanzarHost(Jugador jugador) {
        Socket socket = jugador.getJugador();
        try (OutputStream os = socket.getOutputStream();
             PrintWriter pw = new PrintWriter(os)) {
            pw.println(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devolemos a los demas jugadores la direccion a donde se tienen que unir
     *
     * @param jugador jugador al que unirse
     */
    public void lanzarCliente(Jugador jugador) {
        Socket socket = jugador.getJugador();
        try (OutputStream os = socket.getOutputStream();
             PrintWriter pw = new PrintWriter(os)) {
            pw.println(false + "|" + jugador.getIp() + "|" + jugador.getPuerto());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que comprueba si un partida esta completa y la lanza
     */
    public synchronized void comprobarPartidaCompleta() {
        for (Partida partida : partidasEnEspera) {
            if (!partida.isAbierta()) {
                partidasEnCurso.add(partida);
                partidasEnEspera.remove(partida);
                lanzarPartida(partida.getJugadores());
            }
        }
    }

    /**
     * Buscamos un partida en la lista y la quitamos
     *
     * @param id id de la partida
     */
    public synchronized void cerrarJuego(String id) {
        int ids = Integer.parseInt(id);
        for (Partida partida : partidasEnCurso) {
            if (partida.getId() == ids) {
                partidasEnCurso.remove(ids);
            } else {
                System.err.println("No existe este recurso");
            }
        }
    }


}
