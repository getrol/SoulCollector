package org.example.generators.help.equipment.loaders;

import org.example.generators.help.equipment.types.Rarity;

public class RarityConverter {
    public static int rarityToInt (Rarity rarity){
        return  switch (rarity) {
            case Common -> 0;
            case Rare -> 1;
            case VeryRare -> 2;
            case Epic -> 3;
            case Master -> 4;
            case Legendary -> 5;
        };
    }

    public static Rarity intToRarity (int intRarity){
        return switch (intRarity) {
            case 0 -> Rarity.Common;
            case 1 -> Rarity.Rare;
            case 2 -> Rarity.VeryRare;
            case 3 -> Rarity.Epic;
            case 4 -> Rarity.Master;
            case 5 -> Rarity.Legendary;
            default -> throw new IllegalArgumentException();
        };
    }
}
