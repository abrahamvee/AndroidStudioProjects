package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();
    private static final String LOCATION_SEPARATOR = " of ";
    String primaryLocation;
    String locationOffset;

    EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes){
        super(context,0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        //Get {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        //Fin the TextView in the list_item.xml layout with the ID version name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        //Format the magnitude
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        //Get the magnitude and set it in textView
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        //Get the appropriate background color
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        magnitudeTextView.setText(formattedMagnitude);
        //Get textView for the City
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.location);
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        //Get city and set it
        //Separate location into two parts
        String location = currentEarthquake.getCity();
        if(location.contains(LOCATION_SEPARATOR)){
            String[] parts = location.split(LOCATION_SEPARATOR);
            locationOffset = parts [0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }else{
            locationOffset = getContext().getString(R.string.neart_the);
            primaryLocation = location;
        }
        primaryLocationView.setText(primaryLocation);
        locationOffsetView.setText(locationOffset);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        //Get textview for Date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);
        //Get textView for Time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        return listItemView;
    }

    /**
     * Retunr the formatted datr string form a Date object
     */
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy ");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date String
     */
    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat(" h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Gets adequate color for the magnitude circle
     */
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeRounded = (int) Math.floor(magnitude);
        switch (magnitudeRounded){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        //magnitudeColorResourceId only points to th resource so we need getColor to convert it into an actual integer
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
