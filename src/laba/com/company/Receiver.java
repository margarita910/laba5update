package laba.com.company;

import com.google.gson.JsonIOException;
import laba.commands.AbstractCommand;
import laba.commands.Command;
import laba.commands.Command_ExecuteScriptFile;
import laba.commands.*;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.google.gson.Gson;

import static java.util.Collections.*;

/**
 * Receiver.
 */

public class Receiver {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    static ArrayList<String> scriptNames = new ArrayList<>();
    public static int countExecute;

    /*public static ArrayList<String> getScriptNames(){
        return scriptNames;
    }*/

    public static ArrayList<String> add(Vector<Ticket> collection, Ticket ticket) throws JsonIOException {
        collection.add(ticket);
        ArrayList<String> str = new ArrayList<>();
        str.add("Ticket добавлен в коллекцию.");
        return str;
    }

    public static ArrayList<String> help(){
        ArrayList<String> str = new ArrayList<>();
        int number = 1;
        str.add("Доступные команды: ");
        for (AbstractCommand command : AbstractCommand.getCommands()){
            //if (command.getStatus()) {
                str.add("\t" + number + ": " + command.toString());
                number++;
            //}
        }
        return str;
    }

    public static ArrayList<String> info(Vector<Ticket> collection){
        ArrayList<String> str = new ArrayList<>();
        str.add("Информация о коллекции: ");
        str.add("Коллекция: "+collection.getClass());
        str.add("Колличество элементов: "+collection.size());
        if (collection.size() > 0 ) {
            str.add("Тип элементов: "+collection.get(0).getClass());
            str.add("Коллекция создана: "+collection.get(0).getCreationDate());
        }
        return str;
    }

    public static ArrayList<String> show (Vector<Ticket> collection) throws NullPointerException{
        ArrayList<String> str = new ArrayList<>();
        if (collection.size() > 0){
            for (Ticket ticket : collection){
                str.add(ticket.toString());
            }
        }
        else{
            str.add("Коллекция не имеет элементов.");
        }
        return str;
    }

    public static void detect (int value, Vector<Ticket> collection) throws IllegalArgumentException, CommandExecutionException {
        String message = "Данный объект не обнаружен!";
        if (value <= 0 ) throw new CommandExecutionException(message);
        for (Ticket ticket : collection) {
                if (ticket.getId() == value) return;
        }
        System.out.println(message);
        throw new CommandExecutionException(message);
    }

    public static ArrayList<String> update(Vector<Ticket> collection, int value, Ticket ticket) throws CommandExecutionException, JsonIOException{
        ArrayList<String> str = new ArrayList<>();
        if (collection.size() > 0){
            try{
                detect(value, collection);
            }
            catch (IllegalArgumentException exp){
                str.add("Элемента с таким Id не существует.");
            }
            collection.set(value-1, ticket);
            str.add("Элемент коллекции с Id = " + value+" обновлен.");
        }
        else str.add("Коллекция не имеет элементов. Заменить ticket с Id = "+ (value) + " на созданный вами билет невозможно.");
        return str;
    }

    public static ArrayList<String> removeById(Vector<Ticket> collection, int value) throws CommandExecutionException{
        ArrayList<String> str = new ArrayList<>();
        if (collection.size() > 0){
            try{
                detect(value, collection);
            }
            catch (IllegalArgumentException exp){
                str.add("Элемента с таким Id не существует.");
            }
            collection.removeIf(ticket -> ticket.getId() == value);
            str.add("Элемент с Id = " + value + " удален.");
        }
        else str.add("Коллекция не имеет элементов.");
        return str;
    }

    public static ArrayList<String> removeAtIndex(Vector<Ticket> collection, int index){
        ArrayList<String> str = new ArrayList<>();
        if (collection.size() > 0){
            if (index < collection.size()){
                collection.remove(index);
                str.add("Элемент с индексом = " + index + " удален.");
            }
            else str.add("Элемента с таким индексом не существует.");
        }
        else str.add("Коллекция не имеет элементов.");
        return str;
    }

    public static ArrayList<String> clear(Vector<Ticket> collection){
        ArrayList<String> str = new ArrayList<>();
        if (collection.size() > 0) {
            collection.clear();
            str.add("Коллекция очищена.");
        }
        else str.add("Коллекция не имеет элементов.");
        return str;
    }

    public static ArrayList<String> save(Vector<Ticket> collection, String filePath) throws IOException, CommandExecutionException {
        ArrayList<String> str = new ArrayList<>();
        Gson gson = new Gson();
        FileOutputStream file;
        String json = gson.toJson(collection);
        try {
            file = new FileOutputStream(filePath);
        }
        catch (FileNotFoundException exp){
            str.add("Файл не найден.");
            throw new CommandExecutionException("Ошибка с доступом к файлу!");
        }
        for (Ticket ticket : collection){
           file.write(json.getBytes());
        }
        file.close();
        str.add("Изменения сохранены.");
        return str;
    }

    public static ArrayList<String> exit(){
        ArrayList<String> str = new ArrayList<>();
        str.add("Программа завершена.");
        System.exit(0);
        return str;
    }

    public static ArrayList<String> reorder (Vector<Ticket> collection) {
        ArrayList<String> str = new ArrayList<>();
        if (collection.size()>0){
            reverse(collection);
            str.add("Коллекция отсортирована в обратном порядке.");
        }
        else str.add("Коллекция не имеет элементов.");
        return str;
    }

    public static ArrayList<String > addIfMax(Vector<Ticket> collection, Ticket ticket){
        ArrayList<String> str = new ArrayList<>();
        class AddIfMaxCoordinates implements Comparator<Ticket>{
            @Override
            public int compare (Ticket ticket1, Ticket ticket2){
                int result = 2;
                if (!(ticket1.getCoordinates().getX() == null || ticket2.getCoordinates().getX() == null)){
                    if (ticket1.getCoordinates().getX() > ticket2.getCoordinates().getX()) result = 1;
                    else if (ticket1.getCoordinates().getX() < ticket2.getCoordinates().getX()) result = -1;
                    else if (ticket1.getCoordinates().getX().equals(ticket2.getCoordinates().getX())){
                        if (ticket1.getCoordinates().getY() > ticket2.getCoordinates().getY()) result = 1;
                        else if (ticket1.getCoordinates().getY() < ticket2.getCoordinates().getY()) result = -1;
                        else if (ticket1.getCoordinates().getY() == ticket2.getCoordinates().getY()) result = 0;
                    }
                }
                return result;
            }
        }

        class AddIfMaxPrice implements Comparator<Ticket>{
            @Override
            public int compare(Ticket ticket1, Ticket ticket2){
                int result = 2;
                if (!(ticket1.getPrice() < 0 || ticket2.getPrice() < 0)){
                    if (ticket1.getPrice() > ticket2.getPrice()) result = 1;
                    else if (ticket1.getPrice() < ticket2.getPrice()) result = -1;
                    else if (ticket1.getPrice().equals(ticket2.getPrice())) result = 0;
                }
                return result;
            }
        }

        Ticket maxByCoordinates = max(collection, new AddIfMaxCoordinates());
        Ticket maxByPrice = max(collection, new AddIfMaxPrice());
        if (collection.size() > 0){
            if (maxByCoordinates.compareTo(ticket) < 0 ) {
                str.add("Данный Ticket является максимальным по значению поля coordinates.");
                collection.add(ticket);
                str.add("Ticket добавлен в коллекцию.");
            }
            else if (maxByPrice.getPrice() < ticket.getPrice()) {
                str.add("Данный Ticket является максимальным по значению поля price.");
                collection.add(ticket);
                str.add("Ticket добавлен в коллекцию.");
            }
            else if ((maxByCoordinates.compareTo(ticket) > -1) && (!(maxByPrice.getPrice() < ticket.getPrice())))
                str.add("Данный Ticket не является максимальным ни по значению поля coordinates, ни по значению поля price. Ticket не добавлен.");
        }
        else {
            str.add("Коллекция пуста. Данный ticket является максимальным по всем параметрам.");
            collection.add(ticket);
            str.add("Ticket добавлен в коллекцию.");
        }
        return str;
    }

    public static ArrayList<String> maxByCoordinates(Vector<Ticket> collection) {
        ArrayList<String> str = new ArrayList<>();
        if (collection.size() > 0){
            class FoundMaxCoordinates implements Comparator<Ticket>{
                @Override
                public int compare (Ticket ticket1, Ticket ticket2){
                    int result = 0;
                    if (!(ticket1.getCoordinates().getX() == null || ticket2.getCoordinates().getX() == null )){
                        result = ticket1.getCoordinates().compareTo(ticket2.getCoordinates());
                    }
                    return result;
                }
            }
            str.add(max(collection, new FoundMaxCoordinates()).toString());
        }
        else str.add("Коллекция не имеет элементов.");
        return str;
    }

    public static ArrayList<String> printUniquePrice(Vector<Ticket> collection, float price){
        ArrayList<String> str = new ArrayList<>();
        if (collection.size() > 0){
            for (Ticket ticket : collection){
                if(ticket.getPrice() != null){
                    if (!(ticket.getPrice() == price)){
                        str.add(""+ticket.getPrice());
                    }
                }
                else str.add("Билет с Id "+ticket.getId()+ " не содержит значения price.");
            }
        }
        else str.add("Коллекция не имеет элементов.");
        return str;
    }


    public static ArrayList<String> printFieldDescendingPrice(Vector<Ticket> collection){
        ArrayList<String> str = new ArrayList<>();
        if (collection.size() > 0){
            class SortByPrice implements Comparator<Ticket>{
                @Override
                public int compare(Ticket ticket1, Ticket ticket2){
                    int result = 0;
                    if (!(ticket1.getPrice() < 0 || ticket2.getPrice() < 0)){
                        result = ticket1.getPrice().compareTo(ticket2.getPrice());
                    }
                    return result;
                }
            }
            Comparator comparator = Collections.reverseOrder(new SortByPrice());
            sort(collection, comparator);
            for (Ticket ticket : collection) {
                str.add(""+ticket.getPrice());
            }
        }
        else str.add("Коллекция не имеет элементов.");
        return str;
    }

    /**
     * The method that reads the script.
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalArgumentException
     * @throws InvalidScriptException
     * @throws ClassNotFoundException
     */

    public static ArrayList<String> readCommandFromFile(String Path) throws IOException, InvalidScriptException, CommandExecutionException {
        BufferedReader bufferedReader;
        ArrayList<String> str = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(Path));
            scriptNames.add(Path);
        }
        catch (IOException e){
            System.out.println("Ошибка в пути к файлу.");
            throw new InvalidScriptException("Ошибка в пути к файлу.");
        }

        Scanner scanner;
        String line;
        String data;
        Class myObject;
        Command command;
        String name = null;
        while ((line = bufferedReader.readLine()) != null) {
            command = null;
            System.out.println("data: " + line);
            scanner = new Scanner(line);
            while (scanner.hasNext()) {
                while (true) {
                    try {
                        data = scanner.nextLine() + " ";
                        name = data.substring(0, data.indexOf(" "));
                        myObject = Class.forName("laba.commands.commands.Command_" + name);
                        break;
                    } catch (ClassNotFoundException e) {
                        str.add("Несуществующая команда " + name + ". Исправьте скрипт.");
                        throw new InvalidScriptException("Несуществующая команда " + name + ". Исправьте скрипт.");
                    }
                }
                data = data.trim();
                Constructor[] constructors = myObject.getConstructors();
                Class[] parameterTypes = constructors[0].getParameterTypes();
                try {
                    if (parameterTypes.length == 0) {
                        command = (Command) constructors[0].newInstance();
                        str.add("Полученная из скрипта команда: " + command);
                    } else if (parameterTypes.length == 1) {
                        if (parameterTypes[0].toString().compareTo("int") == 0) {
                            command = (Command) constructors[0].newInstance(Integer.parseInt(data.substring(data.lastIndexOf(" ") + 1)));
                            str.add("Полученная из скрипта команда: " + command);
                        } else if (parameterTypes[0].toString().compareTo("class laba.com.company.Ticket") == 0) {
                            command = (Command) constructors[0].newInstance(createTicket(bufferedReader));
                            str.add("Полученная из скрипта команда: " + command);
                        } else if (parameterTypes[0].toString().compareTo("float") == 0) {
                            command = (Command) constructors[0].newInstance(Float.parseFloat(data.substring(data.lastIndexOf(" ") + 1)));
                            str.add("Полученная из скрипта команда: " + command);
                        } else if (parameterTypes[0].toString().compareTo("class java.lang.String") == 0) {
                            command = (Command) constructors[0].newInstance(data.substring(data.indexOf(" ") + 1));
                            str.add("Полученная из скрипта команда: " + command);
                        }
                    } else if (parameterTypes.length == 2) {
                        command = (Command) constructors[0].newInstance(Integer.parseInt(data.substring(data.indexOf(" ") + 1)), createTicket(bufferedReader));
                        str.add("Полученная из скрипта команда: " + command);
                    }
                } catch (InvalidScriptException exp) {
                    throw exp;
                } catch (IOException | InstantiationException | InvocationTargetException | NumberFormatException | InputMismatchException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                if (command != null) {
                    if ((command instanceof Command_ExecuteScriptFile)) {
                        Iterator iterator = scriptNames.iterator();
                        boolean check = false;
                        while (iterator.hasNext()) {
                            if (parameterTypes[0].toString().equals(iterator.next())) check = true;
                        }
                        if (check) {
                            str.add(ANSI_RED + "\tПопытка зациклить программу прервана." + ANSI_RESET);
                        } else {
                            scriptNames.add(parameterTypes[0].toString());
                            ++countExecute;
                            str.add(ANSI_GREEN + "\tИсполняется полученный из скрипта скрипт." + ANSI_RESET);
                            command.execute();
                            --countExecute;
                            str.add(ANSI_GREEN + "\tСкрипт, полученный из скрипта, исполнен." + ANSI_RESET);
                        }
                    } else {
                        command.execute();
                    }
                }
            }
        }
        if (countExecute == 0){
            scriptNames.clear();
        }
        return str;
    }

    private static Ticket createTicket (BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        String name = null;
        float price;
        String comment;
        TicketType type;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            if (!input.isEmpty()) name = scanner.next().trim();
        }
        System.out.println("name: " + name);
        Coordinates coordinates = createCoordinates(bufferedReader);
        price = getFloat(bufferedReader);
        System.out.println("price: " + price);
        comment = getComment(bufferedReader);
        System.out.println("comment: "+ comment);
        type = createType(bufferedReader);
        System.out.println("type: "+type);
        Person person = createPerson(bufferedReader);
        return new Ticket(name, coordinates, price, comment, type, person);
    }

    private static Float getFloat(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        Float result = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try{
                if (!input.isEmpty()) result = scanner.nextFloat();
            }
            catch (InputMismatchException e){
                System.out.println("Ошибка в скрипте: Неверные данные. Данная строка должна содержать дробное число (float).");
                throw new InvalidScriptException();
            }
        }
        return result;
    }

    private static String getComment(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        String result = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try{
                if (!input.isEmpty()) result = scanner.next();
            }
            catch (InputMismatchException e){
                System.out.println("Ошибка в скрипте: Неверные данные. Данная строка должна содержать строку.");
                throw new InvalidScriptException();
            }
        }
        return result;
    }

    private static Coordinates createCoordinates(BufferedReader bufferedReader) throws IOException, InputMismatchException, InvalidScriptException{
        System.out.println("coordinates: ");
        Float x = null;
        double y;
        x = getFloat(bufferedReader);
        System.out.println("x: "+x);
        y = getDouble(bufferedReader);
        System.out.println("y: "+y);
        return new Coordinates(x, y);
    }

    private static Double getDouble(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        Double result = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try {
                if (!input.isEmpty()) result = scanner.nextDouble();
            }
            catch (InputMismatchException e){
                System.out.println("Ошибка в скрипте: Неверные данные. Данная строка должна содержать дробное число (double).");
                throw new InvalidScriptException();
            }
        }
        return result;
    }

    private static TicketType createType(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        TicketType type = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try {
                if (!input.isEmpty()) type = TicketType.valueOf(scanner.next().trim());
            }
            catch (IllegalArgumentException e){
                System.out.println("Ошибка в скрипте: Неверные данные.");
                throw new InvalidScriptException();
            }
        }
        return type;
    }

    private static Person createPerson(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        int height = getInteger(bufferedReader);
        System.out.println("height: " + height);
        Color eyeColor = createColor(bufferedReader);
        System.out.println("eyeColor: "+ eyeColor);
        Color hairColor = createColor(bufferedReader);
        System.out.println("hairColor: "+hairColor);
        Country nationality = createCountry(bufferedReader);
        System.out.println("nationality: "+nationality);
        return new Person(height, eyeColor, hairColor, nationality);
    }

    private static Integer getInteger(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        Integer result = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try{
                if (!input.isEmpty()) result = scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Ошибка в скрипте: Неверные данные. Данная строка должна содержать целое число (int).");
                throw new InvalidScriptException();
            }
        }
        return result;
    }

    private static Color createColor(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        Color color = null;
        String string = "";
        while (string.isEmpty()){
            string = bufferedReader.readLine();
            scanner = new Scanner(string);
            try {
                if (!string.isEmpty()) color = Color.valueOf(scanner.next().trim());
            }
            catch (IllegalArgumentException e){
                System.out.println("Ошибка в скрипте: Неверные данные.");
                throw new InvalidScriptException();
            }
        }
        return color;
    }

    private static Country createCountry(BufferedReader bufferedReader) throws IOException, InvalidScriptException {
        Scanner scanner;
        Country country = null;
        String string = "";
        while (string.isEmpty()){
            string = bufferedReader.readLine();
            scanner = new Scanner(string);
            try {
                if (!string.isEmpty()) country = Country.valueOf(scanner.next().trim());
            }
            catch (IllegalArgumentException e){
                System.out.println("Ошибка в скрипте: Неверные данные.");
                throw new InvalidScriptException();
            }
        }
        return country;
    }
}

