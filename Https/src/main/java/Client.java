import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=40.395279&lon=49.882221&appid=6c1ed9e9bb93c6a0f5b86d7e69027f5e");
            HttpsURLConnection cons = (HttpsURLConnection) url.openConnection();
            cons.setRequestMethod("GET");
            cons.connect();
            // check if connect is made
            int responseCode = cons.getResponseCode();
            //200 Ok

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode:" + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    informationString.append(sc.nextLine());
                }
                sc.close();
                //Convert Strings to json
                JSONParser parse = new JSONParser();
                Object obj = parse.parse(String.valueOf(informationString));
                JSONArray array = new JSONArray();
                array.add(obj);

                //Get json object in the json array
                JSONObject userData = (JSONObject) array.get(0);
                System.out.println(userData.get("weather"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
