package laba.commands;

import laba.com.company.Receiver;

import java.util.ArrayList;

/**
 Команда, выводящая все элементы коллекции
 */

public class Command_Show extends AbstractCommand implements Command{
    public Command_Show(){
    }

    @Override
    public ArrayList<String> execute() throws NullPointerException{
        return Receiver.show(getCollection());
    }

    @Override
    public String toString(){
        return "Show: выводит все элементы коллекции";
    }
}