package org.example.generators;

import org.example.generators.help.*;
import org.example.generators.help.equipment.EquipmentLoader;
import org.example.generators.help.equipment.MaterialLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        MaterialLoader materialLoader = new MaterialLoader();
        EquipmentLoader equipmentLoader = new EquipmentLoader();

        EquipmentGenerator equipmentGenerator = new EquipmentGenerator(materialLoader,equipmentLoader);
        String result = equipmentGenerator.formQueryWithRandomEquipment(Rarity.Common, Rarity.Epic, 10, 15, 12, 15);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Result.txt"));
        bufferedWriter.write(result);
        bufferedWriter.close();
    }
}
