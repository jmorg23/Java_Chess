package com.game.chess.pieces;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.game.chess.AI;
import com.game.chess.Board;
import com.game.chess.TitleScreen;

public class Player extends MouseAdapter {
    public static boolean whitesTurn = true;

    public static AI theAI;
    public static Board board;

    public static void setAI(AI ai) {
        theAI = ai;

    }

    public static void turnComplete() {
        whitesTurn = !whitesTurn;
        if (theAI != null) {
            if (!theAI.myTurn) {
                board.playAI(theAI);
            } else {
                if (Board.checkMate(Board.inCheck(Board.board), Board.board)) {
                    System.out.println("checkmate");
                    // Board.printBoard(Board.board);
                    new Thread(() -> {
                        JOptionPane.showMessageDialog(null, "CheckMate!!");
                    }).start();

                    // System.exit(0);
                }
            }
        }
    }

    public Player(boolean isWhite, Board b) {
        board = b;
        if (isWhite) {
            whitesTurn = true;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked at x: " + (int) (e.getX() / 128) + "  y: " + (int) (e.getY() / 128));
        Board.areaClicked((int)(e.getX()/TitleScreen.scalex), (int)(e.getY()/TitleScreen.scaley));

    }

}
