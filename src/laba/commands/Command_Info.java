package laba.commands;

import laba.com.company.Receiver;

import java.util.ArrayList;

public class Command_Info extends AbstractCommand implements Command{

    public Command_Info(){
    }

    @Override
    public ArrayList<String> execute(){
        return Receiver.info(getCollection());
    }

    @Override
    public String toString(){
        return "Info: выводит информацию о коллекции.";
    }
}
