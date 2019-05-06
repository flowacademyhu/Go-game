import Service.Board;
import Service.Player;
import Service.Chain.Stone;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board=Board.getBoard(9);
        Player player1= new Player(Player.side.BLACK);
        Player player2 = new Player(Player.side.WHITE);
      /*  board.printBoard();
        System.out.println(board.getActiveChainCollection().size());
        board.addStone(5,5,player1);
        board.addStone(5,4,player1);
        System.out.println(board.getActiveChainCollection().size());
        board.printBoard();
        Player player2 = new Player(Player.side.WHITE);
        board.addStone(5,3,player2);
        board.addStone(5,6,player2);
        board.printBoard();
        System.out.println("chaincollection: " + board.getActiveChainCollection().size());
        board.addStone(4,5,player2);
        board.addStone(6,5,player2);
        board.printBoard();*/
      Scanner sc= new Scanner(System.in);
        board.printBoard();

        while(true) {
          String t=sc.nextLine();
          String[] t1=t.split(" ");
          int x=Integer.parseInt(t1[0]);
          int y=Integer.parseInt(t1[1]);
          board.addStone(y,x,player1);
          board.printBoard();
          System.out.println(" ");
          t=sc.nextLine();
          t1=t.split(" ");
          x=Integer.parseInt(t1[0]);
          y=Integer.parseInt(t1[1]);
          board.addStone(y,x,player2);
          board.printBoard();
      }
    }
}
