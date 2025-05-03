package com.game.util.Inputs;


// import java.awt.Color;

// import java.io.Serial;
// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.List;
// import java.util.Map;
// import java.util.HashMap;

// import javax.swing.JLabel;

// import com.game.stellar.panel.ControlPanel;
/*
import net.java.games.input.*;
import net.java.games.input.Component.Identifier;

public final class ControllerEventHandler {

    public static boolean a, b, y, x, start, menu, right, left, up, down, rright, rleft, rup, rdown, hright, hleft, hup,
            hdown,
            screenShot, lb, rb, leftTrigger, rightTrigger, leftJoy, rightJoy, xbox;

    public static void pressed(Identifier identifier) {
        String id = identifier.toString();
        System.out.println(id);
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
         
        switch (id) {
            case "0":
            System.out.println("a=true");
                a = true;
                break;
            case "1":
                b = true;
                break;
            case "2":
                x = true;
                break;
            case "3":
                y = true;
                break;
            case "4":
                lb = true;
                break;
            case "5":
                rb = true;
                break;
            case "6":
                menu = true;
                break;
            case "7":
                start = true;
                break;
            case "8":
                leftJoy = true;
                break;
            case "9":
                rightJoy = true;
                break;
            case "10":
                xbox = true;
                break;
            case "11":
                screenShot = true;
                break;
            default:
                break;
        }
    }

    public static void released(Identifier identifier) {
        String id = identifier.toString();
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
         
        switch (id) {
            case "0":
            System.out.println("a false");
                a = false;
                break;
            case "1":
                b = false;
                break;
            case "2":
                x = false;
                break;
            case "3":
                y = false;
                break;
            case "4":
                lb = false;
                break;
            case "5":
                rb = false;
                break;
            case "6":
                menu = false;
                break;
            case "7":
                start = false;
                break;
            case "8":
                leftJoy = false;
                break;
            case "9":
                rightJoy = false;
                break;
            case "10":
                xbox = false;
                break;
            case "11":
                screenShot = false;
                break;
            default:
                break;
        }
    }

    private static void axisMoved(Identifier identifier, float data) {
        String id = identifier.toString();
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
            14 tup tr rd tl
        switch (id) {
            case "x":
                right = data > 0.3;
                left = data < -0.3;

                break;
            case "y":
                down = data > 0.3;
                up = data < -0.3;

                break;
            case "z":
                lb = data > 0.3;
                rb = data < -0.3;

                break;
            case "rx":
                rright = data > 0.3;
                rleft = data < -0.3;
                break;
            case "ry":
                rdown = data > 0.3;
                rup = data < -0.3;
                break;

            default:
                break;
        }
    }

    private static abstract class AxisPanel {
        @Serial
        private static final long serialVersionUID = -6200599064870672000L;
        transient Component axis;
        float data;

        public AxisPanel(Component ax) {
            axis = ax;
            // setLayout(new BorderLayout());
            // add(new JLabel(ax.getName() + "(" + ax.getIdentifier() + ")"),
            // BorderLayout.NORTH);
        }

        public void setPollData(float data) {
            this.data = data;
            // System.out.println("Daaaata: " + data);
            renderData();

        }

        public Component getAxis() {
            return axis;
        }

        protected abstract void renderData();
    }

    private static class DigitalAxisPanel extends AxisPanel {


        public DigitalAxisPanel(Component ax) {
            super(ax);
            // add(digitalState, BorderLayout.CENTER);
        }

        protected void renderData() {
            System.out.println("Data: " + data);
            Identifier identifier = axis.getIdentifier();
            String id = identifier.toString();

            if(id=="0"){
                System.out.println("data from a " +data);
            }


            if (data == 0.0f) {
                // digitalState.setBackground(getBackground());
                if(id=="0"){
                    System.out.println("releasing a");
                }
                ControllerEventHandler.released(identifier);

            } else if (data == 1.0f) {

                ControllerEventHandler.pressed(identifier);


            } else { // shoudl never happen
                System.out.print("uh oh");
                System.exit(0);
            }
            // digitalState.repaint();
        }
    }

    private static class DigitalHatPanel extends AxisPanel {

        JLabel digitalState = new JLabel("<unread>");

        public DigitalHatPanel(Component ax) {
            super(ax);
            // add(digitalState, BorderLayout.CENTER);
        }

        protected void renderData() {
            if (data == Component.POV.OFF) {
                // digitalState.setBackground(getBackground());
                digitalState.setText("OFF");
            } else if (data == Component.POV.UP) {
                digitalState.setBackground(Color.green);
                digitalState.setText("UP");
            } else if (data == Component.POV.UP_RIGHT) {
                digitalState.setBackground(Color.green);
                digitalState.setText("UP+RIGHT");
            } else if (data == Component.POV.RIGHT) {
                digitalState.setBackground(Color.green);
                digitalState.setText("RIGHT");
            } else if (data == Component.POV.DOWN_RIGHT) {
                digitalState.setBackground(Color.green);
                digitalState.setText("DOWN+RIGHT");
            } else if (data == Component.POV.DOWN) {
                digitalState.setBackground(Color.green);
                digitalState.setText("DOWN");
            } else if (data == Component.POV.DOWN_LEFT) {
                digitalState.setBackground(Color.green);
                digitalState.setText("DOWN+LEFT");
            } else if (data == Component.POV.LEFT) {
                digitalState.setBackground(Color.green);
                digitalState.setText("LEFT");
            } else if (data == Component.POV.UP_LEFT) {
                digitalState.setBackground(Color.green);
                digitalState.setText("UP+LEFT");
            } else { // shoudl never happen
                digitalState.setBackground(Color.red);
                digitalState.setText("ERR:" + data);
            }
            // digitalState.repaint();
        }
    }

    private static class AnalogAxisPanel extends AxisPanel {
        @Serial
        private static final long serialVersionUID = 7536173405896285590L;
        JLabel analogState = new JLabel("<unread>");

        public AnalogAxisPanel(Component ax) {
            super(ax);
            // add(analogState, BorderLayout.CENTER);
        }

        protected void renderData() {

            String extra = "";
            if (getAxis().getDeadZone() >= Math.abs(data))
                extra = " (DEADZONE)";
            analogState.setText("" + data + extra);
            ControllerEventHandler.axisMoved(axis.getIdentifier(), data);
            // analogState.repaint();
        }
    }

    private static class ControllerWindow {
        @Serial
        private static final long serialVersionUID = 8623977198558568961L;
        transient Controller ca;
        transient Map<Component, AxisPanel> axes_to_panels = new HashMap<>();
        boolean disabled = false;

        public ControllerWindow(Controller ca) {

            // this.setName(ca.getName());
            this.ca = ca;
            // Container c = this.getContentPane();
            // c.setLayout(new BorderLayout());
            Component[] components = ca.getComponents();
            System.out.println("Component count = " + components.length);
            // if (components.length > 0) {
            // int width = (int) Math.ceil(Math.sqrt(components.length));
            // JPanel p = new JPanel();
            // p.setLayout(new GridLayout(width, 0));
            for (int j = 0; j < components.length; j++) {
                addAxis(components[j]);
            }
            // // c.add(new JScrollPane(p), BorderLayout.CENTER);
            // }
            // setSize(400, 400);
            // setLocation(50, 50);
            // setVisible(true);
        }

        public boolean disabled() {
            return disabled;
        }

        private void setDisabled(boolean b) {
            disabled = b;
            if (!disabled) {
                // this.setTitle(ca.getName());
                System.out.println(ca.getName() + " enabled");
            } else {
                // this.setTitle(ca.getName() + " DISABLED!");
                System.out.println(ca.getName() + " disabled");
            }
            // repaint();
        }

        private void addAxis(Component ax) {
            AxisPanel p2;
            if (ax.isAnalog()) {
                p2 = new AnalogAxisPanel(ax);
            } else {
                if (ax.getIdentifier() == Component.Identifier.Axis.POV) {
                    p2 = new DigitalHatPanel(ax);
                } else {
                    p2 = new DigitalAxisPanel(ax);
                }
            }
            // p.add(p2);
            axes_to_panels.put(ax, p2);
        }

        public void poll() {
            if (!ca.poll()) {
                if (!disabled()) {
                    setDisabled(true);
                }

                return;
            }
            if (disabled()) {
                setDisabled(false);
            }
            EventQueue event_queue = ca.getEventQueue();
            Event event = new Event();
            while (event_queue.getNextEvent(event)) {
                AxisPanel panel = axes_to_panels.get(event.getComponent());
                panel.setPollData(event.getValue());
            }
        }
    }

    static final long HEARTBEATMS = 100; // 10th of a second
    transient List<ControllerWindow> controllers = new ArrayList<>();

    public boolean controllerConnected = false;

    public static ControllerEnvironment ce;

    public ControllerEventHandler() {
        // super("Controller Event Test. Version: " + Version.getVersion());
        try{
        ce = ControllerEnvironment.getDefaultEnvironment();
        Controller[] ca = ce.getControllers();
        if(ca == null){
            return;
        }
        for (int i = 0; i < ca.length; i++) {
            makeController(ca[i]);
        }
        controllerConnected = controllers.size()>0;

        new Thread(() -> {
            try {
                while (true) {
                    for (Iterator<ControllerWindow> i = controllers.iterator(); i.hasNext();) {
                        try {
                            ControllerWindow con = i.next();
                            if (con.disabled()) {
                                controllers.remove(con);
                            } else
                                con.poll();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Thread.sleep(HEARTBEATMS);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        ControlPanel.loadedLibs = true;
    }catch(Exception e){
        System.out.println(e);
    }
        // pack();
        // setSize(400,400);
        // setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // setVisible(true);
    }

    private boolean makeController(Controller c) {
        Controller[] subControllers = c.getControllers();
        if (subControllers.length == 0) {
            return createControllerWindow(c);
        }
        return false;
        // else {
        // for(int i=0;i<subControllers.length;i++){
        // makeController(subControllers[i]);
        // }
        // }
    }

    private boolean createControllerWindow(Controller c) {
        if (c.getType() == Controller.Type.GAMEPAD) {
            controllers.add(new ControllerWindow(c));
            return true;
        }
        return false;
    }

    public boolean gamePadConnect() {
        try{
        // check for new connections
        //ce = ControllerEnvironment.getDefaultEnvironment();
        Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();

        boolean b = false;
        for (int i = 0; i < ca.length; i++) {
            if (makeController(ca[i])) {
                b = true;
            }
        }
        controllerConnected = controllers.size()>0;
        return b;
    }catch(Exception e){
        return false;
    }

    }

}

*/