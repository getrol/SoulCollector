package org.example.old.generators.charachters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassesGenerator extends CharachterGeneratorWithoutChances{

    public ClassesGenerator(String fileName) {
        super(fileName);
    }

    public ClassesGenerator() {
        this.fileName = "/media/getrol/PersonalWorkSpac/Накопитель душ/Механики и лут/Классы";
        this.labelName = "Выберите класс";
        this.parameterName = "Класс";
    }


    @Override
    protected List<String> connectToFile() {
        List <String> classesList = new ArrayList<>();
        classesList.add("Случайное");
        File file = new File(fileName);
        File [] dirsList = file.listFiles();
        for (File file1:dirsList) {
            classesList.addAll(Arrays.stream(file1.list())
                            .map(s -> s.replace(".xlsx", ""))
                            .collect(Collectors.toList()));
        }
        this.unsortedList = classesList;
        return classesList;
    }

}
