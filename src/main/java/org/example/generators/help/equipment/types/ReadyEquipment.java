package org.example.generators.help.equipment.types;

public class ReadyEquipment extends Equipment{

    Material material;

    String requirements;
    public ReadyEquipment(String name, Material material) {
        super(name);
        requirements = switch (material.getRarity()){

            case Common -> "Потенциал: 0";
            case Rare -> "Потенциал: 1-2";
            case VeryRare -> "Потенциал: 3-4";
            case Epic -> "Потенциал: 5-6";
            case Master -> "Потенциал: 7-8";
            case Legendary -> "Потенциал: 9-10";
        };
    }
}
