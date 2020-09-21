package Server;

import java.io.IOException;
import java.util.Scanner;

public class Save extends Thread{
    private static String s = "";
    public void run(){
        System.out.println("Введите \"save\" если хотите сохранить коллекцию. Время ввода: 5 секунд.");
        Scanner scanner = new Scanner(System.in);
        long saveTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - saveTime < 5000){
            try{
                if (System.in.available() > 0){
                    s = scanner.nextLine();
                }
            }
            catch (IOException exp){
                exp.printStackTrace();
            }
        }
    }

    public static String getS(){
        return s;
    }
}
