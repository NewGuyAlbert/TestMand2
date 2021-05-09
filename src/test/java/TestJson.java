import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TestJson {

    String file = "C:\\Users\\danil\\IdeaProjects\\Task2TestMand2\\src\\main\\resources\\telenor.json";
    JsonRW jsonRW = new JsonRW(file);

    @Test
    public void testJsonRead(){

        JSONObject jsonObject = jsonRW.readJson();
        assertNotNull(jsonObject);


        JSONObject stock = (JSONObject) jsonObject.get("Stocks");
        JSONArray items = (JSONArray) stock.get("Item");

        System.out.println(((JSONObject) items.get(0)).get("name"));

        //Iterating the contents of the array

    }

    @Test
    public void testCheckStock(){
        String amount = jsonRW.checkStock("iPhone 12 Pro");
        assertNotNull(amount);
        System.out.println(amount);
    }

    @Test
    public void testChangeStock(){
        JSONObject result = jsonRW.changeStock("iPhone 12 Pro", "15000");
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void testWriteToFile(){
        JSONObject newData = jsonRW.changeStock("iPhone 12 Pro", "7001");
        assertNotNull(newData);
        boolean result = jsonRW.writeToJsonFile(newData);
        assertNotNull(result);
    }

    @Test
    public void testAddItem(){
        String newItem = "{\"amount\":\"6700\",\"price\":\"4,000 DKK\",\"name\":\"Huawei P10\",\"company\":\"Huawei\"}";
        JSONObject newData = jsonRW.addItem(newItem);
        assertNotNull(newData);
        Boolean result = jsonRW.writeToJsonFile(newData);
        assertNotNull(result);
    }

}