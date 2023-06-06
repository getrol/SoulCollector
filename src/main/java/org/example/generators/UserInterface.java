package org.example.generators;

import org.example.generators.help.equipment.EquipmentLoader;
import org.example.generators.help.equipment.MaterialLoader;
import org.example.generators.help.RarityConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String request;
        MaterialLoader materialLoader = new MaterialLoader();
        EquipmentLoader equipmentLoader = new EquipmentLoader();

        EquipmentGenerator equipmentGenerator = new EquipmentGenerator(materialLoader,equipmentLoader);

        start:
        while (!(request = scanner.nextLine()).equals("exit")){
            System.out.println("Что вы хотите сделать?");
            System.out.println("1 - сгенерировать лут");
            if (request.equals("1")){
                System.out.println("Какова базовая редкость предмета?");
                System.out.println("0 - Обычное");
                System.out.println("1 - Редкое");
                System.out.println("2 - Очень Редкое");
                System.out.println("3 - Эпическое");
                System.out.println("4 - Мастерское");
                System.out.println("5 - Легендарное");

                int baseRarity = scanner.nextInt();
                System.out.println("Какова граничная редкость предмета?");
                System.out.println("0 - Обычное");
                System.out.println("1 - Редкое");
                System.out.println("2 - Очень Редкое");
                System.out.println("3 - Эпическое");
                System.out.println("4 - Мастерское");
                System.out.println("5 - Легендарное");

                int finishRarity = scanner.nextInt();
                System.out.printf("Введите через пробел сколько предметов каждой редкости нужно. Всего должно быть: %d ", (finishRarity-baseRarity+1));


                String numbers = scanner.nextLine();
                String [] strings = numbers.split(" ");
                int [] ints = new int[strings.length];
                for (int i = 0; i<strings.length; i++){
                    ints[i] = Integer.parseInt(strings[i]);
                }
                String result = equipmentGenerator.formQueryWithRandomEquipment(RarityConverter.intToRarity(baseRarity),
                        RarityConverter.intToRarity(finishRarity), ints);


                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Result.txt"));
                bufferedWriter.write(result);
                bufferedWriter.close();



            }

        }
    }
}
