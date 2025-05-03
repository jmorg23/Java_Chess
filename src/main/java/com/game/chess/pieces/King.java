package com.game.chess.pieces;


import java.awt.Point;
import java.util.ArrayList;

import com.game.chess.Board;
import com.game.chess.Move;

public class King extends Piece {

    public boolean canCastle = true;
    public boolean willCastle = false;
    public ArrayList<Point> castlePoints;

    public King(int x, int y, String color) {
        super(x, y, color.equals("white") ? 12 : 6);
        myPieceType = KING;
        this.color = color;
        pointsWorth = 3;

    }

    @Override
    public void move(int x, int y, Piece[][] b) {
        this.x = x; this.y = y;
        canCastle = false;
        if(willCastle){
            if(castlePoints.contains(new Point(x,y))){
                if(x==1&&y==0)
                Board.castle(b, 1);
                else if(x==6&&y==0)
                Board.castle(b, 2);
                else if(x==1&&y==7)
                Board.castle(b, 3);
                else if(x==6&&y==7)
                Board.castle(b, 4);
            }
        }
        willCastle = false;
        
    }


    @Override
    public ArrayList<Point> getPossiblePlaces(Piece[][] board) {
        ArrayList<Point> moves = new ArrayList<>();
        if (x + 1 <= 7)
            if (board[y][x + 1] == null) {
                moves.add(new Point(x + 1, y));

            } else if (color.equals("white") ? board[y][x + 1].getPiece().contains("black")
                    : board[y][x + 1].getPiece().contains("white")) {
                moves.add(new Point(x + 1, y));
            }

        if (x - 1 >= 0) {

            if (board[y][x - 1] == null) {
                moves.add(new Point(x - 1, y));
            } else if (color.equals("white") ? board[y][x - 1].getPiece().contains("black")
                    : board[y][x - 1].getPiece().contains("white")) {

                moves.add(new Point(x - 1, y));
            }
        }

        if (y + 1 <= 7)
            if (board[y + 1][x] == null) {
                moves.add(new Point(x, y + 1));

            } else if (color.equals("white") ? board[y + 1][x].getPiece().contains("black")
                    : board[y + 1][x].getPiece().contains("white")) {

                moves.add(new Point(x, y + 1));
            }

        if (y - 1 >= 0)
            if (board[y - 1][x] == null) {
                moves.add(new Point(x, y - 1));
            } else if (color.equals("white") ? board[y - 1][x].getPiece().contains("black")
                    : board[y - 1][x].getPiece().contains("white")) {

                moves.add(new Point(x, y - 1));
            }

        if (x + 1 <= 7 && y - 1 >= 0)
            if (board[y - 1][x + 1] == null) {
                moves.add(new Point(x + 1, y - 1));

            } else if (color.equals("white") ? board[y - 1][x + 1].getPiece().contains("black")
                    : board[y - 1][x + 1].getPiece().contains("white")) {
                moves.add(new Point(x + 1, y - 1));
            }

        if (x - 1 >= 0 && y + 1 <= 7)
            if (board[y + 1][x - 1] == null) {
                moves.add(new Point(x - 1, y + 1));

            } else if (color.equals("white") ? board[y + 1][x - 1].getPiece().contains("black")
                    : board[y + 1][x - 1].getPiece().contains("white")) {
                moves.add(new Point(x - 1, y + 1));
            }

        if (x - 1 >= 0 && y - 1 >= 0)
            if (board[y - 1][x - 1] == null) {
                moves.add(new Point(x - 1, y - 1));

            } else if (color.equals("white") ? board[y - 1][x - 1].getPiece().contains("black")
                    : board[y - 1][x - 1].getPiece().contains("white")) {
                moves.add(new Point(x - 1, y - 1));
            }

        if (x + 1 <= 7 && y + 1 <= 7)
            if (board[y + 1][x + 1] == null) {
                moves.add(new Point(x + 1, y + 1));

            } else if (color.equals("white") ? board[y + 1][x + 1].getPiece().contains("black")
                    : board[y + 1][x + 1].getPiece().contains("white")) {
                moves.add(new Point(x + 1, y + 1));

            }

        if (canCastle) {
            castlePoints = new ArrayList<>();
            if (color.equals("white")) {
                if ((board[7][1] == null) && (board[7][2] == null) && (board[7][3] == null)
                        && (board[7][0] instanceof Rook)) {
                    if (((Rook) board[7][0]).canCastle) {
                        moves.add(new Point(1, 7));
                        willCastle = true;
                        castlePoints.add(new Point(1, 7));
                    }
                }
                if ((board[7][5] == null) && (board[7][6] == null) && (board[7][7] instanceof Rook)) {
                    if (((Rook) board[7][7]).canCastle) {
                        moves.add(new Point(6, 7));
                        willCastle = true;
                        castlePoints.add(new Point(6, 7));

                    }
                }

            } else {

                if ((board[0][1] == null) && (board[0][2] == null) && (board[0][3] == null)
                        && (board[0][0] instanceof Rook)) {
                    if (((Rook) board[0][0]).canCastle) {
                        moves.add(new Point(1, 0));
                        willCastle = true;
                        castlePoints.add(new Point(1, 0));

                    }
                }
                if ((board[0][5] == null) && (board[0][6] == null) && (board[0][7] instanceof Rook)) {
                    if (((Rook) board[0][7]).canCastle) {
                        moves.add(new Point(6, 0));
                        willCastle = true;
                        castlePoints.add(new Point(6, 0));

                    }
                }

            }
            // 12356}
        }

        return moves;

    }

    @Override
    public String getPiece() {
        return "king" + color;
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Piece[][] board) {
        ArrayList<Move> moves = new ArrayList<>();
        if (x + 1 <= 7)
            if (board[y][x + 1] == null) {
                moves.add(Board.getMove(this, x+1, y,  0));

            } else if (color.equals("white") ? board[y][x + 1].getPiece().contains("black")
                    : board[y][x + 1].getPiece().contains("white")) {
                        moves.add(Board.getMove(this, x+1, y,  board[y][x+1].pointsWorth));
                    }

        if (x - 1 >= 0) {

            if (board[y][x - 1] == null) {
                moves.add(Board.getMove(this, x-1, y,  0));
            } else if (color.equals("white") ? board[y][x - 1].getPiece().contains("black")
                    : board[y][x - 1].getPiece().contains("white")) {

                        moves.add(Board.getMove(this, x-1, y,  board[y][x-1].pointsWorth));
            }
        }

        if (y + 1 <= 7)
            if (board[y + 1][x] == null) {
                moves.add(Board.getMove(this, x, y+1, 0));

            } else if (color.equals("white") ? board[y + 1][x].getPiece().contains("black")
                    : board[y + 1][x].getPiece().contains("white")) {

                        moves.add(Board.getMove(this, x, y+1,  board[y+1][x].pointsWorth));
            }

        if (y - 1 >= 0)
            if (board[y - 1][x] == null) {
                moves.add(Board.getMove(this, x, y-1, 0));
            } else if (color.equals("white") ? board[y - 1][x].getPiece().contains("black")
                    : board[y - 1][x].getPiece().contains("white")) {

                        moves.add(Board.getMove(this, x, y-1,  board[y-1][x].pointsWorth));
            }

        if (x + 1 <= 7 && y - 1 >= 0)
            if (board[y - 1][x + 1] == null) {
                moves.add(Board.getMove(this, x+1, y-1, 0));

            } else if (color.equals("white") ? board[y - 1][x + 1].getPiece().contains("black")
                    : board[y - 1][x + 1].getPiece().contains("white")) {
                        moves.add(Board.getMove(this, x+1, y-1,  board[y-1][x+1].pointsWorth));
                    }

        if (x - 1 >= 0 && y + 1 <= 7)
            if (board[y + 1][x - 1] == null) {
                moves.add(Board.getMove(this, x-1, y+1,  0));

            } else if (color.equals("white") ? board[y + 1][x - 1].getPiece().contains("black")
                    : board[y + 1][x - 1].getPiece().contains("white")) {
                        moves.add(Board.getMove(this, x-1, y+1,  board[y+1][x-1].pointsWorth));
                    }

        if (x - 1 >= 0 && y - 1 >= 0)
            if (board[y - 1][x - 1] == null) {        
                moves.add(Board.getMove(this, x-1, y-1,  0));
        


            } else if (color.equals("white") ? board[y - 1][x - 1].getPiece().contains("black")
                    : board[y - 1][x - 1].getPiece().contains("white")) {
                        moves.add(Board.getMove(this, x-1, y-1,  board[y-1][x-1].pointsWorth));
                    }

        if (x + 1 <= 7 && y + 1 <= 7)
            if (board[y + 1][x + 1] == null) {
                moves.add(Board.getMove(this, x+1, y+1, 0));

            } else if (color.equals("white") ? board[y + 1][x + 1].getPiece().contains("black")
                    : board[y + 1][x + 1].getPiece().contains("white")) {
                        moves.add(Board.getMove(this, x+1, y+1,  board[y+1][x+1].pointsWorth));

            }

        if (canCastle) {
            castlePoints = new ArrayList<>();
            if (color.equals("white")) {
                if ((board[7][1] == null) && (board[7][2] == null) && (board[7][3] == null)
                        && (board[7][0] instanceof Rook)) {
                    if (((Rook) board[7][0]).canCastle) {

                        moves.add(Board.getMove(this, 1, 7, 0));


                        willCastle = true;
                        castlePoints.add(new Point(1, 7));

                    }
                }
                if ((board[7][5] == null) && (board[7][6] == null) && (board[7][7] instanceof Rook)) {
                    if (((Rook) board[7][7]).canCastle) {
                        moves.add(Board.getMove(this, 6, 7, 0));
                        willCastle = true;
                        castlePoints.add(new Point(6, 7));

                    }
                }

            } else {

                if ((board[0][1] == null) && (board[0][2] == null) && (board[0][3] == null)
                        && (board[0][0] instanceof Rook)) {
                    if (((Rook) board[0][0]).canCastle) {
                        moves.add(Board.getMove(this, 1, 0, 0));
                        willCastle = true;
                        castlePoints.add(new Point(1, 0));

                    }
                }
                if ((board[0][5] == null) && (board[0][6] == null) && (board[0][7] instanceof Rook)) {
                    if (((Rook) board[0][7]).canCastle) {
                        moves.add(Board.getMove(this, 6, 0, 0));
                        willCastle = true;
                        castlePoints.add(new Point(6, 0));

                    }
                }

            }
            // 12356}
        }

        return moves;    }

}
