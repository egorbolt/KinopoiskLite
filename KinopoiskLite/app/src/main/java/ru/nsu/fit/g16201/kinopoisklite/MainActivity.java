package ru.nsu.fit.g16201.kinopoisklite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {

    final ExploreFragment exploreFragment = new ExploreFragment();
    final RandomFragment randomFragment = new RandomFragment();
    final ListsFragment listsFragment = new ListsFragment();
    final FragmentManager fm = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    Fragment active = exploreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_explore:
                                //fm.beginTransaction().hide(active).show(exploreFragment).addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();  //будет сохранять всю историю переходов и разматывать её обратно при нажатии на back
                                Fragment exploreActiveFragment = exploreFragment.getActiveFragment();
                                fm.beginTransaction().hide(active).show(exploreActiveFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                active = exploreActiveFragment;
                                return true;
                            case R.id.action_random:
                                fm.beginTransaction().hide(active).show(randomFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                active = randomFragment;
                                return true;
                            case R.id.action_lists:
                                fm.beginTransaction().hide(active).show(listsFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                active = listsFragment;
                                return true;
                        }
                        return false;
                    }
                });

        fm.beginTransaction().add(R.id.main_container, listsFragment, "3").hide(listsFragment).commit();
        fm.beginTransaction().add(R.id.main_container, randomFragment, "2").hide(randomFragment).commit();
        fm.beginTransaction().add(R.id.main_container, exploreFragment, "1").commit();

        bottomNavigationView.setSelectedItemId(R.id.action_explore);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        //todo

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //todo

        return false;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && bottomNavigationView.getSelectedItemId() == R.id.action_explore && exploreFragment.getActiveFragment() != exploreFragment) {
            exploreFragment.setExploreActive();
            active = exploreFragment;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
