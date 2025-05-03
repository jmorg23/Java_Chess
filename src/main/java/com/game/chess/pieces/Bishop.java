package com.game.chess.pieces;

import java.awt.Point;
import java.util.ArrayList;

import com.game.chess.Board;
import com.game.chess.Move;

public class Bishop extends Piece {

    public Bishop(int x, int y, String color) {
        super(x, y, color.equals("white") ? 9 : 3);
        myPieceType = BISHOP;

        this.color = color;
        pointsWorth = 3;

    }



    @Override
    public ArrayList<Point> getPossiblePlaces(Piece[][] board) {
        ArrayList<Point> moves = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (x + i > 7 || y - i < 0) {
                break;
            }
            if (board[y - i][x + i] == null) {
                moves.add(new Point(x + i, y - i));

            } else if (color.equals("white") ? board[y - i][x + i].getPiece().contains("black")
                    : board[y - i][x + i].getPiece().contains("white")) {
                moves.add(new Point(x + i, y - i));
                break;
            } else {
                break;
            }

        }
        for (int i = 1; i < 8; i++) {
            if (x - i < 0 || y + i > 7) {
                break;
            }
            if (board[y + i][x - i] == null) {
                moves.add(new Point(x - i, y + i));

            } else if (color.equals("white") ? board[y + i][x - i].getPiece().contains("black")
                    : board[y + i][x - i].getPiece().contains("white")) {
                moves.add(new Point(x - i, y + i));
                break;
            } else {
                break;
            }

        }
        for (int i = 1; i < 8; i++) {
            if (x - i < 0 || y - i < 0) {
                break;
            }
            if (board[y - i][x - i] == null) {
                moves.add(new Point(x - i, y - i));

            } else if (color.equals("white") ? board[y - i][x - i].getPiece().contains("black")
                    : board[y - i][x - i].getPiece().contains("white")) {
                moves.add(new Point(x - i, y - i));
                break;
            } else {
                break;
            }

        }
        for (int i = 1; i < 8; i++) {
            if (x + i > 7 || y + i > 7) {
                break;
            }
            if (board[y + i][x + i] == null) {
                moves.add(new Point(x + i, y + i));

            } else if (color.equals("white") ? board[y + i][x + i].getPiece().contains("black")
                    : board[y + i][x + i].getPiece().contains("white")) {
                moves.add(new Point(x + i, y + i));
                break;
            } else {
                break;
            }

        }

        return moves;
    }

    @Override
    public String getPiece() {
        return "bishop" + color;
    }

    @Override
    public Point getPosition() {
        return new Point(x, y);
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Piece[][] board) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (x + i > 7 || y - i < 0) {
                break;
            }
            if (board[y - i][x + i] == null) {
                moves.add(Board.getMove(this, x + i, y - i, 0));

            } else if (color.equals("white") ? board[y - i][x + i].getPiece().contains("black")
                    : board[y - i][x + i].getPiece().contains("white")) {
                moves.add(Board.getMove(this, x + i, y - i, board[y - i][x + i].pointsWorth));
                break;
            } else {
                break;
            }

        }
        for (int i = 1; i < 8; i++) {
            if (x - i < 0 || y + i > 7) {
                break;
            }
            if (board[y + i][x - i] == null) {
                moves.add(Board.getMove(this, x - i, y + i, 0));

            } else if (color.equals("white") ? board[y + i][x - i].getPiece().contains("black")
                    : board[y + i][x - i].getPiece().contains("white")) {
                moves.add(Board.getMove(this, x - i, y + i, board[y + i][x - i].pointsWorth));
                break;
            } else {
                break;
            }

        }
        for (int i = 1; i < 8; i++) {
            if (x - i < 0 || y - i < 0) {
                break;
            }
            if (board[y - i][x - i] == null) {
                moves.add(Board.getMove(this, x - i, y - i, 0));

            } else if (color.equals("white") ? board[y - i][x - i].getPiece().contains("black")
                    : board[y - i][x - i].getPiece().contains("white")) {
                moves.add(Board.getMove(this, x - i, y - i, board[y - i][x - i].pointsWorth));
                break;
            } else {
                break;
            }

        }
        for (int i = 1; i < 8; i++) {
            if (x + i > 7 || y + i > 7) {
                break;
            }
            if (board[y + i][x + i] == null) {
                moves.add(Board.getMove(this, x + i, y + i, 0));

            } else if (color.equals("white") ? board[y + i][x + i].getPiece().contains("black")
                    : board[y + i][x + i].getPiece().contains("white")) {
                moves.add(Board.getMove(this, x + i, y + i, board[y + i][x + i].pointsWorth));
                break;
            } else {
                break;
            }

        }

        return moves;
    }

}
