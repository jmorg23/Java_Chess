package com.game.chess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.game.util.GlobalTick;
import com.game.util.Button.ActivateAction;
import com.game.util.Button.ButtonCircle;
import com.game.util.Button.CustomButton;
import com.game.util.Sounds.SoundPlayer.Songs;

public class TitleScreen extends JPanel {

    public static JFrame frame = new JFrame("Chess");
    private int phase = 0;
    private CustomButton play, singleplayer, multiplayer;
    private ActivateAction multiplayerAction, singleplayerAction, playAction;

    private BufferedImage titleScreen;
    private static Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    public static double scalex = ((screenSize.getWidth() / 1920.0));
    public static double scaley = ((screenSize.getHeight() / 1080.0));

    static {
        frame.setLayout(new BorderLayout());
        frame.setSize((int) (1350 * scalex), (int) (1080 * scaley));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void playGame(boolean aiGame, int diff) {
        frame.remove(this);

        frame.add(new Board(aiGame, diff));
        frame.setVisible(true);
    }

    public void playGame() {
        frame.remove(this);

        frame.add(new Board());
        frame.setVisible(true);

    }
    public static Font mainFont;

    public TitleScreen() throws IOException, FontFormatException {
        
        Songs.MUS_1.loopSound();
        titleScreen = ImageIO.read(getClass().getResourceAsStream("/chessTitleScreen.png"));
        mainFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/aAbstractGroovy.ttf"));
        mainFont = mainFont.deriveFont(50f);
        playAction = new ActivateAction();
        multiplayerAction = new ActivateAction();
        singleplayerAction = new ActivateAction();

        play = new CustomButton(() -> {
            phase = 1;
        
        }, playAction);
        play.addText("Play");
        play.setBounds(650 - 121, 540 - (121 / 2));
        play.setFont(mainFont);
        singleplayer = new CustomButton(() -> {
            phase = 2;
            // playGame(true,
            //         Integer.parseInt(JOptionPane.showInputDialog(null,
            //                 "Enter difficulty (1 super easy, 15 hard (levels 5 and 10 coming soon)):", "Number Input",
            //                 JOptionPane.QUESTION_MESSAGE)));


            playGame(true,15);
        
        }, singleplayerAction);

        singleplayer.addText("Single Player");
        singleplayer.setBounds(250, 450);
        singleplayer.setFont(mainFont);

        multiplayer = new CustomButton(() -> {
            phase = 2;
            playGame();
        }, multiplayerAction);
 
        multiplayer.addText("Multi Player");
        multiplayer.setBounds(250, 600);
        multiplayer.setFont(mainFont);


        
        singleplayer.setVisible(false);
        multiplayer.setVisible(false);

        initGraphics();
        
        GlobalTick.setTick(60, () -> repaint());
        GlobalTick.startTick();
        

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       

        Graphics2D g2 = (Graphics2D) g;
        g2.scale(TitleScreen.scalex, TitleScreen.scaley);

        g2.drawImage(titleScreen, 0, 0, null);
        play.draw(g2);


        singleplayer.draw(g2);
        multiplayer.draw(g2);

        if (phase == 1) {
            singleplayer.setVisible(true);
            play.setVisible(false);
            multiplayer.setVisible(true);
            phase = 10;
        }

    }

    public void initGraphics() {

        AffineTransform t = new AffineTransform();
        t.scale(scalex, scaley);
        ActivateAction.setGraphicalTransform(t);


        CustomButton.addButton(multiplayer);
        CustomButton.addButton(singleplayer);
        CustomButton.addButton(play);
        CustomButton.addCircle(new ButtonCircle(play.myIndex()));
        CustomButton.addCircle(new ButtonCircle(new int[] {multiplayer.myIndex(), singleplayer.myIndex()}));

        frame.add(this);
        addMouseListener(multiplayerAction);
        addMouseListener(singleplayerAction);
        addMouseListener(playAction);

        addMouseMotionListener(multiplayerAction);
        addMouseMotionListener(singleplayerAction);
        addMouseMotionListener(playAction);

        frame.setVisible(true);
    }

}