package com.example.exercise03;

import java.util.Objects;

public class Phone {

    String name;
    boolean selected;

    public Phone(String name) {
        this.name = name;
        this.selected = false;
    }

    public Phone(String name, boolean selected) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Phone phone = (Phone) o;
        return selected == phone.selected &&
                Objects.equals(name, phone.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, selected);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }
}
