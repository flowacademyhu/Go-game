import Service.Board;
import Service.Player.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board=Board.getBoard(9);
        Player player1= Player.getPlayerInstance();
        Player player2 = Player.getPlayerInstance();
      Scanner sc= new Scanner(System.in);
        board.printBoard();
        int[] checkCounter=new int[]{0,0,0};
        String[] t1;
        int x;
        int y;
        while(true) {
            System.out.println(player1.getColor().toString()+ "  lepese: ");
          String t=sc.nextLine();
          if (t.equalsIgnoreCase("passz")) {
              checkCounter[0]++;
              checkCounter[2]++;
              if (checkCounter[2]==2) {
                  System.out.println("Jatek vege");
                  System.exit(0);
              }
          } else {
              t1 = t.split(" ");
              x = Integer.parseInt(t1[0]);
              y = Integer.parseInt(t1[1]);
              board.addStone(y, x, player1);
              board.printBoard();
              checkCounter[2]=0;
          }
          System.out.println(" ");
            System.out.println(player2.getColor().toString()+ "  lepese: ");
            t = sc.nextLine();
            if (t.equalsIgnoreCase("passz")) {
                checkCounter[1]++;
                checkCounter[2]++;
                if (checkCounter[2]==2) {
                    System.out.println("Jatek vege");
                    System.exit(0);
                }
            } else {
                t1 = t.split(" ");
                x = Integer.parseInt(t1[0]);
                y = Integer.parseInt(t1[1]);
                board.addStone(y, x, player2);
                board.printBoard();
                checkCounter[2]=0;

            }

      }
    }
}
