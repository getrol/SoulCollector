package org.example.generators.help.equipment.types;

public abstract class Equipment {
    String name;

    public Equipment() {
    }

    public Equipment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
