package laba.commands;

import laba.com.company.CommandExecutionException;
import laba.com.company.Receiver;
import laba.com.company.Ticket;

import java.util.ArrayList;

/**
 Команда, которая заменяет элемент по ID.
 */

public class Command_UpdateId extends AbstractCommand implements Command{
    private Ticket ticket;
    private int value;

    public Command_UpdateId(int value, Ticket ticket){
        this.value = value;
        this.ticket = ticket;
    }

    public Command_UpdateId(){
    }

    @Override
    public ArrayList<String> execute() throws CommandExecutionException {
       return Receiver.update(getCollection(), this.value, this.ticket);
    }

    @Override
    public String toString(){
        return "UpdateId <int Id>: обновляет значение элемента коллекции, ID которого равно заданному.";
    }
}
