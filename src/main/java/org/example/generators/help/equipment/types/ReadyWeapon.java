package org.example.generators.help.equipment.types;

public class ReadyWeapon extends ReadyEquipment {


    String armorFeatures;
    int physicalDamage;
    int magicDamage;

    String distance;

    String[] enchantments;

    public ReadyWeapon(Weapon weapon, Material material) {
        super(weapon.name, material);
        this.material = material;
        armorFeatures = material.getWeaponFeatures();
        physicalDamage = Math.round(weapon.getDamageByMaterial() * material.getPhysicalDamage());
        magicDamage = Math.round(weapon.getDamageByMaterial() * material.getMagicDamage());

        if (weapon.isDistance) {
            distance = "Дальность: " + Math.round(weapon.getDistanceByMaterial() * Math.max(material.getMagicDamage(), material.getPhysicalDamage()))
                    + " + " + weapon.baseDistance;
        } else {
            distance = "Ближний бой";
        }
    }

    public ReadyWeapon(Weapon weapon, Material material, String[] enchantments) {
        this(weapon, material);
        this.enchantments = enchantments;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(name + " из " + material.getName() + ". Урон: "
                + String.format("(%d,%d)", physicalDamage, magicDamage)
                + ", " + armorFeatures + ". " + distance);

        stringBuilder.append(". ").append(requirements);

        if (enchantments != null) {
            stringBuilder.append('\n').append("Зачарование: \n");
            for (int i = 0; i < enchantments.length; i++) {
                stringBuilder.append(i).append(". ").append(enchantments[i]).append("\n");
            }

        }
        return stringBuilder.toString();
    }
}
