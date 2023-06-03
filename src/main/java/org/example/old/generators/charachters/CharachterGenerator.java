package org.example.old.generators.charachters;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import workWithFiles.TxTReader;

public class CharachterGenerator {
    protected String labelName = "Test";
    protected String parameterName = "Test";
    protected String fileName = "Test";
    protected String [] usedList;
    protected List <Pocket> pocketList;
    protected List <String> unsortedList;


    public CharachterGenerator(String fileName) {
        this.fileName = fileName;
    }

    public CharachterGenerator() {
        
    }

    public String getLabelName() {
        return labelName;
    }

    public String getParameterName() {
        return parameterName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getRandomParameter (){
        if (pocketList==null) {
            connectToFile();
            makeRange(this.unsortedList);
        }
        Random random = new Random();
        return pickFromPocket(pocketList, random.nextInt(100));
    }

    public String [] getUsedList(){
        if (usedList == null) {
            List<String> unsortedList = connectToFile();
            this.pocketList = makeRange(unsortedList);
            usedList = new String[pocketList.size()];
            for (int i = 0; i < usedList.length; i++) {
                usedList[i] = pocketList.get(i).name;
            }
        }
        return usedList;

    }
    protected List <String> connectToFile(){
        TxTReader txTReader;
        String parameter = "";
        List <String> parameteres = null;
        try {
            txTReader = new TxTReader("src/main/java/workWithFiles/files/charachters/"+this.fileName);
            Scanner scanner = new Scanner(txTReader);
            parameteres = new ArrayList<String>();
            while (scanner.hasNextLine()){
                parameteres.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        unsortedList = parameteres;
        return unsortedList;
        
    }
    protected List<Pocket> makeRange (List<String> list){
        Map<String, Float> unsortedMap = new HashMap<String, Float>();
        float sum = 0; //Счетчик суммы, чтобы определить коэффицент перевода чисел в проценты
        for (String s : list) { // fill unsorted map with key and values
            String key1 = s.split(" ")[0];
            float value1 = Float.parseFloat(s.split(" ")[1]);
            sum+=value1;
            unsortedMap.put(key1, value1);
        }
        float koef = 100/sum;
        Map<String, Float> sortedMap = unsortedMap.entrySet().stream() 
        .sorted(Comparator.comparingDouble(Map.Entry::getValue))
        .collect(Collectors.toMap( 
        Map.Entry::getKey, 
        Map.Entry::getValue, 
        (a, b) -> { throw new AssertionError(); }, 
        LinkedHashMap::new 
        ));

        float rangefloor = 0;
        List<Pocket> pocketlist = new ArrayList<>();
        for (Map.Entry<String, Float> sEntry : sortedMap.entrySet()) {
            String name = sEntry.getKey();
            float range = sEntry.getValue();
            float highFloor = rangefloor+range*koef;
            Pocket p1 = new Pocket(rangefloor, highFloor, name);
            rangefloor=highFloor;
            pocketlist.add(p1);
        }
        pocketList = pocketlist;
        return pocketList;
    }
    
    protected String pickFromPocket (List<Pocket> pockets, int number){
        String findString = "";
        for (Pocket pocket : pockets) {
            if (number > pocket.floor && number <= pocket.ceiling){
                findString = pocket.name;
                break;
            }
        }
        
        return findString;
    }
    protected class Pocket {
        float floor;
        float ceiling;
        String name;
        public Pocket(float floor, float ceiling, String name) {
            this.floor = floor;
            this.ceiling = ceiling;
            this.name = name;
        }
        
        public String toString(){
            String s = String.format("Имя кармана: %s, нижняя граница: %f, верхняя граница: %f.", this.name, this.floor, this.ceiling);
            return s;
        }
    }
}
