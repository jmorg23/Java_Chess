package com.game.util.Inputs;

import java.nio.FloatBuffer;

//import net.java.games.input.Controller;
// import net.java.games.input.ControllerEnvironment;
// import net.java.games.input.Event;
// import net.java.games.input.EventQueue;

public class ControllerEventHandler {
        public static boolean a, b, y, x, start, menu, right, left, up, down, right1, left1, up1, down1, rright, rleft,
                        rup, rdown, hright, hleft,
                        hup,
                        hdown,
                        screenShot, lb, rb, lt, rt, leftJoy, rightJoy, xbox;



        public static void pressed(String id) {
                if (id == null) {
                        return;
                }
                String id2 = id.toLowerCase();
                // buttons
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
                 * 10- xbox
                 * 11- screenshot
                 * z-triggers l- -1, r- 1
                 * 
                 * hat switch pov - down up left right
                 */
                switch (id2) {
                        case "a":
                                // System.out.println("a=true");
                                a = true;

                                break;
                        case "b":
                                b = true;
                                break;
                        case "x":
                                x = true;
                                break;
                        case "y":
                                y = true;
                                break;
                        case "lb":
                                lb = true;
                                break;
                        case "rb":
                                rb = true;
                                break;
                        case "menu":
                                menu = true;
                                break;
                        case "start":
                                start = true;
                                break;
                        case "lj":
                                leftJoy = true;
                                break;
                        case "rj":
                                rightJoy = true;
                                break;
                        case "up":
                                up1 = true;
                                break;
                        case "down":
                                down1 = true;
                                break;
                        case "left":
                                left1 = true;
                                break;
                        case "right":
                                right1 = true;
                                break;

                        case "xbox":
                                xbox = true;
                                break;
                        case "ss":
                                screenShot = true;
                                break;

                        default:
                                break;
                }
        }

        public static void released(String id) {
                if (id == null) {
                        return;
                }
                // buttons
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
                 * 10- xbox
                 * 11- screenshot
                 * z-triggers l- -1, r- 1
                 * 
                 * hat switch pov - down up left right
                 */
                String id2 = id.toLowerCase();

                switch (id2) {
                        case "a":
                                // System.out.println("a released");
                                a = false;
                                break;
                        case "b":
                                b = false;
                                break;
                        case "x":
                                x = false;
                                break;
                        case "y":
                                y = false;
                                break;
                        case "lb":
                                lb = false;
                                break;
                        case "rb":
                                rb = false;
                                break;
                        case "menu":
                                menu = false;
                                break;
                        case "start":
                                start = false;
                                break;
                        case "lj":
                                leftJoy = false;
                                break;
                        case "rj":
                                rightJoy = false;
                                break;
                        case "up":
                                up1 = false;
                                break;
                        case "down":
                                down1 = false;
                                break;
                        case "left":
                                left1 = false;
                                break;
                        case "right":
                                right1 = false;
                                break;

                        case "xbox":
                                xbox = false;
                                break;
                        case "ss":
                                screenShot = false;
                                break;
                        default:
                                break;
                }
        }

        private static final String[] xaxisMap = new String[] {
                        "x", "y", "rx", "ry", "lt", "rt"

        };
        private static final String[] psaxisMap = new String[] {
                "x", "y", "rx", "lt", "rt", "ry"

};


        public static void axisMoved(FloatBuffer axes, int con) {

                // buttons
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
                 * 10- xbox
                 * 11- screenshot
                 * z-triggers l- -1, r- 1
                 * 
                 * hat switch pov - down up left right
                 */

                for (int i = 0; i < axes.limit() ; i++) {
                        // System.out.println("Axis "+i+" : "+axes.get(i));

                        String a = con==0?xaxisMap[i]:psaxisMap[i];
                        float data = axes.get(i);

                        switch (a) {
                                case "x":

                                        right = data > 0.8;
                                        left = data < -0.8;

                                        break;
                                case "y":
                                //         System.out.println(data);
                                        down = data > 0.8;
                                        up = data < -0.8;

                                        break;
                                case "lt":
                              //  System.out.println("lt data: "+data);
                                        lt = data > 0;

                                        break;
                                case "rt":
                                //System.out.println("rt data: "+data);
                                        rt = data > 0;

                                        break;
                                case "rx":
                                //System.out.println("rx data: "+data);

                                        rright = data > 0.8;
                                        rleft = data < -0.8;
                                        break;
                                case "ry":
                                //System.out.println("ry data: "+data);

                                        rdown = data > 0.8;
                                        rup = data < -0.8;
                                        break;

                                default:
                                
                                        break;
                        }
                }
        }

        // public static void main(String[] args) {
        // System.out.println("Looking for controllers...");

        // // Get all available controllers
        // Controller[] controllers =
        // ControllerEnvironment.getDefaultEnvironment().getControllers();

        // if (controllers.length == 0) {
        // System.out.println("No controllers found!");
        // return;
        // }

        // // List available controllers
        // for (int i = 0; i < controllers.length; i++) {
        // System.out.println((i + 1) + ". " + controllers[i].getName() + " - " +
        // controllers[i].getType());
        // }

        // // Select a controller (e.g., the first game controller found)
        // Controller selectedController = null;
        // for (Controller controller : controllers) {
        // if (controller.getType() == Controller.Type.GAMEPAD || controller.getType()
        // == Controller.Type.STICK) {
        // selectedController = controller;
        // System.out.println("Using controller: " + controller.getName());
        // break;
        // }
        // }

        // if (selectedController == null) {
        // System.out.println("No game controller found!");
        // return;
        // }

        // // Poll and listen for inputs
        // while (true) {
        // // Poll the controller to refresh its state
        // if (selectedController.poll()) {
        // // Get the controller's event queue
        // EventQueue eventQueue = selectedController.getEventQueue();
        // Event event = new Event();

        // // Process all events in the queue
        // while (eventQueue.getNextEvent(event)) {
        // String componentName = event.getComponent().getName();
        // float value = event.getValue();

        // // Process input (e.g., button press or axis movement)
        // if (Math.abs(value) > 0.2f) { // Avoid noise for analog sticks
        // System.out.println("Input detected: " + componentName + " - Value: " +
        // value);
        // }
        // }
        // } else {
        // System.out.println("Controller disconnected.");
        // break;
        // }

        // // Delay to avoid excessive polling
        // try {
        // Thread.sleep(10);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }
        // }

}
