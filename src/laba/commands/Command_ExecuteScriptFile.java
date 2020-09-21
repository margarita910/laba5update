package laba.commands;

import laba.com.company.CommandExecutionException;
import laba.com.company.InvalidScriptException;
import laba.com.company.Receiver;
import java.io.IOException;
import java.util.ArrayList;

public class Command_ExecuteScriptFile extends AbstractCommand implements Command {

    private String Path;

    public Command_ExecuteScriptFile(String Path){
        this.Path = Path;
    }

    public Command_ExecuteScriptFile(){
    }

    public String getPath() {
        return Path;
    }

    @Override
    public ArrayList<String> execute() throws IOException, IllegalArgumentException, InvalidScriptException, CommandExecutionException {
        ArrayList<String> str = Receiver.readCommandFromFile(this.Path);
        return str;
    }

    @Override
    public String toString(){
        return "ExecuteScriptFile <file_name>: исполняет скрипт.";
    }
}