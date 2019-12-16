package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.tabfragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.GenresListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.showallfragment.ShowAllFragment;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class RandomFragment extends Fragment {
    private static final String ERROR_TAG = "RandomFragment";
    private TextView movieDescription;
    private ImageView moviePoster;
    private MaterialButton button;
    public RandomFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random, container, false);

        movieDescription = view.findViewById (R.id.rmDescription);
        moviePoster = view.findViewById(R.id.rmImageView);
        MaterialButton button = view.findViewById(R.id.next_button);
        button.setTag("showAllButtonRandom");
        button.setOnClickListener(v -> {
            //todo
        });

        return view;
    }

    class DownloadGenres extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... parameter) {
            GenresListTask genresListTask = null;
            try {
                genresListTask = API.loadGenreList("en-US");
            } catch (MalformedURLException e) {
                Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
            }
            if(genresListTask != null)
            {
                try {
                    genresListTask.get();
                } catch (ExecutionException e) {
                    Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                } catch (InterruptedException e) {
                    Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }

            return null;
        }

        /*@Override
        protected void onPostExecute(Integer result) {
            // [... Сообщите о результате через обновление пользовательского
            // интерфейса, диалоговое окно или уведомление ...]
        }*/
    }


    public void loadRandom() throws MalformedURLException {


    }

    //todo: newInstance?
}
