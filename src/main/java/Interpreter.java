import org.json.simple.JSONObject;

import java.sql.SQLOutput;
import java.util.LinkedList;

public class Interpreter {

    public static void interpretLine(String command){
        String[] splitter = command.split(" ", 2);
        String file = "C:\\Users\\danil\\IdeaProjects\\Task2TestMand2\\src\\main\\resources\\telenor.json";
        JsonRW jsonRW = new JsonRW(file);
        int elements;

        //First switch to determine how we segments the commands
        switch (splitter[0]) {
            case "check", "update" -> {
                splitter = command.split(" ", 3);
                elements = 3;
            }
            default -> {
                splitter = command.split(" ", 2);
                elements = 2;
            }
        }

        //Second switch to run the commands
        switch (splitter[0]) {
            case "check" -> {
                if(splitter[1].equals("amount")){
                    String amount = jsonRW.checkStock(splitter[2]);
                    if(amount!=null){
                        System.out.println(amount);
                    } else {
                        System.out.println("Device not found");
                    }
                } else {
                    System.out.println("Anything besides amount is work in progress");
                }
            }
            case "update" -> {
                if(splitter[1].equals("amount")){
                    String[] arr = splitter[2].split("/");
                    String device = arr[0];
                    String newAmount = arr[1];

                    JSONObject result = jsonRW.changeStock(device, newAmount);
                    if(result!=null){
                        boolean success = jsonRW.writeToJsonFile(result);
                        if(success){
                            System.out.println("Update successful");
                        }else {
                            System.out.println("Update failed");
                        }
                    } else {
                        System.out.println("Device not found");
                    }
                }else {
                    System.out.println("Anything besides amount is work in progress");
                }
            }
            case "add-item" -> {
                String[] arr = splitter[1].split("/");
                if(arr.length == 4){
                    String newItem = "{\"amount\":\"%s\",\"price\":\"%s\",\"name\":\"%s\",\"company\":\"%s\"}";
                    newItem = String.format(newItem,arr[0],arr[1],arr[2],arr[3]);

                    JSONObject newData = jsonRW.addItem(newItem);
                    boolean success = jsonRW.writeToJsonFile(newData);
                    if(success){
                        System.out.println("Insert successful");
                    }else {
                        System.out.println("Insert failed");
                    }
                } else if(arr.length > 4){
                    System.out.println("Too many arguments");
                } else{
                    System.out.println("Too few arguments");
                }

            }
        }

    }
}
