/*
    Садовник дает команду Машине (WaterCart) начать работу.
    Проверка состоянии клумбы
    Перемещение к клумбе
    Полив клумбы
*/
package javaapplicationrelex;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gardener {
    
    private ArrayList<WaterCart> lWaterCart = new ArrayList<>();
//    private String nameFile;
    
    public Gardener(){
        ArrayList<Flowerbed> lFlowerbeds = new ArrayList<>();
        lFlowerbeds.add(new Flowerbed("A"));
        lFlowerbeds.add(new Flowerbed("B"));
        lFlowerbeds.add(new Flowerbed("C"));
        lFlowerbeds.add(new Flowerbed("D"));
        lWaterCart.add(new WaterCart(lFlowerbeds));
    }
    
    // считывание из файла --2 задача--
    public Gardener(String nameFile){
//        this.nameFile = nameFile;
        GetFromFile(nameFile);
    }
    //--4 задача-- можно задать количество машин
    public Gardener(int countWaterCart, String nameFile){
        for (int i = 0; i < countWaterCart; i++){
            lWaterCart.add(new WaterCart());
        }
        GetFromFile(nameFile);
    }
    
    private void GetFromFile(String nameFile){
        try {
            List<String> lines = Files.readAllLines(Paths.get(nameFile), StandardCharsets.UTF_8);
            int numberCart = 0;
            for(int i = 0; i < lines.size(); i+=5){
                Sensor tmp = new Sensor(lines.get(i), lines.get(i + 1), Double.parseDouble(lines.get(i+2)), Integer.parseInt(lines.get(i+3)), Integer.parseInt(lines.get(i+4)));
                //--4задача-- работа со списком машин
                lWaterCart.get(numberCart).AddFlowerbed(tmp);
                numberCart++;
                if (numberCart >= lWaterCart.size()) numberCart = 0;
            }
        } catch (IOException ex) {
            Logger.getLogger(JavaApplicationRelex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // сохранение в файл --2 задача--
    private void SaveInformationToFile(){
        //--4задача-- работа со списком машин сбор информации о клумбах с каждой машины
        ArrayList<String> lAllInformation = new ArrayList<>();
        for(WaterCart wCart : lWaterCart){
            lAllInformation.addAll(wCart.GetAllInformation());
        }
        //запись всех данных в файл
        try {
            Files.write(Paths.get("WateringInformation"), lAllInformation);
        } catch (IOException ex) {
            Logger.getLogger(Gardener.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            Files.write(Paths.get("WateringInformation"), lWaterCart.get(0).GetAllInformation());
//        } catch (IOException ex) {
//            Logger.getLogger(Gardener.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        try {
//            Files.write(Paths.get("WateringInformation"), waterCart.GetAllInformation());
//        } catch (IOException ex) {
//            Logger.getLogger(Gardener.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public void startWorkDay(){
        while(true){
//            waterCart.checkFlorebed();
            //--4 задача-- Работа каждой машины
            for(WaterCart wCart : lWaterCart){
                wCart.checkFlorebed();
            }
//            lWaterCart.get(0).checkFlorebed();
            // --2 задача--
            SaveInformationToFile();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Gardener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
