package com.game.chess.pieces;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.game.chess.Move;

public abstract class Piece implements Cloneable{
    public int x,y, pointsWorth;
    protected BufferedImage myImage;

    public String color;
    private static BufferedImage[] images = new BufferedImage[72];
    private static BufferedImage spriteSheet;
    private static int theme = 0;



    public static final int DEFAULT = 0;
    public static final int GLASS = 2;
    public static final int ROSE = 4;
    public static final int FALL = 6;
    public static final int OCEAN = 8;
    public static final int WINTER = 10;


    public int myPieceType;
    public static final int KING = 1;
    public static final int QUEEN = 2;
    public static final int BISHOP = 3;
    public static final int KNIGHT = 4;
    public static final int ROOK = 5;
    public static final int PAWN = 6;

    public static final int WHITE = 0;
    public static final int BLACK = 1;

    public static void setTheme(int dir) {
        try{
            theme += dir;

            if(theme > 11){
                theme = 0;
            }else if(theme < 0){
                theme = 10;
            }
        setClassResources(theme);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static int getTheme() {
        return theme;
    }


    public static void setClassResources(int theme) throws IOException{
        if(spriteSheet == null)        spriteSheet = ImageIO.read(Piece.class.getResourceAsStream("/chessSpriteSheet.png"));

        for(int i = 0+theme; i <2+theme; i++){
            for(int j = 0; j <6; j++){
                images[((i-theme) * 6) + j] = spriteSheet.getSubimage(128*j, i*128, 128, 128);
            }
        }
    }


    public Piece(int x, int y, int pieceType){
        if(spriteSheet==null)
            setTheme(0);
        this.x = x; this.y = y;

        myImage = images[pieceType-1];


        
       

    }

    public BufferedImage getPiece(int pieceType, int color){
        switch (pieceType) {
            case KING:
                return images[color == WHITE ? 11 : 5];
            case ROOK:
                return images[color == WHITE ? 9 : 3];
            case KNIGHT:
                return images[color == WHITE ? 7 : 1];
            case BISHOP:
                return images[color == WHITE ? 8 : 2];
            case PAWN:
                return images[color == WHITE ? 6 : 0];
            case QUEEN:
                return images[color == WHITE ? 10 : 4];

            default:
                return null;
        }
    }

    
    public Point getPosition() {
        return new Point(x, y);
    }
    
    public void move(int x, int y, Piece[][] b) {
        this.x = x; this.y = y;
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(getPiece(myPieceType, color.contains("white")?0:1), x * 128, y * 128, null);

    }
    public abstract ArrayList<Move> getPossibleMoves(Piece[][] board);
    public abstract ArrayList<Point> getPossiblePlaces(Piece[][] board);

    public abstract String getPiece();

    @Override
    public Piece clone() {
        try {
            return (Piece) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
    
}
