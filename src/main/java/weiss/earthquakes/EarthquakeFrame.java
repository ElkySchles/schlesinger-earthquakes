package weiss.earthquakes;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class EarthquakeFrame extends JFrame{
    String allInfo;
    public EarthquakeFrame() throws IOException {
        URL url = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Gson gson = new Gson();
        FeatureCollection featureCollection = gson.fromJson(reader, FeatureCollection.class);
        allInfo = featureCollection.features[0].properties.place;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        JLabel theText = new JLabel(allInfo, SwingConstants.CENTER);
        mainPanel.add(theText, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setSize(1200, 1000);
        setTitle("Latest Earthquake Information");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
