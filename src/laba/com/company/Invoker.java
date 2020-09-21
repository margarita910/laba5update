package laba.com.company;

import com.google.gson.JsonIOException;
import laba.commands.Command;

import java.io.IOException;
import java.util.ArrayList;

/**
 A class that starts any command.
 */
public class Invoker {
    private Command command;

    public Invoker(){
    }

    public void setCommand(Command command){
        this.command = command;

    }

    public ArrayList<String> executeCommand() throws IOException, IllegalArgumentException, NullPointerException, InvalidScriptException, CommandExecutionException, JSONParserException, JsonIOException {
        return command.execute();
    }

    public Command getCommand(){
        return command;
    }
}

