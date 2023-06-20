package org.example.generators.help.equipment;

public class ReadyArmor extends Armor {

    Material material;

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

    public ReadyArmor(String name) {
        super(name);
    }

    public ReadyArmor(String name, Material material, String[] enchantments) {
        super(name);
        this.material = material;
        this.enchantments = enchantments;
    }

    public ReadyArmor(Armor armor, Material material, String[] enchantments) {
        super(armor.name);
        this.material = material;
        this.enchantments = enchantments;
    }

    public ReadyArmor(String name, Material material) {
        super(name);
        this.material = material;
    }

    public ReadyArmor(Armor armor, Material material) {
        super(armor.name);
        this.material = material;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(name + " из " + material.getName() + ". Броня: "
                + String.format("(%d,%d,%d)", physicalDefence, magicDefence, healthPoints)
                + ", " + armorFeatures);
        if (enchantments != null) {
            stringBuilder.append('\n').append("Зачарование: \n");
            for (int i = 0; i < enchantments.length; i++) {
                stringBuilder.append(i).append(". ").append(enchantments[i]).append("\n");
            }

        }
        return stringBuilder.toString();
    }
}
