package org.example.generators.help;

public class EquipmentFactory {

    public static String makeArmor (Armor rawArmor, Material material){ //Считается, что поступаемый материал уже проверен на возможность быть броней
        return rawArmor.name() + " из " + material.getName() + ". Броня: "
                + String.format("(%d,%d,%d)", material.getPhysicalDefence(), material.getMagicDefence(), material.getHealthPoints())
                + ", " + material.getArmorFeatures();
    }

    public static String makeWeapon (Weapon rawWeapon, Material material){

        int weaponPhysicalDamage = Math.round(rawWeapon.getDamageByMaterial() * material.getPhysicalDamage());
        int weaponMagicDamage = Math.round(rawWeapon.getDamageByMaterial() * material.getMagicDamage());
        String distance;
        if (rawWeapon.isDistance()){
            int distanceInt = Math.round(Math.max(material.getPhysicalDamage(),material.getMagicDamage())* rawWeapon.getDistanceByMaterial());
            distance = distanceInt + " + " + rawWeapon.getBaseDistance();
        } else {
            distance = "Ближний бой";
        }


        return rawWeapon.getName() + " из " + material.getName() + ". Урон: "
                + String.format("(%d,%d)", weaponPhysicalDamage, weaponMagicDamage)
                + ", " + material.getWeaponFeatures() + ". Дальность: " + distance;
    }
}
