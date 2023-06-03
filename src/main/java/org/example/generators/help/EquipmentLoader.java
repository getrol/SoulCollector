package org.example.generators.help;


import java.io.*;
import java.util.ArrayList;

public class EquipmentLoader {
    private String fileName = "C:\\Users\\79220\\IdeaProjects\\ExcelTest\\src\\main\\java\\org\\example\\generators\\help\\Weapons.txt";
    public String [] rawArmorList = {"Шлем", "Торс", "Ноги", "Щит"};

    public ArrayList<String[]> rawWeaponList = new ArrayList<>();

    public EquipmentLoader(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rawWeaponList.add(line.split(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
