package org.example.old.generators.charachters;

import java.util.Random;

public class CharachterGeneratorWithoutChances extends CharachterGenerator{
    public CharachterGeneratorWithoutChances() {
        this.fileName = "Traits";
    }
    public CharachterGeneratorWithoutChances(String fileName) {
        super(fileName);
    }


    @Override
    public String getRandomParameter() {
        if (unsortedList == null){
            connectToFile();
        }
        return unsortedList.get(new Random().nextInt(unsortedList.size()));
    }

    @Override
    public String[] getUsedList() {
        if (usedList == null){
            connectToFile();
            usedList = unsortedList.toArray(new String[0]);
        }
        return usedList;
    }
}
