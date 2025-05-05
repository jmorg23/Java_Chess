package com.game.chess;

import javax.swing.JPanel;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.game.chess.pieces.Bishop;
import com.game.chess.pieces.Horse;
import com.game.chess.pieces.King;
import com.game.chess.pieces.Pawn;
import com.game.chess.pieces.Piece;
import com.game.chess.pieces.Player;
import com.game.chess.pieces.Queen;
import com.game.chess.pieces.Rook;
import com.game.util.Button.ActivateAction;
import com.game.util.Button.ButtonCircle;
import com.game.util.Button.CustomButton;

public class Board extends JPanel implements ActionListener {

    private static ArrayList<Point> possibleMoves = new ArrayList<>();
    private static Piece selectedPiece;
    public static ArrayList<Piece> black = new ArrayList<>();
    public static ArrayList<Piece> white = new ArrayList<>();
    public static boolean aiGame = false;
    private BufferedImage mainBg;
    private CustomButton pieceLeft, pieceRight, boardLeft, boardRight;

    private AI theAI;
    private boolean playAI = false;
    public static Piece[][] board = new Piece[][] {
            { new Rook(0, 0, "black"), new Horse(1, 0, "black"), new Bishop(2, 0, "black"), new Queen(3, 0, "black"),
                    new King(4, 0, "black"), new Bishop(5, 0, "black"),
                    new Horse(6, 0, "black"), new Rook(7, 0, "black") },
            { new Pawn(0, 1, "black"), new Pawn(1, 1, "black"), new Pawn(2, 1, "black"), new Pawn(3, 1, "black"),
                    new Pawn(4, 1, "black"), new Pawn(5, 1, "black"),
                    new Pawn(6, 1, "black"), new Pawn(7, 1, "black") },
            { null, null, null, null, null, null, null, null },
            { null, null, null, null, null, null, null, null },
            { null, null, null, null, null, null, null, null },
            { null, null, null, null, null, null, null, null },
            { new Pawn(0, 6, "white"), new Pawn(1, 6, "white"), new Pawn(2, 6, "white"), new Pawn(3, 6, "white"),
                    new Pawn(4, 6, "white"), new Pawn(5, 6, "white"),
                    new Pawn(6, 6, "white"), new Pawn(7, 6, "white") },
            { new Rook(0, 7, "white"), new Horse(1, 7, "white"), new Bishop(2, 7, "white"), new Queen(3, 7, "white"),
                    new King(4, 7, "white"), new Bishop(5, 7, "white"),
                    new Horse(6, 7, "white"), new Rook(7, 7, "white") },

    };

    public static void updateTheme() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j] instanceof Pawn) {
                        board[i][j] = new Pawn(board[i][j].x, board[i][j].y, board[i][j].color);
                    } else if (board[i][j] instanceof Rook) {
                        board[i][j] = new Rook(board[i][j].x, board[i][j].y, board[i][j].color);
                    } else if (board[i][j] instanceof Horse) {
                        board[i][j] = new Horse(board[i][j].x, board[i][j].y, board[i][j].color);
                    } else if (board[i][j] instanceof Bishop) {
                        board[i][j] = new Bishop(board[i][j].x, board[i][j].y, board[i][j].color);
                    } else if (board[i][j] instanceof King) {
                        board[i][j] = new King(board[i][j].x, board[i][j].y, board[i][j].color);
                    } else if (board[i][j] instanceof Queen) {
                        board[i][j] = new Queen(board[i][j].x, board[i][j].y, board[i][j].color);
                    }
                }
            }
        }
    }

    public static void updatePawn(Pawn p, Piece[][] board) {

        board[p.y][p.x] = new Queen(p.x, p.y, p.color);

    }

    public static int inCheck(Piece[][] board) {
        // find Kings
        int whoInCheck = 0;

        King whiteKing = null;
        King blackKing = null;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] instanceof King) {
                    if (board[i][j].getPiece().contains("white")) {
                        whiteKing = (King) board[i][j];
                    } else {
                        blackKing = (King) board[i][j];

                    }

                }
            }
        }
        if (whiteKing == null || blackKing == null) {
            return 0;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getPiece().contains("white")
                            ? board[i][j].getPossiblePlaces(board).contains(blackKing.getPosition())
                            : board[i][j].getPossiblePlaces(board).contains(whiteKing.getPosition())) {

                        if (board[i][j].getPiece().contains("white")) {

                            whoInCheck += 10;

                        } else {

                            whoInCheck += 1;

                        }
                    }
                }

            }
        }

        return whoInCheck;
    }

    public static Piece[][] getBoardCopy() {
        Piece[][] con = new Piece[8][8];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                con[i][j] = board[i][j] != null ? board[i][j].clone() : null;

            }
        }

        return con;
    }

    public static Piece[][] getBoardCopy(Piece[][] board) {
        Piece[][] con = new Piece[8][8];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                con[i][j] = board[i][j] != null ? board[i][j].clone() : null;

            }
        }

        return con;
    }

    public static void areaClicked(int x, int y) {
        if (x / 128 > 7 || y / 128 > 7) {
            return;
        }
        if (selectedPiece != null) {
            if (possibleMoves.contains(new Point(x / 128, y / 128))) {

                Piece[][] boardCopy = getBoardCopy();

                Piece lastSel = selectedPiece.clone();

                selectedPiece = boardCopy[lastSel.y][lastSel.x].clone();

                boardCopy[y / 128][x / 128] = selectedPiece;
                boardCopy[selectedPiece.y][selectedPiece.x] = null;
                selectedPiece.move(x / 128, y / 128, boardCopy);

                int whoCheck = inCheck(boardCopy);
                // System.out.println(whoCheck);
                if (whoCheck != 0)
                    if (whoCheck == 11 || (Player.whitesTurn ? whoCheck == 1 : whoCheck == 10)) {
                        // selectedPiece.move(movePiecePoint.x, movePiecePoint.y);
                        selectedPiece = lastSel;
                        // System.out.println(whoCheck);
                        // System.out.println("you did not get out of check");
                        return;

                    }
                // board = boardCopy;
                selectedPiece = board[lastSel.y][lastSel.x].clone();

                board[y / 128][x / 128] = selectedPiece;
                board[selectedPiece.getPosition().y][selectedPiece.getPosition().x] = null;
                selectedPiece.move(x / 128, y / 128, board);
                if (checkMate(inCheck(board), board)) {
                    System.out.println("checkmate");
                    printBoard(board);
                    JOptionPane.showMessageDialog(TitleScreen.frame, "CheckMate!!");

                    System.exit(0);

                }

                Player.turnComplete();
                possibleMoves.clear();
                return;

            }
        }
        if (board[y / 128][x / 128] != null) {
            if (Player.whitesTurn ? board[y / 128][x / 128].color.equals("white")
                    : board[y / 128][x / 128].color.equals("black")) {
                selectedPiece = board[y / 128][x / 128];
                possibleMoves = selectedPiece.getPossiblePlaces(board);
            }

        } else {
            selectedPiece = null;
            possibleMoves = new ArrayList<>();
        }
    }

    public static void castle(Piece[][] board, int corner) {
        // System.out.println("corner: " + corner);
        if (corner == 1) {
            board[0][2] = board[0][0].clone();
            board[0][2].move(2, 0, board);

            board[0][0] = null;
        } else if (corner == 2) {
            board[0][5] = board[0][7].clone();
            board[0][5].move(5, 0, board);

            board[0][7] = null;
        } else if (corner == 3) {
            board[7][2] = board[7][0].clone();
            board[7][2].move(2, 7, board);

            board[7][0] = null;
        } else if (corner == 4) {
            board[7][5] = board[7][7].clone();
            board[7][5].move(5, 7, board);

            board[7][7] = null;
        }
    }

    public Board(boolean hasAi, int diff) {

        try {
            CustomButton.freshStart();
            mainBg = ImageIO.read(getClass().getResourceAsStream("/mainBg.png"));
            // newGame = new CustomButton(() -> {

            // }, new ActivateAction());
        
            // newGame.setBounds(1036, 875, 200, 200);

            pieceLeft = new CustomButton(() -> {
                Piece.setTheme(-2);

            }, new ActivateAction());
            pieceLeft.addImage(ImageIO.read(getClass().getResourceAsStream("/buttons/left.png")));
            pieceLeft.addButtonPressedImage(ImageIO.read(getClass().getResourceAsStream("/buttons/leftSel.png")));
            pieceLeft.setBounds(1095, 775);

            pieceRight = new CustomButton(() -> {
                Piece.setTheme(2);

            }, new ActivateAction());
            pieceRight.addImage(ImageIO.read(getClass().getResourceAsStream("/buttons/right.png")));
            pieceRight.addButtonPressedImage(ImageIO.read(getClass().getResourceAsStream("/buttons/rightSel.png")));
            pieceRight.setBounds(1270, 775);

            boardLeft = new CustomButton(() -> {
                changeSquareTheme(-2);

            }, new ActivateAction());
            boardLeft.addImage(ImageIO.read(getClass().getResourceAsStream("/buttons/left.png")));
            boardLeft.addButtonPressedImage(ImageIO.read(getClass().getResourceAsStream("/buttons/leftSel.png")));
            boardLeft.setBounds(1095, 505);

            boardRight = new CustomButton(() -> {
                changeSquareTheme(2);

            }, new ActivateAction());
            boardRight.addImage(ImageIO.read(getClass().getResourceAsStream("/buttons/right.png")));
            boardRight.addButtonPressedImage(ImageIO.read(getClass().getResourceAsStream("/buttons/rightSel.png")));
            boardRight.setBounds(1265, 505);

        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean w = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    w = true;
                    continue;
                } else if (!w) {
                    black.add(board[i][j]);
                } else {
                    white.add(board[i][j]);

                }

            }
        }
        boolean iw = Math.random() < 0.5;
        if(!hasAi){
            iw = true;
        }
        Player player1 = new Player(iw, this);
        if (hasAi) {
            new AI(!iw, board, diff, white);
        }

        // addMouseListener(newGame.getMyAction());
        // addMouseMotionListener(newGame.getMyAction());
        // CustomButton.addButton(newGame);
        addMouseListener(pieceLeft.getMyAction());
        addMouseMotionListener(pieceLeft.getMyAction());
        CustomButton.addButton(pieceLeft);
        addMouseListener(pieceRight.getMyAction());
        addMouseMotionListener(pieceRight.getMyAction());
        CustomButton.addButton(pieceRight);

        addMouseListener(boardLeft.getMyAction());
        addMouseMotionListener(boardLeft.getMyAction());
        CustomButton.addButton(boardLeft);

        addMouseListener(boardRight.getMyAction());
        addMouseMotionListener(boardRight.getMyAction());
        CustomButton.addButton(boardRight);

        CustomButton.addCircle(new ButtonCircle(new int[] { 0, 1, 2, 3}));

        addMouseListener(player1);

        new Timer(33, this).start();
    }

    public Board() {
        this(false, 0);
    }

    private Color squareColor = Color.WHITE;
    private Color squareColor2 = Color.GRAY;
    private int boardTheme = 0;

    public static final int DEFAULT = 0;
    public static final int GLASS = 2;
    public static final int ROSE = 4;
    public static final int FALL = 6;
    public static final int OCEAN = 8;
    public static final int WINTER = 10;

    public void changeSquareTheme(int dir) {
        boardTheme += dir;

        if (boardTheme > 11) {
            boardTheme = 0;
        } else if (boardTheme < 0) {
            boardTheme = 10;
        }

        switch (boardTheme) {
            case DEFAULT:
                squareColor = Color.WHITE;
                squareColor2 = Color.GRAY;
                break;
            case GLASS:
                squareColor = new Color(164, 192, 194);
                squareColor2 = new Color(229, 254, 255);
                break;
            case ROSE:

                squareColor = new Color(255, 117, 118);
                squareColor2 = new Color(234, 6, 8);
                break;
            case FALL:
                squareColor = new Color(246, 168, 50);
                squareColor2 = new Color(245, 208, 153);
                break;
            case OCEAN:
                squareColor = new Color(45, 237, 245);
                squareColor2 = new Color(48, 40, 246);
                break;
            case WINTER:
                squareColor = new Color(7, 3, 98);
                squareColor2 = new Color(179, 176, 242);
                break;
        }

    }

    public void playAI(AI ai) {

        theAI = ai;
        playAI = true;

    }

    private int con = 0;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.scale(TitleScreen.scalex, TitleScreen.scaley);
        g2.drawImage(mainBg, 0, 0, null);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                g2.setStroke(new java.awt.BasicStroke(0));

                if ((i + j) % 2 == 0) {

                    g2.setColor(squareColor);

                } else {
                    g2.setColor(squareColor2);

                }

                g2.fillRect(128 * j, 128 * i, 128, 128);

                g2.setColor(Color.YELLOW);
                g2.setStroke(new java.awt.BasicStroke(8));
                if (possibleMoves.contains(new Point(j, i))) {
                    g2.drawRect(128 * j, 128 * i, 128, 128);
                }
                if (board[i][j] != null)
                    board[i][j].draw(g2);
            }
        }

        if (con == 1) {

            black = new ArrayList<>();
            white = new ArrayList<>();

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == null) {
                        continue;
                    } else if (board[i][j].color.equals("white")) {
                        white.add(board[i][j]);

                    } else {
                        black.add(board[i][j]);
                    }

                }
            }
            theAI.move(theAI.isWhite ? white : black);
            con = 0;
        }
        if (playAI) {
            con = 1;
            playAI = false;
        }

        pieceLeft.draw(g2);
        pieceRight.draw(g2);
        boardLeft.draw(g2);
        boardRight.draw(g2);
        g2.setFont(boardLeft.getFont());
        g2.setColor(Color.BLACK);
        switch (boardTheme) {
            case DEFAULT:
                g2.drawString("Default", 1135, 512);
                break;
            case ROSE:
                g2.drawString("Rose", 1135, 512);
                break;
            case GLASS:
                g2.drawString("Glass", 1135, 512);
                break;
            case FALL:
                g2.drawString("Autumn", 1135, 512);
                break;
            case WINTER:
                g2.drawString("Winter", 1135, 512);
                break;
            default:
                g2.drawString("Ocean", 1135, 512);
        }
        switch (Piece.getTheme()) {
            case DEFAULT:
                g2.drawString("Default", 1135, 782);
                break;
            case ROSE:
                g2.drawString("Rose", 1135, 782);
                break;
            case GLASS:
                g2.drawString("Glass", 1135, 782);
                break;
            case FALL:
                g2.drawString("Autumn", 1135, 782);
                break;
            case WINTER:
                g2.drawString("Winter", 1135, 782);
                break;
            default:
                g2.drawString("Ocean", 1135, 782);
        }
        if (Player.whitesTurn) {
            g2.setColor(Color.YELLOW);
            g2.drawRect(1069, 162, 110, 58);
        } else {
            g2.setColor(Color.YELLOW);
            g2.drawRect(1180, 162, 110, 58);

        }

    }

    public static void printBoard(Piece[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (j == 0)
                    System.out.print("[ ");

                System.out.print(b[i][j] != null ? b[i][j].getPiece() : "No Piece");

                if (j == b[i].length - 1)
                    System.out.print(" ]");
                else
                    System.out.print("   ");
            }
            System.out.println("\n---------------------------------------------------------------------------------");

        }

    }

    public static Move getMove(Piece p, int x, int y, int takeWorth) {
        Point pos = new Point(x, y);
        Move move = new Move();

        Piece[][] bc = getBoardCopy();
        Piece pcopy = p.clone();

        pcopy.move(x, y, bc);

        bc[y][x] = pcopy;

        bc[p.y][p.x] = null;

        boolean canBeTakenWhereAtNow = couldBeTaken(p.getPosition(), p.getPiece().contains("white") ? black : white,
                new ArrayList<>(), board) != null;

        ArrayList<Piece> bl = new ArrayList<>();
        ArrayList<Piece> wh = new ArrayList<>();

        for (int i = 0; i < bc.length; i++) {
            for (int j = 0; j < bc[i].length; j++) {
                if (bc[i][j] == null) {
                    continue;
                } else if (bc[i][j].getPiece().contains("white"))
                    wh.add(bc[i][j]);
                else
                    bl.add(bc[i][j]);

            }
        }

        ArrayList<Piece> killers = new ArrayList<>();
        int leastPointKiller = 0;

        while (true) {
            Piece pcon = couldBeTaken(new Point(x, y), p.getPiece().contains("white") ? bl : wh, killers, bc);
            if (pcon != null) {

                if (leastPointKiller < pcon.pointsWorth) {
                    leastPointKiller = pcon.pointsWorth;
                }
                killers.add(pcon);

            } else {
                break;
            }
        }

        int inC = inCheck(bc);

        move.setCanBeTaken(killers.size() > 0);
        move.setGetsPutIntoCheck(p.color.equals("white") ? inCheck(bc) == 10 : inCheck(bc) == 1);
        if (p.color.equals("white") ? inCheck(board) == 10 : inCheck(board) == 1)
            move.setGetsOutOfCheck(p.color.equals("white") ? inC != 10 && inC != 11 : inC != 1 && inC != 11);

        move.setBlocksPotentialDeath(canBeTakenWhereAtNow && !move.isCanBeTaken());

        move.setCanBeTakenButRecoverForMorePoints(move.isCanBeTaken() ? leastPointKiller > p.pointsWorth : false);
        move.setPos(pos);
        // move.setCanBeTaken(couldBeTaken(p, enemyPieces, new ArrayList<>())!=null);
        move.setPointsWorth(takeWorth);

        move.setCanCheckMate(checkMateInOne(bc));
        move.setWillPutEnemyInCheck(p.color.equals("white") ? inCheck(bc) == 10 : inCheck(bc) == 1);

        // Piece[][] bcCopy = getBoardCopy(bc);
        // System.out.println(inC);
        if (inC == 0) {
            // printBoard(bc);

            for (Piece ep : p.getPiece().contains("white") ? bl : wh) {

                if (move.isWillBeCheckedMate()) {
                    break;
                }

                for (Point point : ep.getPossiblePlaces(bc)) {
                    Piece[][] bcCopy = getBoardCopy(bc);

                    bcCopy[point.y][point.x] = ep.clone();
                    bcCopy[ep.y][ep.x] = null;
                    bcCopy[point.y][point.x].move(point.x, point.y, bcCopy);

                    if (checkMateInOne(bcCopy)) {
                        move.setWillBeCheckedMate(true);
                        // printBoard(bcCopy);
                        // System.out.println("\n\n\n\n");
                        // System.exit(0);
                        break;
                    }
                }

            }
        }

        return move;

    }

    public static Piece couldBeTaken(Point me, ArrayList<Piece> enemyPieces, ArrayList<Piece> exeptions,
            Piece[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (enemyPieces.size() > (i * board[i].length) + j)
                    if (enemyPieces.get((i * board[i].length) + j).getPossiblePlaces(board).contains(me)
                            && !exeptions.contains(enemyPieces.get((i * board[i].length) + j))) {
                        // System.out.println();
                        return enemyPieces.get((i * board[i].length) + j);
                    }

            }
        }

        return null;

    }

    public static boolean checkMateInOne(Piece[][] board) {
        return checkMate(inCheck(board), board);

    }

    public static boolean checkMate(int who, Piece[][] bo) {
        if (who == 0) {
            return false;
        }

        /*
         * if king cant move anywhere
         * 
         * if no piece can cover
         */

        Piece[][] boardCopy = getBoardCopy(bo);
        for (int i = 0; i < boardCopy.length; i++) {
            for (int j = 0; j < boardCopy[i].length; j++) {

                if (boardCopy[i][j] != null) {

                    if (who % 11 != 0) {

                        // System.out.println("checking peice: " + boardCopy[i][j].getPiece());
                        if (who == 1 ? boardCopy[i][j].getPiece().contains("white")
                                : boardCopy[i][j].getPiece().contains("black")) {

                            ArrayList<Point> themoves = boardCopy[i][j].getPossiblePlaces(boardCopy);

                            // System.out
                            // .println("checking: " + boardCopy[i][j] + "who has " + themoves.size() + "
                            // many moves");

                            for (int k = 0; k <= themoves.size() - 1; k++) {
                                // Piece p =
                                // boardCopy[themoves.get(k).y][themoves.get(k).x]!=null?boardCopy[themoves.get(k).y][themoves.get(k).x].clone():null;
                                // Point lastPlace = new Point(boardCopy[i][j].getPosition().x,
                                // boardCopy[i][j].getPosition().y);
                                // System.out.println("he can move to: x=" + themoves.get(k).x + "y=" +
                                // themoves.get(k).y);

                                boardCopy[themoves.get(k).y][themoves.get(k).x] = boardCopy[i][j];
                                boardCopy[i][j].move(themoves.get(k).x, themoves.get(k).y, boardCopy);
                                boardCopy[i][j] = null;
                                if (inCheck(boardCopy) == 0) {
                                    // System.out.println("out of check possibility");
                                    // printBoard(boardCopy);
                                    return false;
                                }

                                boardCopy = getBoardCopy(bo);
                            }
                        }
                        // System.out.println();
                    }

                }
            }
        }

        return true;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}