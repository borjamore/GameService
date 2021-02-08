package Server;

import Entity.Jugador;

/**
 * Aqui hay que gestionar si el clienteesta enviando el cierre de una partida o quiere jugar una nueva
 */
public class ProcesarJugador implements Runnable{

    //Hilo de conexion con el cliente
    @Override
    public void run() {

    }

    //Analizamos si la peticion es para cerrar un juego o buscar una nueva partida
    private void leerPeticion(){

    }

    //Buscamos el juego y lo cerramos de la lista
    private void cerrarJuego( /*La identificación de la partida*/ ){

    }
    //Generamos un Jugador nuevo y lo mandamos al emparejador de partidas--> "BuscarPartida" Class
    private void nuevaPartida(){

    }
    //Crear un Jugador
    private Jugador nuevoJugador(){
        Jugador j = new Jugador();
        return j;
    }

}
