package org.example.old.generators.loot;

import static generators.loot.Rarity.*;
import static generators.loot.Tier.*;

public class EnumAdapter {

    public static String rarityToString(Rarity rarityE) {
        String rarityS = switch (rarityE) {
            case COMMON -> "Обычное";
            case RARE -> "Редкое";
            case VERY_RARE -> "Очень редкое";
            case EPIC -> "Эпическое";
            case LEGENDARY -> "Легендарное";
            case MYTHICAL -> "Мифическое";
        };
        return rarityS;
    }

    public static Rarity stringToRarity(String rarityS) throws IllegalArgumentException {
        Rarity rarityE = switch (rarityS) {
            case "Обычное" -> COMMON;
            case "Редкое" -> RARE;
            case "Очень редкое" -> VERY_RARE;
            case "Эпическое" -> EPIC;
            case "Легендарное" -> LEGENDARY;
            case "Мифическое" -> MYTHICAL;
            default -> throw new IllegalArgumentException();
        };
        return rarityE;
    }

    public static String[] raritiesToStrings(Rarity[] raritiesE) {
        String[] raritiesS = new String[raritiesE.length];
        for (int i = 0; i < raritiesE.length; i++) {
            raritiesS[i] = rarityToString(raritiesE[i]);
        }
        return raritiesS;
    }

    public static Rarity[] stringsToRarities(String[] raritiesS) {
        Rarity[] raritiesE = new Rarity[raritiesS.length];
        for (int i = 0; i < raritiesS.length; i++) {
            raritiesE[i] = stringToRarity(raritiesS[i]);
        }
        return raritiesE;
    }

    // **************************** Tier Adapter ****************************************
    public static String tierToString(Tier tierE) {
        String tierS = switch (tierE) {
            case FIRST -> "Первый";
            case SECOND -> "Второй";
            case THIRD -> "Третий";
        };
        return tierS;
    }

    public static Tier stringToTier(String tierS) throws IllegalArgumentException {
        Tier tierE = switch (tierS) {
            case "Первый" -> FIRST;
            case "Второй" -> SECOND;
            case "Третий" -> THIRD;
            default -> throw new IllegalArgumentException();
        };
        return tierE;
    }
}

