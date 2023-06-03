package org.example.old.generators.charachters;

public class ProfessionGenerator extends CharachterGenerator{
    public ProfessionGenerator(String fileName) {
        super(fileName);
    }

    public ProfessionGenerator() {
        this.fileName = "Professions";
        this.labelName = "Выберите профессию";
        this.parameterName = "Профессия";
    }
}
