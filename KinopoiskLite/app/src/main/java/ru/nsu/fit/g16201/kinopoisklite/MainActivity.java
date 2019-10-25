package ru.nsu.fit.g16201.kinopoisklite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        final TextView textView = findViewById(R.id.text_view);;
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_explore:
                                //todo: сделать видимым какой-нибудь из других activity
                                textView.setText("Explore");
                                break;
                            case R.id.action_random:
                                textView.setText("Random");
                                break;
                            case R.id.action_lists:
                                textView.setText("Lists");
                                break;
                        }
                        return true;
                    }
                });
    }
}
