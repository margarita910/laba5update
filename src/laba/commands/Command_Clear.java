package laba.commands;

import laba.com.company.Receiver;

import java.util.ArrayList;

/**
 Команда, которая очищает коллекцию.
 */

public class Command_Clear extends AbstractCommand implements Command{

    public Command_Clear(){
    }

    @Override
    public ArrayList<String> execute(){
       return Receiver.clear(getCollection());
    }

    @Override
    public String toString(){
        return "Clear: очищает коллекцию.";
    }
}
