package laba.commands;
import laba.com.company.Receiver;

import java.util.ArrayList;

/**
 Команда, показывающая все доступные комманды.
 */

public class Command_Help extends AbstractCommand implements Command{

    public Command_Help(){
    }

    @Override
    public ArrayList<String> execute(){
        return Receiver.help();
    }

    @Override
    public String toString(){
        return "Help: показывает все доступные комманды.";
    }
}
