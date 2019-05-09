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
    private Set<Stone> removedStones;
    private ChainFactory chainFactory;

    private Board(int dimension) {
        this.dimension = dimension;
        this.intersections= new Stone[dimension][dimension];
        this.activeChainCollection= new HashSet<>();
        this.removedStones= new HashSet<>();
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
       Chain chain= chainFactory.createChain(x,y,player,this); //create stone(chain)
       //activeChainCollection.add(chain);
       intersections[y][x] = chain.getStone();
       Set<Chain> neighbourAlies = chain.neighbourAlies();
       chain.concatenateChains(neighbourAlies);
       chain.setLiberty(chain.countLiberty());
       activeChainCollection.add(chain);
       deleteChanesFromActiveList(neighbourAlies);


       List<Chain> chainstoDestroy = reCalculateNeighboursLiberty(chain);
        //Test
        System.out.println("ActiveCollection    "+activeChainCollection.size());
        System.out.println("removedStones" +removedStones.size());


        return chainstoDestroy;
    }

    public void deleteChanesFromActiveList(Set<Chain> neighbourAlies) {
        for(var chain: neighbourAlies) {
            activeChainCollection.remove(chain);
           chain = null;

        }
    }


    private List<Chain> reCalculateNeighboursLiberty(Chain chain) {


        List<Chain> chainsToDestroy = new ArrayList<>();


        List<Stone> stones=chain.getStone().getEnemyNeighbours();
        Iterator<Stone> stoneIterator= stones.iterator();
        while(stoneIterator.hasNext()) {
            Chain enemyChain = stoneIterator.next().getChain();
            int liberty = enemyChain.countLiberty();
            if (liberty == 0) {
                chainsToDestroy.add(enemyChain);
                activeChainCollection.remove(enemyChain);

                for(var i:enemyChain.getStones()) {
                    intersections[i.getY()][i.getX()]=null;
                }
                removedStones.addAll(enemyChain.getStones());
                enemyChain=null;
            } else {
                enemyChain.setLiberty(liberty);
            }
        }



        return chainsToDestroy;
    }



    private void concatenateChains(Chain chain) {
        List<Stone> stones = ((Stone)chain.getStones().stream().findFirst().orElse(null)).getAliedNeighbours();
        System.out.println("getAliedNeighbours:   "+stones.size());
        Iterator<Stone> stoneIterator = stones.iterator();
        //EDDIG VALOSZINULEG JO
        while(stoneIterator.hasNext()) {
            Chain neighbourAliedChain = stoneIterator.next().getChain();
            chain.addStones(neighbourAliedChain.getStones());

            activeChainCollection.remove(neighbourAliedChain);
            // TODO create passiveChaincollection
            neighbourAliedChain= null;
        }
        chain.countLiberty();
    }



    public Stone getStone(int x, int y) {
        return intersections[y][x];
    }

    public void printBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j< dimension; j++) {
                if (intersections[j][i] == null) {
                    System.out.print("0 ");
                } else {
                    if(intersections[j][i].getPlayer().getColor().toString().equals("BLACK")) {
                        System.out.print("B ");
                    } else {
                        System.out.print("W ");
                    }

                }
            }
            System.out.println("");
        }
    }

    public boolean isEmptyIntersection(int x,int y) {
        if (intersections[y][x] == null) {
            return true;
        }
        return false;
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
