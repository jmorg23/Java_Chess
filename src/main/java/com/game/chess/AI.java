package com.game.chess;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import com.game.chess.pieces.Piece;
import com.game.chess.pieces.Player;

public class AI {

    private Piece[][] curBoard;
    public boolean myTurn = false;

    private int diff = 1;

    private boolean firstMove = true;
    public boolean isWhite;

    public AI(boolean isWhite, Piece[][] curBoard, int difficulty, ArrayList<Piece> myPieces) {
        this.isWhite = isWhite;
        if (isWhite) {
            Player.whitesTurn = true;
        }
        this.curBoard = curBoard;

        diff = difficulty;
        Player.setAI(this);
        if (isWhite)
            move(myPieces);
    }

    /*
     * YOUR TURN AI
     */
    public void move(ArrayList<Piece> myPieces) {

        myTurn = true;
        if (firstMove) {

            firstMove = false;

            while (true) {

                int i = (int) (Math.random() * 8);
                int j = (int) (Math.random() * 8);
                if (curBoard[i][j] != null)
                    if (isWhite ? curBoard[i][j].color.equals("white") : curBoard[i][j].color.equals("black")) {
                        ArrayList<Point> moves = curBoard[i][j].getPossiblePlaces(curBoard);
                        if (!moves.isEmpty()) {
                            int m = (int) (Math.random() * moves.size());

                            Piece[][] con = Board.getBoardCopy();

                            con[moves.get(m).y][moves.get(m).x] = con[i][j].clone();
                            con[moves.get(m).y][moves.get(m).x].move(moves.get(m).x, moves.get(m).y, con);
                            con[i][j] = null;

                            if (validMove(con)) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                curBoard[moves.get(m).y][moves.get(m).x] = curBoard[i][j].clone();
                                curBoard[moves.get(m).y][moves.get(m).x].move(moves.get(m).x, moves.get(m).y,
                                        curBoard);
                                curBoard[i][j] = null;
                                Player.turnComplete();
                                myTurn = false;
                                break;
                            }

                        }
                    }
            }

        } else {

            if (diff == 1) {
                while (true) {

                    int i = (int) (Math.random() * 8);
                    int j = (int) (Math.random() * 8);

                    if (curBoard[i][j] != null)
                        if (isWhite ? curBoard[i][j].color.equals("white") : curBoard[i][j].color.equals("black")) {
                            ArrayList<Point> moves = curBoard[i][j].getPossiblePlaces(curBoard);
                            if (!moves.isEmpty()) {
                                int m = (int) (Math.random() * moves.size());

                                Piece[][] con = Board.getBoardCopy();

                                con[moves.get(m).y][moves.get(m).x] = con[i][j].clone();
                                con[moves.get(m).y][moves.get(m).x].move(moves.get(m).x, moves.get(m).y, con);
                                con[i][j] = null;

                                if (validMove(con)) {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    curBoard[moves.get(m).y][moves.get(m).x] = curBoard[i][j].clone();
                                    curBoard[moves.get(m).y][moves.get(m).x].move(moves.get(m).x, moves.get(m).y,
                                            curBoard);
                                    curBoard[i][j] = null;
                                    Player.turnComplete();
                                    myTurn = false;
                                    break;
                                }

                            }
                        }
                }
            } else {

                HashMap<Piece, Move> bestMoves = new HashMap<>();

                for (Piece piece : myPieces) {
                    ArrayList<Move> moves = piece.getPossibleMoves(curBoard);

                    Move bestMoveForPiece = new Move();
                    for (Move move : moves) {

                        Piece[][] con = Board.getBoardCopy();

                        con[(int) move.getPos().getY()][(int) move.getPos().getX()] = con[piece.y][piece.x].clone();
                        con[(int) move.getPos().getY()][(int) move.getPos().getX()].move((int) move.getPos().getX(),
                                (int) move.getPos().getY(), con);
                        con[piece.y][piece.x] = null;

                        if (validMove(con)) {
                            // System.out.println("move is valid");
                            move.setMoveRank(0);
                            if (move.isGetsOutOfCheck()) {
                                move.setMoveRank(1000);
                                bestMoveForPiece = move;
                                // continue;
                            } else if (move.isCanCheckMate()) {
                                move.setMoveRank(500);
                                bestMoveForPiece = move;
                                continue;

                            } else if (move.isWillBeCheckedMate()) {
                                move.setMoveRank(-1000);

                                // bestMoveForPiece = move;
                                // continue;
                                // continue;
                            } else if (move.isWillPutEnemyInCheck()) {
                                move.setMoveRank(2);
                                // System.out.println("can check opponent");
                                // System.exit(0);

                            }

                            move.setMoveRank(move.getMoveRank() + move.getPointsWorth());
                            if (move.isCanBeTaken()) {
                                move.setMoveRank(move.isCanBeTakenButRecoverForMorePoints() ? move.getMoveRank() + 4
                                        : move.getMoveRank() - piece.pointsWorth);
                                // System.out.println("This move will cause me to be able to be taken");

                                move.setMoveRank(move.getMoveRank() - piece.pointsWorth);

                            }

                            if (move.getNextPieceToTake() > 0) {
                                move.setMoveRank(move.getMoveRank() + (move.getNextPieceToTake() / 2) + 1);
                                // System.out.println("This move allows me to take another piece");
                            }
                            if (move.isBlocksPotentialDeath()) {
                                move.setMoveRank(move.getMoveRank() + piece.pointsWorth);
                            }

                            if (bestMoveForPiece.getMoveRank() <= move.getMoveRank()) {
                                // System.out.println("Therefore this is the best move for now for this piece");
                                bestMoveForPiece = move;
                            }

                        }
                    }
                    if (bestMoveForPiece.getPos() != null)
                        bestMoves.put(piece, bestMoveForPiece);

                }
                Piece pieceToMove = null;
                Move bestOverallMove = new Move();
                // HashMap<Piece, Move> topHalfMoves = new HashMap<>();

                // int doThisPiece = (int) (Math.random() * bestMoves.size());

                // int ind = 0;
                // System.out.println("\n\n\n\n\nnew piece cycle\n");
                // System.out.println("here are the best moves: "+bestMoves);
                for (Piece p : myPieces) {
                    // System.out.println("looking if the best moves contains: "+p.getPiece());

                    if (bestMoves.containsKey(p)) {
                        // System.out.println("now checking if the move is greater than:
                        // "+bestOverallMove.getMoveRank());

                        if (bestMoves.get(p).getMoveRank() >= bestOverallMove.getMoveRank()) {
                            bestOverallMove = bestMoves.get(p);
                            // System.out.println("new best move put down: "+bestOverallMove.getPos());

                            pieceToMove = p;
                        }
                    }
                    // if (diff == 5) {
                    // if (ind == doThisPiece) {
                    // bestOverallMove = bestMoves.get(p);
                    // pieceToMove = p;

                    // break;
                    // }
                    // }
                    // ind++;

                    // if (diff == 10) {
                    // topHalfMoves.put(p, bestMoves.get(p));

                    // if (topHalfMoves.size() > bestMoves.size() / 2) {
                    // Piece worstPieceMove = null;
                    // for (Entry<Piece, Move> entry : topHalfMoves.entrySet()) {
                    // Move m = entry.getValue();
                    // for (Entry<Piece, Move> e : topHalfMoves.entrySet()) {
                    // Move mo = e.getValue();

                    // if (m.getMoveRank() <= mo.getMoveRank()) {

                    // worstPieceMove = entry.getKey();
                    // }
                    // }
                    // }

                    // topHalfMoves.remove(worstPieceMove);
                    // }
                    // }
                }
                // if (diff == 10) {
                // int goAt = (int) (Math.random() * topHalfMoves.size());
                // int inde = 0;
                // for (Entry<Piece, Move> entry : topHalfMoves.entrySet()) {
                // if (inde == goAt) {
                // bestOverallMove = topHalfMoves.get(entry.getKey());
                // pieceToMove = entry.getKey();

                // break;
                // }
                // inde++;

                // }
                // }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Move Taken Stats, Move Rank: "
                        + bestOverallMove.getMoveRank() + " and Piece: " + pieceToMove.getPiece() + " and move to: "
                        + bestOverallMove.getPos().getX() + " and " + bestOverallMove.getPos().getY()
                        + "\n\nThis move gets out of check: " + bestOverallMove.isGetsOutOfCheck()
                        + "\nThis move puts the opponent in check: " + bestOverallMove.isWillPutEnemyInCheck()
                        + "\nThis move will be checkmated: " + bestOverallMove.isWillBeCheckedMate()
                        + "\nThis move can be taken: " + bestOverallMove.isCanBeTaken()
                        + "\nThis move can be taken but recover for more points: "
                        + bestOverallMove.isCanBeTakenButRecoverForMorePoints()
                        + "\nThis move blocks a potential death: " + bestOverallMove.isBlocksPotentialDeath()
                        + "\nThis move allows me to take another piece: " + bestOverallMove.getNextPieceToTake());
                Point posCon = new Point(pieceToMove.x, pieceToMove.y);
                curBoard[(int) bestOverallMove.getPos().getY()][(int) bestOverallMove.getPos()
                        .getX()] = curBoard[pieceToMove.y][pieceToMove.x].clone();
                curBoard[(int) bestOverallMove.getPos().getY()][(int) bestOverallMove.getPos().getX()]
                        .move((int) bestOverallMove.getPos().getX(), (int) bestOverallMove.getPos().getY(), curBoard);
                curBoard[posCon.y][posCon.x] = null;

                Player.turnComplete();
                myTurn = false;

            }

        }
    }

    public boolean validMove(Piece[][] board) {

        int whoCheck = Board.inCheck(board);
        // System.out.println(whoCheck);
        if (whoCheck != 0)
            if (whoCheck == 11 || (Player.whitesTurn ? whoCheck == 1 : whoCheck == 10)) {
                return false;

            }
        return true;
    }

}
