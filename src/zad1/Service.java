/**
 *
 *  @author Gołębski Przemysław S16540
 *
 */

package zad1;



import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


class Service {
        private final static String APIKEY ="f39ecbf377bed837c0b4deee7953fa12";
        private String kraj;
        private Content countryJson;
     Service(String kraj) {
        this.kraj=kraj;
        getCountryData();
    }

     String getWeather(String miasto) {
         String url = "http://api.openweathermap.org/data/2.5/weather?appid="+APIKEY+"&q="+miasto+","+getCountryCode();
         String weather;
         try {
              Content content = Request.Get(url).execute().returnContent();
              JSONObject jsonObject  = new JSONObject(content.toString());
              weather=jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
         } catch (IOException e) {
                e.printStackTrace();
                weather = "Error while retrieving weather";
         }
         return weather;
    }

     Double getRateFor(String kod_waluty) {
        String url  = "https://api.exchangeratesapi.io/latest?base="+getCurrencyCode();
        double rate =0;
         try {
             Content content = Request.Get(url).execute().returnContent();
             rate = new JSONObject(content.toString()).getJSONObject("rates").getDouble(kod_waluty);
             System.out.println(rate);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return rate;
    }

     Double getNBPRate() {

        return null;
    }
     private void getCountryData(){
        String url = "https://restcountries.eu/rest/v2/name/"+kraj+"?fullText=true";
         try {
             countryJson = Request.Get(url).execute().returnContent();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     private String getCountryCode(){
         JSONArray JSONarray = new JSONArray(countryJson.toString());
         return  JSONarray.getJSONObject(0).getString("alpha3Code");


     }
     private String getCurrencyCode(){
         JSONArray JSONarray = new JSONArray(countryJson.toString());
        return JSONarray.getJSONObject(0).getJSONArray("currencies").getJSONObject(0).getString("code");

     }
}
