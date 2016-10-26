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
    
    private WaterCart waterCart;
    //private String nameFile;
    
    public Gardener(){
        ArrayList<Flowerbed> lFlowerbeds = new ArrayList<>();
        lFlowerbeds.add(new Flowerbed("A"));
        lFlowerbeds.add(new Flowerbed("B"));
        lFlowerbeds.add(new Flowerbed("C"));
        lFlowerbeds.add(new Flowerbed("D"));
        waterCart = new WaterCart(lFlowerbeds);
    }
    
    // считывание из файла --2 задача--
    public Gardener(String nameFile){
        //this.nameFile = nameFile;
        
        waterCart = new WaterCart();
        try {
            List<String> lines = Files.readAllLines(Paths.get(nameFile), StandardCharsets.UTF_8);
            for(int i = 0; i < lines.size(); i+=5){

                String name = lines.get(i);
                String strDate = lines.get(i + 1);
                double temperature = Double.parseDouble(lines.get(i+2));
                
                //--3 задача-- во входной файл добили время перещения до клумбы и вермя ее полива
                int timeMoveTo = Integer.parseInt(lines.get(i+3));
                int timeWatering = Integer.parseInt(lines.get(i+4));
                waterCart.AddFlowerbed(name, strDate, temperature, timeMoveTo, timeWatering);
            }
        } catch (IOException ex) {
            Logger.getLogger(JavaApplicationRelex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // сохранение в файл --2 задача--
    private void SaveInformationToFile(){
        
        try {
            Files.write(Paths.get("WateringInformation"), waterCart.GetAllInformation());
        } catch (IOException ex) {
            Logger.getLogger(Gardener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startWorkDay(){
        while(true){
            waterCart.checkFlorebed();
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
