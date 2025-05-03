package com.game.util.Inputs;

public class Controller {
    private String name;
    private int id;
    private int controllerType; // 0 for xbox, 1 for ps, 2 for other
    public Controller(String name, int id, int controllerType) {
        this.name = name;
        this.id = id;
        this.controllerType = controllerType;
        System.out.println("New Controller Created of Name: "+name+" and Type:"+controllerType);

    }
    public String getName() {
        return name;
    }
    public int getControllerType() {
        return controllerType;
    }
    public void setControllerType(int controllerType) {
        this.controllerType = controllerType;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    

}
