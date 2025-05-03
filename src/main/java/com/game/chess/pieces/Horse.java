package com.game.chess.pieces;


import java.awt.Point;
import java.util.ArrayList;

import com.game.chess.Board;
import com.game.chess.Move;

public class Horse extends Piece {

    public Horse(int x, int y, String color) {
        super(x, y, color.equals("white") ? 8 : 2);
        myPieceType = KNIGHT;

        this.color = color;
        pointsWorth = 3;

    }

    @Override
    public ArrayList<Point> getPossiblePlaces(Piece[][] board) {
        ArrayList<Point> moves = new ArrayList<>();

        int[][] offsetsx = {
                { 2, 1 }, { 2, -1 },
                { 1, 2 }, { 1, -2 },
                { -1, 2 }, { -1, -2 },
                { -2, 1 }, { -2, -1 },
        };

        for (int[] offset : offsetsx) {
            int dy = offset[0];
            int dx = offset[1];
            try {
                if (board[y + dy][x + dx] == null) {
                    moves.add(new Point(x + dx, y + dy));
                } else if (color.equals("white") ? board[y + dy][x + dx].getPiece().contains("black")
                        : board[y + dy][x + dx].getPiece().contains("white")) {
                    moves.add(new Point(x + dx, y + dy));
                }
            } catch (IndexOutOfBoundsException e) {

            }

        }

        return moves;
    }

    @Override
    public String getPiece() {
        return "knight" + color;
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Piece[][] board) {
        ArrayList<Move> moves = new ArrayList<>();

        int[][] offsetsx = {
                { 2, 1 }, { 2, -1 },
                { 1, 2 }, { 1, -2 },
                { -1, 2 }, { -1, -2 },
                { -2, 1 }, { -2, -1 },
        };

        for (int[] offset : offsetsx) {
            int dy = offset[0];
            int dx = offset[1];
            try {
                if (board[y + dy][x + dx] == null) {
                    moves.add(Board.getMove(this, x + dx, y + dy, 0));

                } else if (color.equals("white") ? board[y + dy][x + dx].getPiece().contains("black")
                        : board[y + dy][x + dx].getPiece().contains("white")) {
                    moves.add(Board.getMove(this, x + dx, y + dy, board[y + dy][x + dx].pointsWorth));
                }
            } catch (IndexOutOfBoundsException e) {

            }

        }

        return moves;
    }

}
