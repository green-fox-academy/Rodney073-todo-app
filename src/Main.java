import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class Main {

    public static void main(String[] args) {

        System.out.println("$ todo\n" +
                "\n" +
                "Command Line Todo application\n" +
                "=============================\n" +
                "\n" +
                "Command line arguments:\n" +
                "    -l   Lists all the tasks\n" +
                "    -a   Adds a new task\n" +
                "    -r   Removes an task\n" +
                "    -c   Completes an task\n");


        if (args[0].equals("-l")) {
            openFile("C:/Users/Yoga/Documents/GitHub/green-fox-academy/Rodney073/todo-app/assets/todos.txt");
        } else if (args[0].equals("-a")) {
            if (args.length < 2) {
                System.out.println("type the new todo after command -a");
                System.exit(2);
            } else {
                System.out.println("I'd like to add the following item to my list: " + args[1]);
                addNewTodo("C:/Users/Yoga/Documents/GitHub/green-fox-academy/Rodney073/todo-app/assets/todos.txt", args[1]);
            }
        } else if (args[0].equals("-r")) {
            removeSecondTodo("C:/Users/Yoga/Documents/GitHub/green-fox-academy/Rodney073/todo-app/assets/todos.txt");
        } else if (args[0].equals("-c")) {
            checkSecondTodo("C:/Users/Yoga/Documents/GitHub/green-fox-academy/Rodney073/todo-app/assets/todos.txt");
        }
    }

    public static void openFile(String doksi) {
        try {

            Path filePath = Paths.get(doksi);
            List<String> lines = Files.readAllLines(filePath);

            if (lines.size() == 0) {
                System.out.println("No todos for today! :)");
            } else {

                for (int i = 0; i < lines.size(); i++) {
                    System.out.println(i + 1 + ". [ ] " + lines.get(i));
                }
            }

        } catch (Exception e) {
            System.out.println("Unable to read file: todos.txt");
        }
    }

    public static void addNewTodo(String doksi, String newTodoItem) {
            try {
                Path filePath = Paths.get(doksi);
                List<String> lines = Files.readAllLines(filePath);
                FileWriter writeFile = new FileWriter(doksi,true);


                lines.add(newTodoItem);
                writeFile.write("\n"+newTodoItem);
                writeFile.close();

                for (int i = 0; i < lines.size(); i++) {
                    System.out.println(i + 1 + ". [ ] " + lines.get(i));
                }
            } catch (Exception e) {
                System.out.println("Unable to read file: todos.txt");
            }
        }

    public static void removeSecondTodo(String doksi) {
        try {
            Path filePath = Paths.get(doksi);
            List<String> lines = Files.readAllLines(filePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(doksi));

            //Print out original list
            System.out.println("Current todo list: ");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println(i + 1 + ". [ ] " + lines.get(i));
            }

            //Remove 2nd item
            if (lines.size()>=2) {
                lines.remove(1);
            } else {
                System.out.println("There's only one task in the list, so I can't remove the second one.");
            }
            System.out.println("The second item has been removed from the list. Here's the updated list: ");

            //Print out list after removal
            for (int i = 0; i < lines.size(); i++) {
                System.out.println(i + 1 + ". [ ] " + lines.get(i));
                writer.write(lines.get(i)+"\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Unable to read file: todos.txt");
        }
    }

    public static void checkSecondTodo(String doksi) {
        try {
            Path filePath = Paths.get(doksi);
            List<String> lines = Files.readAllLines(filePath);
            String checkButton= ". [ ] ";

            //Print out original list
            System.out.println("Current todo list: ");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println(i + 1 +checkButton+lines.get(i));
            }

            //Print out list after check


            if (lines.size()>=2) {
                System.out.println("\n"+lines.get(1)+" is done!");
                for (int i = 0; i < lines.size(); i++) {
                    if (i == 1) {
                        checkButton=". [x] ";
                        System.out.println((i+1)+checkButton+lines.get(i));
                    } else {
                        checkButton=". [ ] ";
                        System.out.println((i+1)+checkButton+lines.get(i));
                    }
                }
            } else {
                System.out.println("I had only 1 task for today and I couldn't be bothered to do anything. Sorry...");
            }

        } catch (Exception e) {
            System.out.println("Unable to read file: todos.txt");
        }
    }
}
