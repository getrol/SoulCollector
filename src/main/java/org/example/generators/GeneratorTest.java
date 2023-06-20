package org.example.generators;

import org.example.generators.help.equipment.loaders.EquipmentLoader;
import org.example.generators.help.equipment.loaders.MaterialLoader;
import org.example.generators.help.equipment.types.*;

public class GeneratorTest {
    public static void main(String[] args) {
        ReadyEquipmentGenerator readyEquipmentGenerator = new ReadyEquipmentGenerator(new MaterialLoader(), new EquipmentLoader());
        ReadyWeapon [] readyArmors = readyEquipmentGenerator.makeReadyWeaponsSeveralRarity(readyEquipmentGenerator.getEquipmentLoader().rawWeaponList,
              Rarity.Common, Rarity.Rare, 5);

        for (ReadyWeapon r :
                readyArmors) {
            System.out.println(r.toString());
        }
    }
}
