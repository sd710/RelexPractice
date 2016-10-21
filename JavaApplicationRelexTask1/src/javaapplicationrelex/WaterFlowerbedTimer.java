/*
    Некий словарь, для хранения информации ключ - значение, клумба - время полива
    
*/
package javaapplicationrelex;

import java.util.Date;

public class WaterFlowerbedTimer {
    public static int index = 0;
    public String nameFlowerbed;
    public Date dateWaterFlowerbed;

    public WaterFlowerbedTimer(String nameFlowerbed, Date dateWaterFlowerbed) {
        this.nameFlowerbed = nameFlowerbed;
        this.dateWaterFlowerbed = dateWaterFlowerbed;
        WaterFlowerbedTimer.index++;
    }
}
