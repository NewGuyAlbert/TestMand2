import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        String file = "C:\\Users\\danil\\IdeaProjects\\Task2TestMand2\\src\\main\\resources\\commands.txt";
        FileRead fr = new FileRead(file);
        fr.readFileByLine();
        LinkedList<String> commands = fr.getCommands();

        //pass command to interpreter one by one
        for (String command : commands) {
            Interpreter.interpretLine(command);
        }

        //TODO check for duplicates before inserting new item

    }
}
