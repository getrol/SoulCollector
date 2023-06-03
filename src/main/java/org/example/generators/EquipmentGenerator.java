package org.example.generators;

import org.example.generators.help.*;

import java.util.Random;

public class EquipmentGenerator {
    MaterialLoader materialLoader;
    EquipmentLoader equipmentLoader;


    public EquipmentGenerator(MaterialLoader materialLoader, EquipmentLoader equipmentLoader) {
        this.materialLoader = materialLoader;
        this.equipmentLoader = equipmentLoader;
    }

    public String formQueryWithRandomEquipment(Rarity rarityFrom, Rarity rarityTo, int... amount) {

        Material[] materials = materialLoader.formQueryWithoutWeapons(rarityFrom, rarityTo, amount);
        Random random = new Random();
        Armor[] rawArmors = equipmentLoader.rawArmorList;
        Weapon[] rawWeapons = equipmentLoader.rawWeaponList;

        String[] equipments = new String[materials.length];

        //Для всех материалов, нагенереных.
        for (int i = 0; i < equipments.length; i++) {
            int armorOrWeapon = random.nextInt(0, 20);
            Material randomMaterial = materials[i];

            //Создаем броню, тестовая вероятность
            if (armorOrWeapon < 8) {
                if (randomMaterial.isCanBeArmor()){
                    Armor randomArmor = rawArmors[random.nextInt(0, rawArmors.length)];
                    equipments[i] = EquipmentFactory.makeArmor(randomArmor, randomMaterial);
                } else {
                    i--;
                }

            } else { //Или создаем оружие
                if (randomMaterial.isCanBeWeapon()){
                    Weapon randomWeapon = rawWeapons[random.nextInt(0, rawWeapons.length)];
                    equipments[i] = EquipmentFactory.makeWeapon(randomWeapon, randomMaterial);
                } else {
                    i--;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s :
                equipments) {
            stringBuilder.append(s).append("\n");
        }
        return stringBuilder.toString();
    }
}


