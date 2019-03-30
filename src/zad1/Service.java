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
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
              JSONObject jsonObject  = new JSONObject(getContent(url).toString());
              String conditions=jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
              double temp =jsonObject.getJSONObject("main").getDouble("temp");
              temp-=273.15;
              weather = conditions+"   "+(int)temp;
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
             rate = new JSONObject(getContent(url).toString()).getJSONObject("rates").getDouble(kod_waluty);
             System.out.println(rate);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return rate;
    }

     Double getNBPRate() {

         String XMLLINKA = getNBPXml("http://www.nbp.pl/kursy/kursya.html");
         String XMLLINKB = getNBPXml("http://www.nbp.pl/kursy/kursyb.html");
         double rate = 0;

         try {
             Content conA = getContent(XMLLINKA);
             Content conB = getContent(XMLLINKB);
             Serializer serializer = new Persister();
             tabela_kursow tabA = serializer.read(tabela_kursow.class,conA.asStream());
             tabela_kursow tabB = serializer.read(tabela_kursow.class,conB.asStream());
             List<pozycja> exchRates = new ArrayList<>();
             exchRates.addAll(tabA.list);
             exchRates.addAll(tabB.list);
             rate = Double.parseDouble(exchRates.stream()
                     .filter(p-> p.kod_waluty.equals(getCurrencyCode())).findFirst().
                             get().kurs_sredni.replace(",",".")) ;
             System.out.println(rate);
         }
         catch (NoSuchElementException ex){
             rate=1;
             System.out.println("NIE ZNALEZIONO WALUTY");
         }
         catch (Exception e) {
             e.printStackTrace();
         }


         return rate;
    }
     private String getNBPXml(String url)  {
         Content content = null;
         try {
             content = getContent(url);
         } catch (IOException e) {
             e.printStackTrace();
         }
         Pattern pattern = Pattern.compile("href=\"(.*xml)\"");
         Matcher matcher  = pattern.matcher(content != null ? content.toString() : "Error");
         if (matcher.find()){
             System.out.println("PO CO TRZEBA TO SPRAWDZAC");
         }else {
             System.out.println("nie");
         }
         return"http://www.nbp.pl"+ matcher.group(1);
     }

     private void getCountryData(){
        String url = "https://restcountries.eu/rest/v2/name/"+kraj+"?fullText=true";
         try {
             countryJson = getContent(url);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     private String getCountryCode(){
         JSONArray JSONarray = new JSONArray(countryJson.toString());
         return  JSONarray.getJSONObject(0).getString("alpha3Code");


     }
      String getCurrencyCode(){
         JSONArray JSONarray = new JSONArray(countryJson.toString());
        return JSONarray.getJSONObject(0).getJSONArray("currencies").getJSONObject(0).getString("code");

     }
     private Content getContent(String URL) throws IOException {
         return Request.Get(URL).execute().returnContent();
     }
}
