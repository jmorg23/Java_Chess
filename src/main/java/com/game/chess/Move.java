package com.game.chess;

import java.awt.Point;

public class Move {


    public int getMoveRank() {
        return moveRank;
    }

    public void setMoveRank(int moveRank) {
        this.moveRank = moveRank;
    }

    public boolean isBlocksPotentialDeath() {
        return blocksPotentialDeath;
    }

    public void setBlocksPotentialDeath(boolean blocksPotentialDeath) {
        this.blocksPotentialDeath = blocksPotentialDeath;
    }
    private int pointsWorth = 0;
    private boolean canCheckMate = false, willBeCheckedMate = false;
    private boolean canBeTaken = false;
    private boolean canBeTakenButRecoverForMorePoints = false;
    private int nextPieceToTake;
    private Point pos;
    private int moveRank =-999;
    private boolean blocksPotentialDeath= false;
    private boolean getsPutIntoCheck = false;
    private boolean getsOutOfCheck = false;
    private boolean willPutEnemyInCheck = false;


    public boolean isGetsOutOfCheck() {
        return getsOutOfCheck;
    }

    public void setGetsOutOfCheck(boolean getsOutOfCheck) {
        this.getsOutOfCheck = getsOutOfCheck;
    }





    public Move(int pointsWorth, boolean canCheckMate, boolean willBeCheckedMate, boolean canBeTaken,
            boolean canBeTakenButRecoverForMorePoints, int nextPieceToTake, Point pos, int moveRank,
            boolean blocksPotentialDeath, boolean getsPutIntoCheck, boolean getsOutOfCheck,
            boolean willPutEnemyInCheck) {
        this.pointsWorth = pointsWorth;
        this.canCheckMate = canCheckMate;
        this.willBeCheckedMate = willBeCheckedMate;
        this.canBeTaken = canBeTaken;
        this.canBeTakenButRecoverForMorePoints = canBeTakenButRecoverForMorePoints;
        this.nextPieceToTake = nextPieceToTake;
        this.pos = pos;
        this.moveRank = moveRank;
        this.blocksPotentialDeath = blocksPotentialDeath;
        this.getsPutIntoCheck = getsPutIntoCheck;
        this.getsOutOfCheck = getsOutOfCheck;
        this.willPutEnemyInCheck = willPutEnemyInCheck;
    }

    public Move(){

    }

    public int getPointsWorth() {
        return pointsWorth;
    }

    public int getNextPieceToTake() {
        return nextPieceToTake;
    }

    public void setNextPieceToTake(int nextPieceToTake) {
        this.nextPieceToTake = nextPieceToTake;
    }

    public void setPointsWorth(int pointsWorth) {
        this.pointsWorth = pointsWorth;
    }

    public boolean isCanCheckMate() {
        return canCheckMate;
    }

    public void setCanCheckMate(boolean canCheckMate) {
        this.canCheckMate = canCheckMate;
    }

    public boolean isWillBeCheckedMate() {
        return willBeCheckedMate;
    }

    public void setWillBeCheckedMate(boolean willBeCheckedMate) {
        this.willBeCheckedMate = willBeCheckedMate;
    }

    public boolean isCanBeTaken() {
        return canBeTaken;
    }

    public void setCanBeTaken(boolean canBeTaken) {
        this.canBeTaken = canBeTaken;
    }

    public boolean isCanBeTakenButRecoverForMorePoints() {
        return canBeTakenButRecoverForMorePoints;
    }

    public void setCanBeTakenButRecoverForMorePoints(boolean canBeTakenButRecoverForMorePoints) {
        this.canBeTakenButRecoverForMorePoints = canBeTakenButRecoverForMorePoints;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public boolean isGetsPutIntoCheck() {
        return getsPutIntoCheck;
    }

    public void setGetsPutIntoCheck(boolean getsPutIntoCheck) {
        this.getsPutIntoCheck = getsPutIntoCheck;
    }

    public boolean isWillPutEnemyInCheck() {
        return willPutEnemyInCheck;
    }

    public void setWillPutEnemyInCheck(boolean willPutEnemyInCheck) {
        this.willPutEnemyInCheck = willPutEnemyInCheck;
    }


}
