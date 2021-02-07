package Servidor;

public class ServerClientManage {

    public void listenConnection(Boolean nuevo,String nickname){
        //filtro
        if(nuevo==false){
            serchGameInProgress(nickname);
        }else {
            newGame(nickname);
        }


    }
    //Si es un juego acabado
    private void serchGameInProgress(String nickname) {
        
    }

    //Si es un juego nuevo
    private void newGame(String nickname) { 
        
    }
}
