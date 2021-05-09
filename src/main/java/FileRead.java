import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class FileRead {

    private String file;
    private LinkedList<String> commands;

    public FileRead(String file){
        this.file = file;
        this.commands = new LinkedList<String>();
    }

    public LinkedList<String> getCommands() {
        return commands;
    }

    public void readFileByLine(){

        BufferedReader reader;
        try{

            reader = new BufferedReader(new FileReader(this.file));
            String line = reader.readLine();
            while(line != null){
                commands.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
