package com.unholy.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherApi {
    public Location location;
    public Current current;
    public static class Condition {
        public String text;
        public String icon;
        public int code;
    }

    public static class Current {

        @JsonProperty("last_updated_epoch")
        public int lastUpdatedEpoch;
        public String last_updated;
        public double temp_c;
        public double temp_f;
        public int is_day;
        public Condition condition;
        public double wind_mph;
        public double wind_kph;
        public int wind_degree;
        public String wind_dir;
        public int pressure_mb;
        public double pressure_in;
        public double precip_mm;
        public double precip_in;
        public int humidity;
        public int cloud;
        public double feelslike_c;
        public double feelslike_f;
        public double windchill_c;
        public double windchill_f;
        public double heatindex_c;
        public int heatindex_f;
        public double dewpoint_c;
        public double dewpoint_f;
        public double vis_km;
        public int vis_miles;
        public int uv;
        public double gust_mph;
        public int gust_kph;
    }

    public static class Location {
        public String name;
        public String region;
        public String country;
        public double lat;
        public double lon;
        public String tz_id;
        public int localtime_epoch;
        public String localtime;
    }


}