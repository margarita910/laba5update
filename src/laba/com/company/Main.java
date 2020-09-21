package laba.com.company;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Main class.
 */

public class Main {
    public static void main (String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NullPointerException{
        Reader reader = new Reader("C:\\Users\\пк\\IdeaProjects\\laba\\file.json");
        reader.readFile();
        Invoker invoker = new Invoker();
        while (true){
            try {
                invoker.setCommand(Reader.readCommandFromConsole());
            }
            catch (IllegalArgumentException e){
                System.out.println("Вы ввели неправильные аргументы комманды. Повторите ввод.");
                continue;
            }
            try {
                invoker.executeCommand();
            }
            catch (CommandExecutionException | InvalidScriptException e){
                System.out.println("Ошибка в чтении скрипта.");
            }
        }
    }
}
