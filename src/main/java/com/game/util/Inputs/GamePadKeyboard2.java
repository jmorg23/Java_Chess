package com.game.util.Inputs;


// import com.studiohartman.jamepad.ControllerAxis;
// import com.studiohartman.jamepad.ControllerButton;
// import com.studiohartman.jamepad.ControllerIndex;
// import com.studiohartman.jamepad.ControllerManager;
// import com.studiohartman.jamepad.ControllerUnpluggedException;


/**
 * <strong> GamePadKeyboard </strong>
 * <p>
 * Has default keybinds
 */
public class GamePadKeyboard2  {//extends GameInput
    /*
    // Controller
    ControllerManager controllers;
    // STATIC KEY BINDINGS
    
//    private static ControllerAxis rightTrigger = ControllerAxis.valueOf("TRIGGERRIGHT");
//    private static ControllerAxis leftTrigger = ControllerAxis.valueOf("TRIGGERLEFT");
//    private static ControllerAxis leftY = ControllerAxis.valueOf("LEFTY");
    
    private static ControllerAxis leftX = ControllerAxis.valueOf("LEFTX");
    private static ControllerAxis rightX = ControllerAxis.valueOf("RIGHTX");
    private static ControllerAxis rightY = ControllerAxis.valueOf("RIGHTY");
    private static ControllerButton start = ControllerButton.valueOf("START");
//    private static ControllerButton rightBumber = ControllerButton.valueOf("RIGHTBUMPER");
    private static ControllerButton buttA = ControllerButton.valueOf("A");
//    private static ControllerButton buttY = ControllerButton.valueOf("Y");
//    private static ControllerButton buttB = ControllerButton.valueOf("B");
    private static ControllerButton buttX = ControllerButton.valueOf("X");
    private static ControllerButton back = ControllerButton.valueOf("BACK");

    float lefty;
    float leftx;
    float mx;
    float my;

    // CONTROLLER CONTROL
    private int controllerIndex;
    private int previousControllerCount;
    private int currentControllerCount;
    private int controllersStart;
//    private int conts;
    ControllerIndex currController;

    // STATIC CONTROLLER CONTROL
//    private static float DEADZONE = 0.5f;

    // CONSTRUCTOR
    public GamePadKeyboard2(ControllerManager controllerManager, int controllerIndex) {
        // Controllers
        controllers = controllerManager;
        previousControllerCount = controllers.getNumControllers();
        currentControllerCount = controllers.getNumControllers();
        controllersStart = controllers.getNumControllers();
        this.controllerIndex = 0;

        // index
    }

    //

    @Override
    public void updateProperties() {

        try {
            previousControllerCount = controllers.getNumControllers();
            // GET CURRENT CONTROLLER

            currController = controllers.getControllerIndex(controllerIndex);
            //controllers.update(); // If using ControllerIndex, you should call update() to check if a new
                                  // controller
            currentControllerCount = controllers.getNumControllers();
            if (previousControllerCount > currentControllerCount) {
                // STOP GAME
                System.out.println("Controller Disconnected");
                currController.close();

            } else if (previousControllerCount < currentControllerCount) {
                currController = controllers.getControllerIndex(controllerIndex);
                System.out.println("Controller Connected");
                if (controllers.getNumControllers() == controllersStart) {
                }
            }
        //System.out.println("updating...");
            // UPDATE KEY PRESSES & SCALARS
        //    if (currController != null && currController.isConnected()) { // Check if a controller is connected
                // GET MOTION SCALES

          //      float turnVal = currController.getAxisState(leftX);
            //}
            // MOVE FORWARD
            //if (exceedsDeadzone(forwardVal, DEADZONE)) {
              //  float forwardScale = deadzoneScalar(forwardVal, DEADZONE);
                //isMovingForward = true;
            //} else {
              //  isMovingForward = false;
            //}
            // MOVE BACKWARD
            // if (exceedsDeadzone(backwardVal, DEADZONE)) {
            // float backwardScale = deadzoneScalar(backwardVal, DEADZONE);
            // isMovingBackward = true;
            // } else {
            /// isMovingBackward = false;
            /// }
            // TURN
            // if (exceedsDeadzone(turnVal, DEADZONE)) {
            // APPLY SCALE
            // float turnScale = deadzoneScalar(turnVal, DEADZONE);
            // if (turnVal > 0) {
            // TURN RIGHT
            // isMovingRight = true;
            // isMovingLeft = false;
            // } else if (turnVal < 0) {
            // TURN LEFT
            // isMovingLeft = true;
            // isMovingRight = false;
            // }
            // } else {
            // isMovingRight = false;
            // isMovingLeft = false;
            // }

            // HANDBRAKE
            if (currController.isButtonJustPressed(buttA)) {
                release(KeyEvent.VK_ENTER);
            
            }
            if (currController.isButtonPressed(buttX)) {
                // e
                release(KeyEvent.VK_E);
            }
            if ((currController.getAxisState(leftX)) > 0.2) {
                press(KeyEvent.VK_RIGHT);


            } else {
                release(KeyEvent.VK_RIGHT);

            }
            if ((currController.getAxisState(leftX)) < -0.2) {
                press(KeyEvent.VK_LEFT);

            } else {
                release(KeyEvent.VK_LEFT);

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
            if (currController.isButtonPressed(back)) {
                // controllers.quitSDLGamepad(); // ONLY WHEN GAME ENDS
                // gameP.window.setPanel("titlePanel");
                
            }

        } catch (ControllerUnpluggedException e) {
        }

    }

    public void press(int keyCode){
        GamePanel.getCurrentPanel().keyPressed(getKeyEvent(keyCode));

    }
    public void release(int keyCode){
        GamePanel.getCurrentPanel().keyReleased(getKeyEvent(keyCode));

    }
    
    
    public KeyEvent getKeyEvent(int keyCode) {
        KeyEvent keyEvent = new KeyEvent(
            new Component() {}, // Placeholder source
            KeyEvent.KEY_PRESSED, // Event type
            System.currentTimeMillis(), // Timestamp
            0, // Modifiers (e.g., shift, ctrl)
            keyCode, // Key code
            KeyEvent.getKeyText(keyCode).charAt(0), // Key char
            KeyEvent.KEY_LOCATION_STANDARD // Key location
        );
    
       return keyEvent;
    }

    public void checkButtons() {
        // try {
        updateProperties();
        // isFiring = (currController.isButtonJustPressed(fire));
        // isJumping = (currController.isButtonJustPressed(jump));
        // isPausing = (currController.isButtonJustPressed(start));
        // isInteracting = (currController.isButtonJustPressed(interact));
        // toggleInventory = (currController.isButtonJustPressed(inventory));

        // leftx = (currController.getAxisState(leftX));
        // lefty = (currController.getAxisState(LEFTY));

        // mx = (currController.getAxisState(MouseX));
        // my = (currController.getAxisState(MouseY));
        // conts = 1;

        // } catch (ControllerUnpluggedException e) {
        // conts = 0;
        // }

    }

    public boolean getisFiring() {
        return isFiring;
    }

    public double getMX() {
        return mx;
    }

    public double getMY() {
        return my;
    }

    // public boolean isInteracting() {
    // return isInteracting;
    // }

    public static float deadzoneScalar(float rawScalar, float deadzone) {
        rawScalar = Math.abs(rawScalar);
        float outScalar = 0.0f;
        if (rawScalar >= deadzone) {
            float delta = rawScalar - deadzone;
            float scale = 1 - deadzone;
            outScalar = delta / scale;
        }
        return outScalar;
    }

    public static boolean exceedsDeadzone(float rawScalarMagnitude, float deadzone) {
        return (Math.abs(rawScalarMagnitude) >= deadzone);
    }

    @Override
    public String toString() {
        return "GamePad " + controllerIndex;
    }

    
     // Transfers the focus that this panel was originally set to so keys will still
     // work.
     
    public void transferOwner(ControllerManager controllerManager) {
        this.controllers = controllerManager;
    }




    */
}
