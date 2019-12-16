package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Person;

import static ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API.TIMEOUT;
import static ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API.moshi;

public class PersonInfoTask extends AsyncTask<URL, Void, Person> {
    private JsonAdapter<Person> adapter = moshi.adapter(Person.class);
    @Override
    protected Person doInBackground(URL... urls) {
        URL url = urls[0];
        HttpsURLConnection req;
        try {
            req = (HttpsURLConnection) url.openConnection();
            req.setRequestMethod("GET");
        } catch (IOException e) {
            Log.e("MovieInfoTask", "Cannot create connection: " + e.getMessage());
            return null;
        }
        req.setDoOutput(false);
        req.setDoInput(true);
        req.setConnectTimeout(TIMEOUT);
        req.setReadTimeout(TIMEOUT);
        try {
            req.connect();
        } catch (IOException e) {
            Log.e("MovieInfoTask", "Cannot open connection: " + e.getMessage());
            return null;
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            StringBuilder res = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                res.append(line).append("\n");
            }
            reader.close();
            if (res.length() != 0) {
                return adapter.fromJson(res.toString());
            }
        } catch (IOException e) {
            Log.e("MovieInfoTask", "Cannot read data: " + e.getMessage());
            return null;
        }
        return null;
    }
}