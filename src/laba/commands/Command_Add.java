package laba.commands;

import laba.com.company.Receiver;
import laba.com.company.Ticket;

import java.util.ArrayList;

/**
 Команда, добавляющая элемент в коллекцию.
 */

public class Command_Add extends AbstractCommand implements Command{

    private Ticket ticket;

    public Command_Add(Ticket ticket){
        this.ticket = ticket;
    }

    public Command_Add(){
    }

    @Override
    public ArrayList<String> execute(){
        return Receiver.add(getCollection(), this.ticket);
    }

    @Override
    public String toString(){
        return "Command Add: добавляет элемент класса Ticket в коллекцию.";
    }
}

