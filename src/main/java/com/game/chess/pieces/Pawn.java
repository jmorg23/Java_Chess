package com.game.chess.pieces;

import java.awt.Point;
import java.util.ArrayList;

import com.game.chess.Board;
import com.game.chess.Move;

public class Pawn extends Piece {

    public Pawn(int x, int y, String color) {
        super(x, y, color.equals("white") ? 7 : 1);
        myPieceType = PAWN;

        this.color = color;
        pointsWorth = 1;
    }



    @Override
    public ArrayList<Point> getPossiblePlaces(Piece[][] board) {
        ArrayList<Point> moves = new ArrayList<>();

        if (color.equals("white")){

        if(x<7)
        if (board[y - 1][x + 1] != null) {
            if (color.equals("white") ? board[y - 1][x + 1].getPiece().contains("black")
                    : board[y - 1][x + 1].getPiece().contains("white"))
                moves.add(new Point(x + 1, y - 1));


        }
        if(x>0)
        if (board[y - 1][x - 1] != null) {
            if (color.equals("white") ? board[y - 1][x - 1].getPiece().contains("black")
                    : board[y - 1][x - 1].getPiece().contains("white"))
                moves.add(new Point(x - 1, y - 1));

        }

        if (board[y-1][x] == null) {
            moves.add(new Point(x, y - 1));
            if (y == 6){
                if (board[y-2][x] == null) {
                    moves.add(new Point(x, y - 2));

                }
            }
        }


        }else {
            if(x<7)
            if (board[y + 1][x + 1] != null) {
                if (color.equals("white") ? board[y + 1][x + 1].getPiece().contains("black")
                        : board[y + 1][x + 1].getPiece().contains("white"))
                    moves.add(new Point(x + 1, y + 1));
    
            }
            if(x>0)
            if (board[y + 1][x - 1] != null) {
                if (color.equals("white") ? board[y + 1][x - 1].getPiece().contains("black")
                        : board[y + 1][x - 1].getPiece().contains("white"))
                    moves.add(new Point(x - 1, y + 1));
    
            }

            if (board[y+1][x] == null) {
            moves.add(new Point(x, y + 1));
            if (y == 1){
                if (board[y+2][x] == null) {
                    moves.add(new Point(x, y + 2));

                }
            }
        }
    }

        // for (Point p : moves) {
        //     System.out.println("moves: x=" + p.getX() + " and y=" + p.getY());
        // }

        return moves;
    }

    @Override
    public String getPiece() {
        return "pawn" + color;
    }
    @Override
    public void move(int x, int y, Piece[][] board){
        this.x = x; this.y = y;
        if (y==0||y==7){
            Board.updatePawn(this, board);
        }

    }
    @Override
    public ArrayList<Move> getPossibleMoves(Piece[][] board) {
        ArrayList<Move> moves = new ArrayList<>();

        if (color.equals("white")){

        if(x<7)
        if (board[y - 1][x + 1] != null) {
            if (color.equals("white") ? board[y - 1][x + 1].getPiece().contains("black")
                    : board[y - 1][x + 1].getPiece().contains("white"))
                moves.add(Board.getMove(this, x+1, y-1,  board[y-1][x+1].pointsWorth));



        }
        if(x>0)
        if (board[y - 1][x - 1] != null) {
            if (color.equals("white") ? board[y - 1][x - 1].getPiece().contains("black")
                    : board[y - 1][x - 1].getPiece().contains("white"))
                    moves.add(Board.getMove(this, x-1, y-1,  board[y-1][x-1].pointsWorth));

        }

        if (board[y-1][x] == null) {
            moves.add(Board.getMove(this, x, y-1,0));

            if (y == 6){
                if (board[y-2][x] == null) {
                    moves.add(Board.getMove(this, x, y-2,  0));

                }
            }
        }


        }else {
            if(x<7)
            if (board[y + 1][x + 1] != null) {
                if (color.equals("white") ? board[y + 1][x + 1].getPiece().contains("black")
                        : board[y + 1][x + 1].getPiece().contains("white"))
                        moves.add(Board.getMove(this, x+1, y+1,  board[y+1][x+1].pointsWorth));
    
            }
            if(x>0)
            if (board[y + 1][x - 1] != null) {
                if (color.equals("white") ? board[y + 1][x - 1].getPiece().contains("black")
                        : board[y + 1][x - 1].getPiece().contains("white"))
                        moves.add(Board.getMove(this, x-1, y+1,  board[y+1][x-1].pointsWorth));
    
            }

            if (board[y+1][x] == null) {
                moves.add(Board.getMove(this, x, y+1, 0));
                if (y == 1){
                if (board[y+2][x] == null) {
                    moves.add(Board.getMove(this, x, y+2, 0));

                }
            }
        }
    }

        // for (Point p : moves) {
        //     System.out.println("moves: x=" + p.getX() + " and y=" + p.getY());
        // }

        return moves;
        }

}
