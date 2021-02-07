package Entity;

import lombok.Data;

@Data
public class Player {

    String nickname;

    String gametype;

    String ip;

    String port;

    Boolean newgame;
}
