package laba.commands;

import laba.com.company.Receiver;

import java.util.ArrayList;

/**
 Команда, осуществляющая выход из программы.
 */

public class Command_Exit extends AbstractCommand implements Command{

    public Command_Exit(){
    }

    @Override
    public ArrayList<String > execute(){
        return Receiver.exit();
    }

    @Override
    public String toString(){
        return "Exit: осуществляет выход из программы без сохранения изменений.";
    }
}
