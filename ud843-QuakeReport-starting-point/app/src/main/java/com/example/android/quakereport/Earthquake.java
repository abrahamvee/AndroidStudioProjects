package com.example.android.quakereport;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    //Member variables for the earthquake
    private double magnitude;
    private String city;
    private long date;
    private String earthquakeURL;
    //Date dateObject = new Date(date);


    /**
     *
     * @param magnitude is the magnitude of the earthquake
     * @param city is the location of the earthquake
     * @param date is thetime in milliseconds (from EPoch)
     */
    public Earthquake(double magnitude, String city, long date, String earthquakeURL){
        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
        this.earthquakeURL = earthquakeURL;
    }

    /**
     * Get the magnitude of the earthquake
     * @return magnitude
     */
    public double getMagnitude(){return magnitude;}

    /**
     * Get the city where the earthquake ocurred.
     * @return String city
     */
    public String getCity(){return city;}

    /**
     * Get the date when the earthquake occurred.
     * @return Date of the event
     */
    public long getTimeInMilliseconds(){return date;}

    public String getEarthquakeURL() {
        return earthquakeURL;
    }
}
