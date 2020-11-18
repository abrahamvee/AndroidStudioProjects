package com.example.android.quakereport;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform teh nwtowrk request to the given URL
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    //Query URL
    private String mUrl;

    public EarthquakeLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override protected void onStartLoading(){
        forceLoad();
    }

    /**
     *this is on background thread
     */
    @Override
    public List<Earthquake> loadInBackground(){
        if(mUrl== null){
            return null;
        }
        //Perform the network request, parse, the response, and extract a list of earthquakes
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }

}
