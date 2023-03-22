package weiss.earthquakes;

import com.google.gson.Gson;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws IOException {

        EarthquakeFrame frame = new EarthquakeFrame();
        frame.setVisible(true);
        //Feature collection, with an array of features. A feature has:
        //properties - which has mag(double, time(long), place(String), tsunami(integer).
        //Geometry(object) - which has coordinates(array of doubles)

        //Make a gui that shows the latest earthquake
    }
}
