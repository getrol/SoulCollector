package org.example.generators.help.equipment.types;

public class ReadyArmor extends ReadyEquipment {

    String armorFeatures;
    int physicalDefence;
    int magicDefence;
    int healthPoints;

    String[] enchantments;

    public Material getMaterial() {
        return material;
    }

    public String getArmorFeatures() {
        return armorFeatures;
    }

    public int getPhysicalDefence() {
        return physicalDefence;
    }

    public int getMagicDefence() {
        return magicDefence;
    }

    public int getHealthPoints() {
        return healthPoints;
    }


    public ReadyArmor(String name, Material material, String[] enchantments) {
        super(name, material);
        this.material = material;
        this.enchantments = enchantments;
        physicalDefence = material.getPhysicalDefence();
        magicDefence = material.getMagicDefence();
        healthPoints = material.getHealthPoints();
        armorFeatures = material.getArmorFeatures();
    }

    public ReadyArmor(Armor armor, Material material, String[] enchantments) {
        this(armor.name, material, enchantments);
    }

    public ReadyArmor(String name, Material material) {
        this(name, material, null);
    }

    public ReadyArmor(Armor armor, Material material) {
        this(armor.name, material, null);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(name + " из " + material.getName() + ". Броня: "
                + String.format("(%d,%d,%d)", physicalDefence, magicDefence, healthPoints));

        if (armorFeatures != null) {
            stringBuilder.append(", ").append(armorFeatures);
        }

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
