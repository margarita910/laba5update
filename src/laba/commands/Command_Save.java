package laba.commands;

import laba.com.company.CommandExecutionException;
import laba.com.company.Receiver;

import java.io.IOException;
import java.util.ArrayList;

/**
 Команда, которая сохраняет коллекцию в файл.
 */

public class Command_Save extends AbstractCommand implements Command{
    String Path;

    public Command_Save(String Path){
        this.Path = Path;
    }

    public Command_Save(){
    }

    @Override
    public ArrayList<String> execute() throws IOException, CommandExecutionException {
       return Receiver.save(getCollection(), this.Path);
    }

    public String getPath() {
        return Path;
    }

    @Override
    public String toString(){
        return "Save: сохраняет коллекцию в файл.";
    }
}
