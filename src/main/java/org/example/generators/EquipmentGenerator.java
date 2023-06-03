package org.example.generators;

import org.example.generators.help.*;

import java.util.ArrayList;
import java.util.Random;

public class EquipmentGenerator {
    MaterialLoader materialLoader;
    EquipmentLoader equipmentLoader;

    Equipment[] equipments = new Equipment[0];

    public EquipmentGenerator(MaterialLoader materialLoader, EquipmentLoader equipmentLoader) {
        this.materialLoader = materialLoader;
        this.equipmentLoader = equipmentLoader;
    }

    public String formQueryWithRandomEquipment(Rarity rarityFrom, Rarity rarityTo, int... amount) {

        Material[] materials = materialLoader.formQueryWithoutWeapons(rarityFrom, rarityTo, amount);
        Random random = new Random();
        String[] rawArmors = equipmentLoader.rawArmorList;
        ArrayList<String[]> rawWeapons = equipmentLoader.rawWeaponList;

        Equipment[] equipments = new Equipment[materials.length];
        //Для всех материалов, нагенереных.
        for (int i = 0; i < equipments.length; i++) {
            int armorOrWeapon = random.nextInt(0, 20);
            Material randomMaterial = materials[i];

            //Создаем броню
            if (armorOrWeapon < 8) {
                String randomArmor = rawArmors[random.nextInt(0, rawArmors.length)];

                Armor armor = new Armor(randomArmor, randomMaterial.getName(), randomMaterial.getArmorFeatures(),
                        String.format("(%s,%s,%s)", randomMaterial.getPhysicalDefence(), randomMaterial.getMagicDefence(), randomMaterial.getHealthPoints()));
                equipments[i] = armor;
            } else {
                String name;
                String materialName;
                String weaponFeatures;
                String damage;
                String distance;

                String[] randomRawWeapon = rawWeapons.get(random.nextInt(0, rawWeapons.size()));
                name = randomRawWeapon[0];
                materialName = randomMaterial.getName();
                weaponFeatures = randomMaterial.getWeaponFeatures();

                //Проверяем на дальний бой. Для них другая формула.
                if (randomRawWeapon[2].equals("0")) {
                    damage = String.format("(%s,%s)", randomMaterial.getPhysicalDamage(), randomMaterial.getMagicDamage());
                    distance = "Ближний бой";
                } else {
                    try {
                        int rawPhysicalDamage = Math.round(Float.parseFloat(randomMaterial.getPhysicalDamage()) * Float.parseFloat(randomRawWeapon[1]));
                        int rawMagicDamage = Math.round(Float.parseFloat(randomMaterial.getMagicDamage()) * Float.parseFloat(randomRawWeapon[1]));

                        distance = String.valueOf(Math.max(Integer.parseInt(randomMaterial.getPhysicalDamage()),
                                Integer.parseInt(randomMaterial.getMagicDamage()))
                                - Math.max(rawPhysicalDamage, rawMagicDamage));
                        distance += "+" + String.format(randomRawWeapon[2].split("@")[1]);

                        damage = String.format("(%s,%s)", rawPhysicalDamage, rawMagicDamage);
                    } catch (Exception e) {
                        e.printStackTrace();
                        damage = "Error";
                        distance = "Error";
                    }
                }
                Weapon weapon = new Weapon(name, materialName, weaponFeatures, damage, distance);
                equipments[i] = weapon;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Equipment e :
                equipments) {
            stringBuilder.append(e.getEquipment()).append("\n");
        }
        return stringBuilder.toString();
    }
}
