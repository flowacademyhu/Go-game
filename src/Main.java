import Service.Board;
import Service.Player;
import Service.Stone;

public class Main {
    public static void main(String[] args) {
        Board board= new Board(19);
        Player player1=new Player(Player.side.BLACK);
        Stone stone= new Stone(0,0,player1);
        board.addStoneToBoard(stone,0,1);
        board.addStoneToBoard(stone,1,1);
        board.printBoard();
        System.out.println(player1);
        System.out.println(stone);
    }
}
