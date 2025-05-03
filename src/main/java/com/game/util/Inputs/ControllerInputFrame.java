package com.game.util.Inputs;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryStack;


import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class ControllerInputFrame {

    private long window;
   // private boolean isControllerConnected = false;
        // xbox buttons
        /*
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
         * 10  -up right down left
         * z-triggers l- -1, r- 1
         * 
         // ps buttons
         0 square
         1 x
         2 circle
         3 triangle
         4 lb
         5 rb
         6 lt
         7 rt
         8 left share button
         9 options button
         10 left joy
            11 right joy
            13 big square
            12 ps button
            14 tup tr td tl
            */
    private static ArrayList<Controller> connectedControllers = new ArrayList<>();
public static boolean controllerConnected(){
    return !connectedControllers.isEmpty();
}
   private static final HashMap<Integer, String> xMapping = new HashMap<>() {{
        put(0, "A");
        put(1, "B");
        put(2, "X");
        put(3, "Y");

        put(4, "LB");
        put(5, "RB");

        put(6, "Menu");
        put(7, "Start");

        put(8, "LJ");
        put(9, "RJ");

        put(10, "up");
        put(11, "right");
        put(12, "down");
        put(13, "left");
    }};
    private static final HashMap<Integer, String> psMapping = new HashMap<>() {{
        put(1, "A");
        put(2, "B");
        put(3, "Y");
        put(0, "X");

        put(4, "LB");
        put(5, "RB");

        //put(6, "LT");
        //put(7, "RT");

        put(8, "Menu");
        put(9, "Start");

        put(10, "LJ");
        put(11, "RJ");

        put(14, "up");
        put(15, "right");
        put(16, "down");
        put(17, "left");
    }};



    public ControllerInputFrame() {
        // Initialize the JFrame


        // Initialize GLFW (GLFW)
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        System.out.println("GLFW initialized successfully");

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); // We don't need a visible window
        window = GLFW.glfwCreateWindow(1, 1, "Invisible Window", 0, 0); // Create an invisible window
       // GLFW.glfwShowWindow(window);
        if (window == 0) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        // // Set the joystick callback to detect controller connection
        // GLFW.glfwSetJoystickCallback(new GLFWJoystickCallback() {
        //     @Override
        //     public void invoke(int joy, int event) {
        //         if (event == GLFW.GLFW_CONNECTED) {

        //             //System.out.println("Controller " + joy + " connected");
        //             String name = GLFW.glfwGetJoystickName(joy);
        //             System.out.println("Controller " + joy + " connected: " + name);
        //             connectedControllers.add(joy, new Controller(name, joy, identifyController(name))); // Add controller to the list
        //             System.out.println("conected controller lenght now: "+connectedControllers.size());
        //             //     isControllerConnected = true;  // Mark controller as connected
        //         } else if (event == GLFW.GLFW_DISCONNECTED) {
                
        //             System.out.println("Controller " + joy + " disconnected");

        //             //for(int i = 0; i<connectedControllers.size(); i++){
        //                // if(connectedControllers.get(i).getId()==joy){
        //             connectedControllers.remove(joy);

        //             //connectedControllers.remove(connectedControllers.get(joy)); // Add controller to the list

                
        //          //   isControllerConnected = false; // Mark controller as disconnected
        //         }
        //     }
        // });
        // new Thread(() -> {
        //     while (true) {
        //         GLFW.glfwPollEvents(); // Poll GLFW events to detect controller changes
        //         try {
        //             Thread.sleep(16); // Sleep for ~16ms (60 FPS)
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // }).start();
        // Initialize timer to regularly update inputs
    //    Timer timer = new Timer(16, e -> updateControllerInput());
    //    timer.start();
    }

    private int identifyController(String name) {
        if (name == null) return 2;
        if (name.toLowerCase().contains("xbox")) {
            return 0;
        } else if (name.toLowerCase().contains("playstation") || name.toLowerCase().contains("dualshock") || name.toLowerCase().contains("dualsense")|| name.toLowerCase().contains("wireless")) {
            return 1;
        } else {
            return 2;
        }
    }
    public void updateControllerInput() {
        // Poll controller input
        GLFW.glfwPollEvents();

        try (MemoryStack stack = MemoryStack.stackPush()) {

            // Poll for joystick connection status manually (check every frame)
            for (int joyID = GLFW.GLFW_JOYSTICK_1; joyID <= GLFW.GLFW_JOYSTICK_16; joyID++) {
                if (GLFW.glfwJoystickPresent(joyID)) {
                //System.out.println("controller: "+joyID+" is present");

                    if(connectedControllers.size()-1<joyID){

                        connectedControllers.add(joyID, new Controller(GLFW.glfwGetJoystickName(joyID), joyID, identifyController(GLFW.glfwGetJoystickName(joyID)))); // Add controller to the list
                    }


                    // Joystick is connected, check for axis and button input
                    // if (!isControllerConnected) {
                    //     System.out.println("Controller detected (reconnect)");
                    //     isControllerConnected = true;
                    // }

                    // Get number of axes
                    Controller curController = connectedControllers.get(joyID);

                    FloatBuffer axes = GLFW.glfwGetJoystickAxes(joyID);
                    if (axes != null && axes.limit() > 0) {
                        // Get axis values (e.g., left stick X, left stick Y)
                       // if (axes != null && axes.limit() > 1) {
                            //float axisX = axes.get(0); // Left X axis
                            //float axisY = axes.get(1); // Left Y axis
                            // System.out.println(axes.limit());
                            ControllerEventHandler.axisMoved(axes, curController.getControllerType());
                            // System.out.println("Joystick " + joyID + " Axis values: X=" + axisX + ", Y=" + axisY);
                        //}
                    }

                    // Check for button presses
                    ByteBuffer buttons = GLFW.glfwGetJoystickButtons(joyID);
                    //HashSet<Integer> upPressed = new HashSet<>();
                    if (buttons != null && buttons.limit() > 0) {
                        for (int i = 0; i < buttons.limit(); i++) {
                            if (buttons.get(i) == GLFW.GLFW_PRESS) {
                                ControllerEventHandler.pressed((curController.getControllerType()==0)?xMapping.get(i):psMapping.get(i));
                                // System.out.println("Button " + ((curController.getControllerType()==0)?xMapping.get(i):psMapping.get(i)) + " pressed"+" aka: "+i);

                                //System.out.println("Button " + ((curController.getControllerType()==0)?xMapping.get(i):psMapping.get(i)) + " pressed"+" ada: "+i);
                               // System.out.println("Button " + xMapping.get(i) + " pressed"+" aka: "+i);

                            }else {
                               // System.out.println("Button " + ((curController.getControllerType()==0)?xMapping.get(i):psMapping.get(i)) + " not pressed"+" aka: "+i);

                                ControllerEventHandler.released(((curController.getControllerType()==0)?xMapping.get(i):psMapping.get(i)));
                            }
                        }
                    }
                }else{
                    if(connectedControllers.size()>joyID){
                    connectedControllers.remove(joyID);
                    System.out.println("Controller " + joyID + " disconnected");
                    }

                }
                // } else {
                //     // If controller is not connected, but previously was, mark it as disconnected
                //     if (isControllerConnected) {
                //         //System.out.println("Controller " + joyID + " disconnected");
                //         isControllerConnected = false;
                //     }
                // }
            }

            // Ensure events are processed, which could help with detecting controllers
        
        } catch (Exception e) {
         System.out.println("\n\n\n\n\nuh oh\n\n\n");
        }
        
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         ControllerInputFrame frame = new ControllerInputFrame();
    //         frame.setVisible(true);
    //     });
    // }
}
