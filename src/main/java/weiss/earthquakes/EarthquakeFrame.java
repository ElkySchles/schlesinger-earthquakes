package weiss.earthquakes;

import com.google.gson.Gson;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EarthquakeFrame extends JFrame {
    public EarthquakeFrame() throws IOException {


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        JLabel theText = new JLabel("", SwingConstants.CENTER);
        mainPanel.add(theText, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setSize(1200, 1000);
        setTitle("Latest Earthquake Information");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        EarthquakeService service = retrofit.create(EarthquakeService.class);

        Disposable disposable = service.getLatestEarthquakes()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        featureCollection -> {
                            String allInfo = featureCollection.features[0].properties.place;
                            theText.setText(allInfo);
                        }
                        ,
                        Throwable::printStackTrace

                );

    }
}
