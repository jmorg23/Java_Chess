package com.game.chess;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class HueShifter {

    private static int hueDegrees = 0;

    public static int getHue() {
        return hueDegrees;
    }

    public static void setHue(int hueDegrees) {
        HueShifter.hueDegrees = hueDegrees;
    }

    /**
     * Sets only the hue of the original image while preserving saturation and
     * brightness.
     *
     * @param original   The original image.
     * @param hueDegrees The target hue in degrees (0–360).
     * @return A new BufferedImage with adjusted hue.
     */
    public static BufferedImage setHue(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        float targetHue = hueDegrees / 360f;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = original.getRGB(x, y);
                int alpha = (argb >> 24) & 0xFF;

                if (alpha == 0) {
                    result.setRGB(x, y, argb); // preserve transparent pixels
                    continue;
                }

                Color color = new Color(argb, true);
                float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
                int rgb = Color.HSBtoRGB(targetHue, hsb[1], hsb[2]);

                Color newColor = new Color((rgb & 0x00FFFFFF) | (alpha << 24), true);
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }
}
