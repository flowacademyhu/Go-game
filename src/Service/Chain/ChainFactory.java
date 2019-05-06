package Service.Chain;

import Service.Board;
import Service.Player.Player;

public class ChainFactory {

    public Chain createChain(int x, int y, Player player, Board board) {
        Stone stone= new Stone(x, y, player, board);
        Chain chain=new Chain(stone);
        stone.setChain(chain);
        return chain;
    }
}
