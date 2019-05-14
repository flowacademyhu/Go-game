package View;

import Service.Board;
import Service.Chain.Chain;
import Service.Chain.Stone;
import Service.Player.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameArea extends JFrame implements MouseListener, KeyListener {
    private JIntersection[][] intersections;
    private int dimension = 9;
    private Board board;
    private List<Chain> chainsToDestroy;

    private Container middleContainer;
    private JButton newGame;
    private JButton loadGame;
    private JButton exit;
    private JPanel gameArea;
    private JPanel sideMenu;
    private JButton surrender;
    private JTextArea gameLog;
    private JTextArea countDown;
    private JButton sendMessage;
    private JTextArea messageInfo;
    private JTextArea chat;
    private Player actualPlayer;
    private JLabel picLabel;
    private BufferedImage bufferedImage;
    private BufferedImage blackImg;
    private BufferedImage whiteImg;
    private ImageIcon blackIcon;
    private ImageIcon whiteIcon;

    public GameArea() throws HeadlessException {
        this.board= Board.getBoard(dimension);
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
      //  setSize(300,200);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {

                whiteImg = ImageIO.read(new File("./resources/p2.png"));
                blackImg = ImageIO.read(new File("./resources/p1.png"));
                blackIcon = new ImageIcon(new ImageIcon(blackImg).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
                whiteIcon = new ImageIcon(new ImageIcon(whiteImg).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));


        } catch (IOException e) {
            e.printStackTrace();
        }

       // initWindow();
        startgame();
    }
    public void initWindow() {
        middleContainer= new Container();
        newGame= new JButton("New Game");
        loadGame= new JButton("Load Game");
        exit = new JButton("Exit");
        newGame.addMouseListener(this);
        loadGame.addMouseListener(this);
        addKeyListener(this);
        exit.addMouseListener(this);
        middleContainer.setLayout(new FlowLayout());
        middleContainer.add(newGame);
        middleContainer.add(loadGame);
        middleContainer.add(exit);

        this.add(middleContainer);
        setVisible(true);
    }

    public void changeBackground(JIntersection button, String color) {
        try {

                if (color.equalsIgnoreCase("black")) {
                    blackImg = ImageIO.read(new File("./resources/black/c.png"));
                    blackIcon = new ImageIcon(blackImg);
                    button.setIcon(blackIcon);

                } else if (color.equalsIgnoreCase("white")) {
                    whiteImg = ImageIO.read(new File("./resources/white/c.png"));
                    whiteIcon = new ImageIcon(whiteImg);
                    button.setIcon(whiteIcon);

                } else {
                    //TODO atnevezes hianyzik
                    whiteImg = ImageIO.read(new File("./resources/c.png"));
                    whiteIcon = new ImageIcon(whiteImg);
                    button.setIcon(whiteIcon);

                }

        } catch (Exception e) {
            System.out.println(e);
        }


    }


    public void startgame() {
        GridLayout gridLayout= new GridLayout(dimension,dimension);
        gameArea= new JPanel();
        sideMenu= new JPanel();
        BoxLayout boxLayout = new BoxLayout(sideMenu,BoxLayout.Y_AXIS);
        sideMenu.setLayout(boxLayout);
        gameArea.setLayout(gridLayout);
       // setLayout(gridLayout);
        intersections= new JIntersection[dimension][dimension];
        setTitle("GO");

        /*try {
            bufferedImage = ImageIO.read(new File("./resources/c.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Border emptyBorder = BorderFactory.createEmptyBorder();
        for (int i = 0 ; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                intersections[i][j]= new JIntersection(i,j);
                intersections[i][j].setOpaque(true);
                intersections[i][j].addMouseListener(this);
              //  intersections[i][j].setIcon(new ImageIcon(bufferedImage));
                changeBackground(intersections[i][j],"");
                intersections[i][j].setBackground(Color.blue);
                intersections[i][j].setBorder(emptyBorder);

                gameArea.add(intersections[i][j]);
            }
        }
        add(gameArea,BorderLayout.CENTER);

        surrender = new JButton("Surrend");
        surrender.setSize(300,200);
        gameLog = new JTextArea("Welcome");
        gameLog.setLineWrap(true);
        gameLog.setEditable(false);
        gameLog.setWrapStyleWord(true);


        JScrollPane scroll = new JScrollPane(gameLog);
        scroll.setPreferredSize(new Dimension(400, 300));
        scroll.setMinimumSize(new Dimension(400, 300));
        scroll.setMaximumSize(new Dimension(400, 300));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gameLog.setSize(300,600);

       JLabel gameLoginfo = new JLabel("Game log:");

       chat = new JTextArea("");
       chat.setLineWrap(true);
       chat.setLineWrap(true);
       chat.setEditable(true);
       JScrollPane scrollChat = new JScrollPane(chat);
       scrollChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        messageInfo = new JTextArea("Write your message here...");
        messageInfo.setPreferredSize(new Dimension(400, 30));
        messageInfo.setMinimumSize(new Dimension(400, 30));
        messageInfo.setMaximumSize(new Dimension(400, 30));
        messageInfo.setBackground(sideMenu.getBackground());
        messageInfo.setEditable(false);
        sendMessage = new JButton("Send");
        countDown = new JTextArea("Hello my friend! Black starts the game...");
        countDown.setPreferredSize(new Dimension(400, 100));
        countDown.setMinimumSize(new Dimension(400, 100));
        countDown.setMaximumSize(new Dimension(400, 100));
        countDown.setEditable(false);
        countDown.setBackground(sideMenu.getBackground());

        Font font = new Font("Default", 0 ,20);
        countDown.setFont(font);

        //picLabel = new JLabel();

        try {
            bufferedImage = ImageIO.read(new File("./resources/p1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        picLabel = new JLabel(new ImageIcon(bufferedImage));
        picLabel.setHorizontalAlignment(SwingConstants.LEFT);
        picLabel.setVerticalAlignment(SwingConstants.CENTER);

        sendMessage.addKeyListener(this);
        sendMessage.addMouseListener(this);
        sideMenu.add(gameLoginfo);
        sideMenu.add(scroll);
        sideMenu.add(surrender);
        sideMenu.add(messageInfo);
        sideMenu.add(scrollChat);
        sideMenu.add(sendMessage);

        sideMenu.add(countDown);
        sideMenu.add(picLabel);

        sideMenu.setPreferredSize(new Dimension(400, MAXIMIZED_VERT));
        sideMenu.setMinimumSize(new Dimension(400, MAXIMIZED_VERT));
        sideMenu.setMaximumSize(new Dimension(400, MAXIMIZED_VERT));

        add(sideMenu,BorderLayout.EAST);
        sendMessage.requestFocus();
        startTimer();
        setVisible(true);

    }

    public void startTimer(){
        Runnable timer = this::run;
        Thread t= new Thread(timer);
        t.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(newGame)||e.getSource().equals(loadGame)||e.getSource().equals(exit)) {
            initWindowClicked(e);
        } else if (e.getSource().equals(sendMessage)) {
            gameLog.setText(gameLog.getText()+"\n----"+chat.getText().toUpperCase());
            chat.setText(" ");
            setVisible(true);
        }
        else{

            JIntersection jIntersection = ((JIntersection) e.getSource());
            int x = jIntersection.getxPosition();
            int y = jIntersection.getyPosition();
            if (board.isEmptyIntersection(x, y)) {
                actualPlayer = Player.getPlayerInstance();

                chainsToDestroy = board.addStone(x, y, actualPlayer);

                if (actualPlayer.getColor().toString().equalsIgnoreCase("BLACK")) {
                   // jIntersection.setBackground(Color.BLACK);
                   // jIntersection.setIcon(blackIcon);
                    changeBackground(jIntersection,"black");

                    gameInfo(actualPlayer,x,y);
                } else if (actualPlayer.getColor().toString().equalsIgnoreCase("WHITE")) {
                   // jIntersection.setBackground(Color.WHITE);
                   // jIntersection.setIcon(whiteIcon);
                    changeBackground(jIntersection,"white");

                    gameInfo(actualPlayer,x,y);
                }
                destroyChainsFromBoard(chainsToDestroy);
            }
        }

    }

        public void gameInfo(Player player, int x, int y) {
            gameLog.setText(gameLog.getText()+ "\n" +player.getColor().toString()+ " játékos letett egy követ a következő koordinátákra:"+ (Integer)(y+1) +", "+(Integer)(x+1));
        }

    public void initWindowClicked(MouseEvent e) {
        if (((JButton)e.getSource()).getText().equalsIgnoreCase("new game")) {
            removeButtons();
            startgame();
        } else if (((JButton)e.getSource()).getText().equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }

    public void removeButtons() {
        this.remove(middleContainer);
    }

    public void destroyChainsFromBoard(List<Chain> chainsToDestroy) {
        if (chainsToDestroy.size() == 0) {
            return;
        } else {
            for (var chain: chainsToDestroy) {
                for (var stone: chain.getStones()) {
                    gameLog.setText(gameLog.getText()+ "\n" + "A következő"+ "kő lekerült a tábláról: "+ (Integer)(stone.getY()+1)+", "+(Integer)(stone.getX()+1));
                   // intersections[stone.getX()][stone.getY()].setIcon(null);
                    changeBackground(intersections[stone.getX()][stone.getY()],"");

                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            gameLog.setText(gameLog.getText()+"\n"+chat.getText());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void run() {
        while (actualPlayer == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
        while (actualPlayer.getTimer() > 0) {
                try {
                    if (actualPlayer.getColor().toString().equalsIgnoreCase("BLACK")) {
                        bufferedImage = ImageIO.read(new File("./resources/p2.png"));
                    } else {
                        bufferedImage = ImageIO.read(new File("./resources/p1.png"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                picLabel.setIcon(new ImageIcon(bufferedImage));

                actualPlayer.setEnemyTimer(actualPlayer.getTimer() - 1);

                countDown.setText("Remaining time :   " + actualPlayer.getTimer());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }
