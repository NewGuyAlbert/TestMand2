//Class responsible for reading and writing into Json files


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class JsonRW {

    String file = "C:\\Users\\danil\\IdeaProjects\\Task2TestMand2\\src\\main\\resources\\telenor.json";

    public JsonRW(String file){
        this.file = file;
    }

    public JSONObject readJson(){

        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(file));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.

            return (JSONObject) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String checkStock(String item){
        JSONParser parser = new JSONParser();
        try{
            JSONObject obj = (JSONObject) parser.parse(new FileReader(file));

            JSONObject stock = (JSONObject) obj.get("Stocks");
            JSONArray items = (JSONArray) stock.get("Item");

            //We go through all the items until we find the one we are looking for, then we return the stock for that particular item
            for(int i = 0; i < items.size(); i++){
                if(((JSONObject) items.get(i)).get("name").equals(item))
                    return (String) ((JSONObject) items.get(i)).get("amount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public JSONObject changeStock(String item, String newAmount){
        JSONParser parser = new JSONParser();
        JSONObject updatedAmount = null;
        JSONObject data;
        int i;
        boolean ok = false;

        try{
            data = (JSONObject) parser.parse(new FileReader(file));

            JSONObject stock = (JSONObject) data.get("Stocks");
            JSONArray items = (JSONArray) stock.get("Item");

            //We go through all the items until we find the one we are looking for, then input the new stock
            for(i = 0; i < items.size(); i++){
                if(((JSONObject) items.get(i)).get("name").equals(item)){
                    updatedAmount = (JSONObject) items.get(i);
                    updatedAmount.put("amount", newAmount);

                    ok = true;
                }
            }

            if(ok){
                items.add(i, updatedAmount);

                return data;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;

    }
    public JSONObject addItem(String newItem){

        JSONParser parser = new JSONParser();
        JSONObject data;

        try {
            data = (JSONObject) parser.parse(new FileReader(file));

            JSONObject stock = (JSONObject) data.get("Stocks");
            JSONArray items = (JSONArray) stock.get("Item");
            items.add(0,parser.parse(newItem));

            return data;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    //Insert data to file | Overwrites the file, does not add it on top
    public boolean writeToJsonFile(JSONObject data){
        FileWriter jsonfile = null;
        try {

            jsonfile = new FileWriter(file);
            jsonfile.write(data.toJSONString());
            jsonfile.flush();
            jsonfile.close();

            //Return true if successful
            return true;
        } catch (IOException e) {
            e.printStackTrace();

            //Return false if fail
            return false;
        }

    }

}
