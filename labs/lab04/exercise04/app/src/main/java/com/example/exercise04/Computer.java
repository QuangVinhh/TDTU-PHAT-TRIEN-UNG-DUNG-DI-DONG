package com.example.exercise04;

public class Computer {
    String name;
    boolean selected;

    public Computer(String name) {
        this.name = name;
        this.selected = false;
    }

    public Computer(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
