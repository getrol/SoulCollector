package org.example.generators;

import org.example.generators.help.*;
import org.example.generators.help.equipment.*;

import java.util.ArrayList;
import java.util.Random;

public class EquipmentGenerator {
    private MaterialLoader materialLoader;
    private EquipmentLoader equipmentLoader;


    public EquipmentGenerator(MaterialLoader materialLoader, EquipmentLoader equipmentLoader) {
        this.materialLoader = materialLoader;
        this.equipmentLoader = equipmentLoader;
    }

    public MaterialLoader getMaterialLoader() {
        return materialLoader;
    }

    public EquipmentLoader getEquipmentLoader() {
        return equipmentLoader;
    }

    public String formQueryWithRandomEquipment(Rarity rarityFrom, Rarity rarityTo, int... amount) {

        Material[] materials = materialLoader.formQueryWithoutWeapons(rarityFrom, rarityTo, amount);
        Random random = new Random();
        Armor[] rawArmors = equipmentLoader.rawArmorList;
        Weapon[] rawWeapons = equipmentLoader.rawWeaponList;

        String[] equipments = new String[materials.length];
        Rarity rarity = null;
        StringBuilder stringBuilder = new StringBuilder();

        //Для всех материалов, нагенереных.
        for (int i = 0; i < equipments.length; i++) {
            int armorOrWeapon = random.nextInt(0, 20);

            Material randomMaterial = materials[i];

            if (randomMaterial.getRarity() != rarity) {
                rarity = randomMaterial.getRarity();
                stringBuilder.append("\n").append(rarity).append(": \n");
            }
            //Создаем броню, тестовая вероятность
            if (armorOrWeapon < 5) {
                if (randomMaterial.isCanBeArmor()) {
                    Armor randomArmor = rawArmors[random.nextInt(0, rawArmors.length)];
                    equipments[i] = EquipmentFactory.makeArmor(randomArmor, randomMaterial);
                    stringBuilder.append(equipments[i]).append("\n");
                } else {
                    i--;
                }

            } else { //Или создаем оружие
                if (randomMaterial.isCanBeWeapon()) {
                    Weapon randomWeapon = rawWeapons[random.nextInt(0, rawWeapons.length)];
                    equipments[i] = EquipmentFactory.makeWeapon(randomWeapon, randomMaterial);
                    stringBuilder.append(equipments[i]).append("\n");
                } else {
                    i--;
                }
            }
        }
        return stringBuilder.toString();
    }

    //Исключаем из списков генерации часть брони. Массив теоретически должен быть в том же порядке, ведь инфа получается из одного и того же лоадера.
    private Armor[] makeArmorExcluding(boolean[] excludeArmor) {
        Armor [] rawArmor = equipmentLoader.rawArmorList;
        ArrayList<Armor> armorsResult = new ArrayList<>();
        for (int i = 0; i < rawArmor.length; i++) {
            if (!excludeArmor[i]){
                armorsResult.add(rawArmor[i]);
            }
        }
        return armorsResult.toArray(new Armor[armorsResult.size()]);
    }

    //Исключаем из списков генерации часть оружия. Массив теоретически должен быть в том же порядке, ведь инфа получается из одного и того же лоадера.
    private Weapon[] makeWeaponExcluding(boolean[] excludeWeapon) {
        Weapon [] rawWeapon = equipmentLoader.rawWeaponList;
        ArrayList<Weapon> weaponsResult = new ArrayList<>();
        for (int i = 0; i < rawWeapon.length; i++) {
            if (!excludeWeapon[i]){
                weaponsResult.add(rawWeapon[i]);
            }
        }
        return weaponsResult.toArray(new Weapon[weaponsResult.size()]);
    }




}


