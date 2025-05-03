package com.game.util.Inputs;

import java.awt.event.KeyEvent;


public class GamePadKeyboard {
    public static boolean a, b, y, x, start, menu, right, left, up, down, rright, rleft, rup, rdown, hright, hleft, hup,
            hdown,
            screenShot, lb, rb, leftTrigger, rightTrigger, leftJoy, rightJoy, xbox;

    public static KeyEvent getKeyEvent(int keyCode) {
        KeyEvent keyEvent = new KeyEvent(
                new java.awt.Component() {
                }, // Placeholder source
                KeyEvent.KEY_PRESSED, // Event type
                System.currentTimeMillis(), // Timestamp
                0, // Modifiers (e.g., shift, ctrl)
                keyCode, // Key code
                KeyEvent.getKeyText(keyCode).charAt(0), // Key char
                KeyEvent.KEY_LOCATION_STANDARD // Key location
        );

        return keyEvent;
    }

    private static void resetValues() {
        a = false;
        b = false;
        y = false;
        x = false;
        start = false;
        menu = false;
        right = false;
        left = false;
        up = false;
        down = false;
        rright = false;
        rleft = false;
        rup = false;
        rdown = false;
        hright = false;
        hleft = false;
        hup = false;
        hdown = false;
        screenShot = false;
        lb = false;
        rb = false;
        leftTrigger = false;
        rightTrigger = false;
        leftJoy = false;
        rightJoy = false;
        xbox = false;
    }

    private static int resetCon = 1;

    public static void updateProperties() {
        if (!ControllerInputFrame.controllerConnected()) {
            // System.out.println("no controllers connected");
            if (resetCon == 0) {
                resetValues();
                resetCon = 1;
            }
            return;
        }
        try {
            resetCon = 0;
            // System.out.println("Updating properties, a: " + ControllerEventHandler.a);
            if (ControllerEventHandler.a || ControllerEventHandler.rt) {
                a = true;
                press(KeyEvent.VK_SPACE);
                // System.out.println("space pressed");
            } else {
                if (a) {
                    // System.out.println("space released");
                    release(KeyEvent.VK_SPACE);
                    a = false;
                }
            }
            if (ControllerEventHandler.x) {
                // e
                press(KeyEvent.VK_E);
                x = true;
            } else {
                if (x) {
                    release(KeyEvent.VK_E);
                    x = false;
                }
            }
            if (ControllerEventHandler.y) {
                // b
                press(KeyEvent.VK_B);
                y = true;
            } else {
                if (y) {
                    release(KeyEvent.VK_B);
                    y = false;
                }
            }
            if (ControllerEventHandler.up || ControllerEventHandler.up1) {
                press(KeyEvent.VK_UP);
                up = true;
            } else {
                if (up) {
                    release(KeyEvent.VK_UP);
                    up = false;
                }

            }
            if (ControllerEventHandler.down || ControllerEventHandler.down1 || ControllerEventHandler.lt) {
                press(KeyEvent.VK_DOWN);

                down = true;
            } else {
                if (down) {
                    release(KeyEvent.VK_DOWN);
                    down = false;
                }

            }
            if (ControllerEventHandler.right || ControllerEventHandler.right1) {
                press(KeyEvent.VK_RIGHT);

                right = true;
            } else {
                if (right) {
                    release(KeyEvent.VK_RIGHT);
                    right = false;
                }

            }
            if (ControllerEventHandler.left || ControllerEventHandler.left1) {
                press(KeyEvent.VK_LEFT);

                left = true;
            } else {
                if (left) {
                    release(KeyEvent.VK_LEFT);
                    left = false;
                }

            }

            if (ControllerEventHandler.start) {
                press(KeyEvent.VK_ESCAPE);

                start = true;
            } else {
                if (start) {
                    release(KeyEvent.VK_ESCAPE);
                    start = false;
                }

            }

            // (currController.getAxisState(leftY));

            // FIRE
            // isFiring = (currController.isButtonPressed(fire));

            // ACCELERATOR
            // isAccelerating = currController.isButtonPressed(start);

            // AIM
            // isAiming = currController.isButtonPressed(inventory);

            // USE ITEM
            // a = currController.isButtonPressed(interact);

            // GO BACK
            // if (currController.isButtonPressed(back)) {
            // // controllers.quitSDLGamepad(); // ONLY WHEN GAME ENDS
            // // gameP.window.setPanel("titlePanel");

            // }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void press(int keyCode) {
        // System.out.println("true or false: "+GamePanel.getCurrentPanel());

        // System.out.println("Pressed: " + keyCode);

//        GamePanel.getCurrentPanel().keyPressed(getKeyEvent(keyCode));

    }

    public static void release(int keyCode) {
        // System.out.println("Released: " + keyCode);

//        GamePanel.getCurrentPanel().keyReleased(getKeyEvent(keyCode));

    }

    // public static void main(String[] args) {
    // try {
    // String os = System.getProperty("os.name").toLowerCase();
    // String libPath = "./native-libs/";
    // String libPath2 = "./native-libs/";

    // if (os.contains("win")) {
    // libPath += "jinput-dx8_64.dll";
    // libPath2 += "jinput-raw_64.dll";

    // } else if (os.contains("mac")) {
    // libPath += "libjinput-linux64.so";
    // } else if (os.contains("nux")) {
    // libPath += "libjinput-osx.jnlib";
    // } else {
    // throw new UnsupportedOperationException("Unsupported OS: " + os);
    // }

    // System.load(new java.io.File(libPath).getAbsolutePath());
    // System.load(new java.io.File(libPath2).getAbsolutePath());

    // System.out.println("SDL2 loaded successfully from: " + libPath);
    // // Your JInput setup code here
    // // Example:
    // // ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
    // // Controller[] controllers = ce.getControllers();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // new ControllerEventHandler();

    // // Timer t = new Timer(33, new ActionListener() {
    // // public void actionPerformed(java.awt.event.ActionEvent e) {
    // // updateProperties();
    // // }
    // // });
    // // t.start();

    // }

    /**
     * 
     * 
     * //buttons
     * /*
     * 0-a
     * 1-b
     * 2-x
     * 3-y
     * 4-lb
     * 5-rb
     * 6- menu
     * 7-start
     * 8-leftjoy
     * 9-rightjoy
     * 10- xbox
     * 11- screenshot
     * z-triggers l- -1, r- 1
     * 
     * 
     * 
     * 
     * hat switch pov - down up left right
     * 
     * 
     * 
     * 
     */
}
