package com.game.chess;

import java.awt.FontFormatException;
import java.io.IOException;

import com.game.util.Sounds.SoundPlayer;

public class Driver{

    public static void main(String[] args) throws IOException, FontFormatException {
        new SoundPlayer();
        new TitleScreen();
    }
}