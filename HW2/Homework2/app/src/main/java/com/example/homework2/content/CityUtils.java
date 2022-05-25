package com.example.homework2.content;

import android.content.SyncStatusObserver;


import com.example.homework2.APIData;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CityUtils {
        // An ArrayList of Songs
        public static final List<City> CITY_ITEMS = new ArrayList<>();
        public static final List<Weather> WEATHER_ITEMS = new ArrayList<>();
        public static final Map<String,Long> CITY_CODES = new HashMap<>();

        public static final String CITY_ID_KEY = "item_id";

        //Number of cities
        private static final int COUNT = 7;

        public static class City {
            public final String city_name;
            public final String details;

            private City(String content, String details) {
                this.city_name = content;
                this.details = details;
            }

            @Override
            public String toString() {
                return "City{" +
                        "city_name='" + city_name + '\'' +
                        ", details='" + details + '\'' +
                        '}';
            }
        }

        public static void fetchWeather(String city,String url) throws IOException, ExecutionException, InterruptedException, ParseException, JSONException {
            String weatherData = new APIData().execute(url).get();
            JSONObject jsonObj = (JSONObject) new JSONParser().parse(weatherData);
            JSONObject responseJson = (JSONObject)((JSONArray) jsonObj.get("data")).get(0);
            Weather w1 = new Weather(city,(String) responseJson.get("precipitaProb"),(String) responseJson.get("tMin"),(String) responseJson.get("tMax"),(String) responseJson.get("predWindDir"),(String) responseJson.get("forecastDate"));
            WEATHER_ITEMS.add(w1);
        }

        public static void fetchCities() throws ExecutionException, InterruptedException, ParseException {
            String countriesData = new APIData().execute("https://api.ipma.pt/open-data/distrits-islands.json").get();
            JSONObject jsonObj = (JSONObject) new JSONParser().parse(countriesData);
            int numOfCities = ((JSONArray) jsonObj.get("data")).size();
            for(int i=0;i<numOfCities;i++) {
                JSONObject responseJson = (JSONObject)((JSONArray) jsonObj.get("data")).get(i);
                CITY_CODES.put((String) responseJson.get("local"),(Long) responseJson.get("globalIdLocal"));
                CITY_ITEMS.add(new City((String)responseJson.get("local"),"Details"));
            }
        }

        public static class Weather {
            public String city;
            public String probPrecipt;
            public String tMin;
            public String tMax;
            public String windDir;
            public String date;

            public Weather(String city,String probPrecipt, String tMin, String tMax, String windDir, String date) {
                this.city = city;
                this.probPrecipt = probPrecipt;
                this.tMin = tMin;
                this.tMax = tMax;
                this.windDir = windDir;
                this.date = date;
            }

            @Override
            public String toString() {
                String s1 = "Forecast for the City of "+city+" :\n\n"+
                        "Minimum Temperature: "+tMin+"\n"+
                        "Maximum Temperature: "+tMax+"\n"+
                        "Wind Direction: "+windDir+"\n"+
                        "Precipitation Probability: "+probPrecipt+"\n"+
                        "Forecast Date: "+date+"\n";
                return s1;
            }
        }



        private static void addItem(City item) {
            CITY_ITEMS.add(item);
        }

        static {
//            CITY_ITEMS.add(new City("Aveiro","Cidade de Aveiro"));
//            CITY_ITEMS.add(new City("Porto","Cidade do Porto"));
//            CITY_ITEMS.add(new City("Braga","Cidade do Braga"));
//            CITY_ITEMS.add(new City("Coimbra","Cidade de Coimbra"));
//            CITY_ITEMS.add(new City("Leiria","Cidade de Leiria"));
//            CITY_ITEMS.add(new City("Lisboa","Cidade de Lisboa"));
//            CITY_ITEMS.add(new City("Viseu","Cidade do Viseu"));
            try {
                fetchCities();
            } catch (ExecutionException | InterruptedException | ParseException e) {
                e.printStackTrace();
            }
            try {
                for(City c : CITY_ITEMS)
                {
                    String url = "https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+CITY_CODES.get(c.city_name).toString()+".json";
                    fetchWeather(c.city_name,url);
                }
//                fetchWeather("Aveiro","https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1010500.json");
//                fetchWeather("Porto","https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1131200.json");
//                fetchWeather("Braga","https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1030300.json");
//                fetchWeather("Coimbra","https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1060300.json");
//                fetchWeather("Leiria","https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1100900.json");
//                fetchWeather("Lisboa","https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1110600.json");
//                fetchWeather("Viseu","https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1182300.json");

            } catch (IOException | JSONException | ParseException | ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


