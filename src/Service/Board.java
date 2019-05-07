package Service;


import Service.Chain.Chain;
import Service.Chain.ChainFactory;
import Service.Chain.Stone;
import Service.Player.Player;

import java.util.*;

public class Board {
    private static Board instance;
    private final int dimension;
    private Stone[][] intersections;
    private Set<Chain> activeChainCollection;
    private ChainFactory chainFactory;

    private Board(int dimension) {
        this.dimension = dimension;
        this.intersections= new Stone[dimension][dimension];
        this.activeChainCollection= new HashSet<>();
        this.chainFactory = new ChainFactory();
    }

    public static Board getBoard(int dimension) {
        if(instance == null) {
            instance= new Board(dimension);
            return instance;
        } else {
            return instance;
        }
    }

    public List<Chain> addStone(int x, int y, Player player) {
       Chain chain= chainFactory.createChain(x,y,player,this);
       activeChainCollection.add(chain);
       intersections[x][y] = (Stone) (chain.getStones().stream().findFirst()).orElse(null);
       List<Chain> chainstoDestroy = reCalculateNeighboursLiberty(chain);
       concatenateChains(chain);
       return chainstoDestroy;
    }

    private List<Chain> reCalculateNeighboursLiberty(Chain chain) {


        List<Chain> chainsToDestroy = new ArrayList<>();


        List<Stone> stones=((Stone)chain.getStones().stream().findFirst().orElse(null)).getEnemyNeighbours();
        Iterator<Stone> stoneIterator= stones.iterator();
        while(stoneIterator.hasNext()) {
            Chain enemyChain = stoneIterator.next().getChain();
            int liberty = enemyChain.countLiberty();
            if (liberty == 0) {
                chainsToDestroy.add(enemyChain);
                activeChainCollection.remove(enemyChain);

                for(var i:enemyChain.getStones()) {
                    intersections[i.getX()][i.getY()]=null;
                }

                //TODO create passiveChaincollection
                enemyChain=null;
            } else {
                enemyChain.setLiberty(liberty);
            }
        }
        //TEST
        System.out.println("number of chains"+activeChainCollection.size());


        return chainsToDestroy;
    }

    private void concatenateChains(Chain chain) {
        List<Stone> stones = ((Stone)chain.getStones().stream().findFirst().orElse(null)).getAliedNeighbours();
        Iterator<Stone> stoneIterator = stones.iterator();

        while(stoneIterator.hasNext()) {
            Chain neighbourAliedChain = stoneIterator.next().getChain();
            chain.addStones(neighbourAliedChain.getStones());
            System.out.println("list size:" + activeChainCollection.size()+ "  szomszed"+ neighbourAliedChain.getStones());
            activeChainCollection.remove(neighbourAliedChain);
            // TODO create passiveChaincollection
            neighbourAliedChain= null;
        }
        chain.countLiberty();
    }



    public Stone getStone(int x, int y) {
        return intersections[x][y];
    }

    public void printBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j< dimension; j++) {
                if (intersections[i][j] == null) {
                    System.out.print("0 ");
                } else {
                    if(intersections[i][j].getPlayer().getColor().toString().equals("BLACK")) {
                        System.out.print("B ");
                    } else {
                        System.out.print("W ");
                    }

                }
            }
            System.out.println("");
        }
    }


    public int getDimension() {
        return dimension;
    }

    public Stone[][] getIntersections() {
        return intersections;
    }

    public void setIntersections(Stone[][] intersections) {
        this.intersections = intersections;
    }

    public Set<Chain> getActiveChainCollection() {
        return activeChainCollection;
    }
}
