package com.example.homework2.content;

import android.content.SyncStatusObserver;

import java.util.ArrayList;
import java.util.List;

public class CityUtils {
        // An ArrayList of Songs
        public static final List<City> CITY_ITEMS = new ArrayList<>();

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

        private static void addItem(City item) {
            CITY_ITEMS.add(item);
        }

        static {
            // Fill the array with songs.
            for (int i = 0; i < COUNT; i++) {
                addItem(createCityAtPosition(i));
            }
        }

        private static City createCityAtPosition(int position) {
            String newName;
            String newDetail;
            switch (position) {
                case 0:
                    newName = "Aveiro";
                    newDetail = "Cidade de Aveiro";
                    break;
                case 1:
                    newName = "Porto";
                    newDetail = "Cidade do Porto";
                    break;
                case 2:
                    newName = "Guarda";
                    newDetail = "Cidade da Guarda";
                    break;
                case 3:
                    newName = "Leiria";
                    newDetail = "Cidade de Leiria";
                    break;
                case 4:
                    newName = "Braga";
                    newDetail = "Cidade de Braga";
                    break;
                case 5:
                    newName = "Viana";
                    newDetail = "Cidade de Viana";
                    break;
                default:
                    newName = "Cidade Random";
                    newDetail = "Cidade Random";
                    break;
            }
            return new City(newName, newDetail);
        }
    }


