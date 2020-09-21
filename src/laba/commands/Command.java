package laba.commands;

import laba.com.company.CommandExecutionException;
import laba.com.company.InvalidScriptException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public interface Command extends Serializable{
    ArrayList<String> execute() throws IOException, InvalidScriptException, CommandExecutionException;
}
