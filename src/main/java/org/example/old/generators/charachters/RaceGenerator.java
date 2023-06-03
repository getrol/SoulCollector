package org.example.old.generators.charachters;

public class RaceGenerator extends CharachterGenerator{
    public RaceGenerator() {
        this.fileName = "Races";
        this.labelName = "Выберите расу";
        this.parameterName = "Раса";
    }
    public RaceGenerator(String fileName) {
        super(fileName);

    }
}