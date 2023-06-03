package org.example.generators;

import org.example.generators.help.*;

public class Test {
    public static void main(String[] args) {
        MaterialLoader materialLoader = new MaterialLoader();
        EquipmentLoader equipmentLoader = new EquipmentLoader();

        EquipmentGenerator equipmentGenerator = new EquipmentGenerator(materialLoader,equipmentLoader);
        String result = equipmentGenerator.formQueryWithRandomEquipment(Rarity.VeryRare, Rarity.VeryRare, 10);
        System.out.println(result);
    }
}
