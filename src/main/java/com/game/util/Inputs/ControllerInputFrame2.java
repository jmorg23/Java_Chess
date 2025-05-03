package com.game.util.Inputs;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWJoystickCallback;
import org.lwjgl.system.MemoryStack;

import javax.swing.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class ControllerInputFrame2 extends JFrame {

    private long window;
    //private boolean isControllerConnected = false;

    public ControllerInputFrame2() {
        // Initialize the JFrame
        setTitle("Controller Input Example");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

        // Initialize GLFW (GLFW)
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        System.out.println("GLFW initialized successfully");

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); // We don't need a visible window
        window = GLFW.glfwCreateWindow(1, 1, "Invisible Window", 0, 0); // Create an invisible window
        if (window == 0) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        // Set the joystick callback to detect controller connection
        GLFW.glfwSetJoystickCallback(new GLFWJoystickCallback() {
            @Override
            public void invoke(int joy, int event) {
                if (event == GLFW.GLFW_CONNECTED) {
                    System.out.println("Controller " + joy + " connected");
                    //isControllerConnected = true;  // Mark controller as connected
                } else if (event == GLFW.GLFW_DISCONNECTED) {
                    System.out.println("Controller " + joy + " disconnected");
                  //  isControllerConnected = false; // Mark controller as disconnected
                }
            }
        });

        // Initialize timer to regularly update inputs
        Timer timer = new Timer(16, e -> updateControllerInput());
        timer.start();
    }

    private void updateControllerInput() {
        // Poll controller input
        try (MemoryStack stack = MemoryStack.stackPush()) {
           // IntBuffer axisCount = stack.mallocInt(1);
           // int joystickID = GLFW.GLFW_JOYSTICK_1; // Change to the correct joystick ID if needed

            // Poll for joystick connection status manually (check every frame)
            for (int joyID = GLFW.GLFW_JOYSTICK_1; joyID <= GLFW.GLFW_JOYSTICK_16; joyID++) {
                if (GLFW.glfwJoystickPresent(joyID)) {
                    // Joystick is connected, check for axis and button input


                    // Get number of axes
                    FloatBuffer axes = GLFW.glfwGetJoystickAxes(joyID);
                    if (axes != null && axes.limit() > 0) {
                        // Get axis values (e.g., left stick X, left stick Y)
                        if (axes != null && axes.limit() > 1) {
                           // float axisX = axes.get(0); // Left X axis
                           // float axisY = axes.get(1); // Left Y axis
                            // System.out.println("Joystick " + joyID + " Axis values: X=" + axisX + ", Y=" + axisY);
                        }
                    }

                    // Check for button presses
                    ByteBuffer buttons = GLFW.glfwGetJoystickButtons(joyID);
                    if (buttons != null && buttons.limit() > 0) {
                        for (int i = 0; i < buttons.limit(); i++) {
                            if (buttons.get(i) == GLFW.GLFW_PRESS) {
                                System.out.println("Button " + i + " pressed");
                            }
                        }
                    }
                } 
            }

            // Ensure events are processed, which could help with detecting controllers
            GLFW.glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControllerInputFrame2 frame = new ControllerInputFrame2();
            frame.setVisible(true);
        });
    }
}
