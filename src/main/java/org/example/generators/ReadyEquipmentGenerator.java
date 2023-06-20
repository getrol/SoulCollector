package org.example.generators;

import org.example.generators.help.equipment.loaders.EquipmentLoader;
import org.example.generators.help.equipment.loaders.MaterialLoader;
import org.example.generators.help.equipment.loaders.RarityConverter;
import org.example.generators.help.equipment.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ReadyEquipmentGenerator {
    private final MaterialLoader materialLoader;
    private final EquipmentLoader equipmentLoader;

    private final Random random;


    public ReadyEquipmentGenerator(MaterialLoader materialLoader, EquipmentLoader equipmentLoader) {
        this.materialLoader = materialLoader;
        this.equipmentLoader = equipmentLoader;
        random = new Random();
    }

    public MaterialLoader getMaterialLoader() {
        return materialLoader;
    }

    public EquipmentLoader getEquipmentLoader() {
        return equipmentLoader;
    }


    //Создаем Х готовой брони при заданной редкости
    public ReadyArmor[] makeReadyArmorsOneRarity(Armor[] armors, Rarity rarity, int amount) {
        //Получаем все материалы заданной редкости и дополнительно отсеиваем те, которые не могут быть броней.
        Material[] materialsWithOneRarityCanBeArmor = getMaterialsCanBeArmor(materialLoader.getMaterialsByRarity().get(rarity));
        ReadyArmor[] readyArmors = new ReadyArmor[amount];
        for (int i = 0; i < amount; i++) {
            readyArmors[i] = new ReadyArmor(
                            armors[random.nextInt(0, armors.length)],
                            materialsWithOneRarityCanBeArmor[random.nextInt(0, materialsWithOneRarityCanBeArmor.length)]);
        }
        return readyArmors;
    }

    //Создаем Х готового оружия при заданной редкости
    public ReadyWeapon[] makeReadyWeaponOneRarity(Weapon[] weapons, Rarity rarity, int amount) {
        //Получаем все материалы заданной редкости и дополнительно отсеиваем те, которые не могут быть оружием.
        Material[] materialsWithOneRarityCanBeWeapon = getMaterialsCanBeWeapon(materialLoader.getMaterialsByRarity().get(rarity));
        ReadyWeapon[] readyWeapons = new ReadyWeapon[amount];
        for (int i = 0; i < amount; i++) {
            readyWeapons[i] = new ReadyWeapon(
                    weapons[random.nextInt(0, weapons.length)],
                    materialsWithOneRarityCanBeWeapon[random.nextInt(0, materialsWithOneRarityCanBeWeapon.length)]);
        }
        return readyWeapons;
    }

    //Создаем Х готовой брони при редкости, заданной пользователем
    public ReadyArmor[] makeReadyArmorsSeveralRarity(Armor[] armors, Rarity [] rarities, int amount) {

        //Получаем все материалы заданной редкости и дополнительно отсеиваем те, которые не могут быть броней.
        Material[][] allMaterials = new Material[rarities.length][];
        HashMap<Rarity, Material[]> rarityHashMap = materialLoader.getMaterialsByRarity();
        for (int i = 0; i < rarities.length; i++) {
            allMaterials[i] = getMaterialsCanBeArmor(rarityHashMap.get(rarities[i]));
        }

        ReadyArmor [] readyArmors = new ReadyArmor[rarities.length*amount];

        //Заполняем экипу каждой редкости одинаковым количеством.
        for (int i = 0; i < rarities.length; i++) {
            for (int j = 0; j < amount; j++) {
                readyArmors[i*amount+j] = new ReadyArmor(armors[random.nextInt(0, armors.length)],
                        allMaterials[i][random.nextInt(0, allMaterials[i].length)]);
            }
        }

        return readyArmors;
    }

    //Создаем Х готового оружия при редкости, заданной пользователем
    public ReadyWeapon[] makeReadyWeaponsSeveralRarity(Weapon[] weapons, Rarity [] rarities, int amount) {

        //Получаем все материалы заданной редкости и дополнительно отсеиваем те, которые не могут быть оружием.
        Material[][] allMaterials = new Material[rarities.length][];
        HashMap<Rarity, Material[]> rarityHashMap = materialLoader.getMaterialsByRarity();
        for (int i = 0; i < rarities.length; i++) {
            allMaterials[i] = getMaterialsCanBeWeapon(rarityHashMap.get(rarities[i]));
        }

        ReadyWeapon [] readyWeapons = new ReadyWeapon[rarities.length*amount];

        //Заполняем экипу каждой редкости одинаковым количеством.
        for (int i = 0; i < rarities.length; i++) {
            for (int j = 0; j < amount; j++) {
                readyWeapons[i*amount+j] = new ReadyWeapon(weapons[random.nextInt(0, weapons.length)],
                        allMaterials[i][random.nextInt(0, allMaterials[i].length)]);
            }
        }

        return readyWeapons;
    }


    //Создаем Х готовой брони при диапазоне редкости (считаем редкость и пробрасываем метод)
    public ReadyArmor[] makeReadyArmorsSeveralRarity(Armor[] armors, Rarity rarityFrom, Rarity rarityTo, int amount) {
        int iRarityFrom = RarityConverter.rarityToInt(rarityFrom);
        int iRarityTo = RarityConverter.rarityToInt(rarityTo);
        int abs = Math.abs(iRarityTo-iRarityFrom)+1;
        int min = Math.min(iRarityFrom,iRarityTo);

        Rarity [] rarities = new Rarity[abs];
        for (int i = 0; i < abs; i++) {
            rarities[i] = RarityConverter.intToRarity(min+i);
        }
        return makeReadyArmorsSeveralRarity(armors,rarities,amount);
    }

    //Создаем Х готового оружия при диапазоне редкости (считаем редкость и пробрасываем метод)
    public ReadyWeapon[] makeReadyWeaponsSeveralRarity(Weapon[] weapons, Rarity rarityFrom, Rarity rarityTo, int amount) {
        int iRarityFrom = RarityConverter.rarityToInt(rarityFrom);
        int iRarityTo = RarityConverter.rarityToInt(rarityTo);
        int abs = Math.abs(iRarityTo-iRarityFrom)+1;
        int min = Math.min(iRarityFrom,iRarityTo);

        Rarity [] rarities = new Rarity[abs];
        for (int i = 0; i < abs; i++) {
            rarities[i] = RarityConverter.intToRarity(min+i);
        }
        return makeReadyWeaponsSeveralRarity(weapons,rarities,amount);
    }





    //Группа методов для того, чтобы отсеить из материалов те, которые не подходят для оружия/брони
    private Material[] getMaterialsCanBeArmor(Material[] materials) {
        ArrayList<Material> arrayList = new ArrayList<>();
        for (Material material :
                materials) {
            if (material.isCanBeArmor()) {
                arrayList.add(material);
            }
        }
        return arrayList.toArray(new Material[0]);
    }

    private Material[] getMaterialsCanBeWeapon(Material[] materials) {
        ArrayList<Material> arrayList = new ArrayList<>();
        for (Material material :
                materials) {
            if (material.isCanBeWeapon()) {
                arrayList.add(material);
            }
        }
        return arrayList.toArray(new Material[0]);
    }


}


