package laba.commands;

import laba.com.company.Receiver;

import java.util.ArrayList;

public class Command_PrintUniquePrice extends AbstractCommand implements Command {
    float price;
    public Command_PrintUniquePrice(float price){
        this.price = price;
    }

    public Command_PrintUniquePrice(){
    }

    @Override
    public ArrayList<String > execute(){
       return Receiver.printUniquePrice(getCollection(), this.price);
    }

    @Override
    public String toString(){
        return "PrintUniquePrice <float price>: выводит уникальные значения поля price всех элементов коллекции.";
    }
}
