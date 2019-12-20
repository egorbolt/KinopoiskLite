package ru.nsu.fit.g16201.kinopoisklite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.nsu.fit.g16201.kinopoisklite.internal.userstories.commonfragments.showallfragment.SearchFragment;
import ru.nsu.fit.g16201.kinopoisklite.internal.userstories.tabfragments.ExploreFragment;
import ru.nsu.fit.g16201.kinopoisklite.internal.userstories.tabfragments.RandomFragment;
import ru.nsu.fit.g16201.kinopoisklite.internal.userstories.commonfragments.moviefragment.MovieFragment;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String EXPLORE_FRAGMENT = "explore_fragment";
    private static final String EXPLORE_ACTIVE_FRAGMENT = "explore_active_fragment";
    private static final String RANDOM_ACTIVE_FRAGMENT = "random_active_fragment";
    private static final String RANDOM_FRAGMENT = "random_fragment";
    private static final String ACTIVE_FRAGMENT = "active_fragment";

    private ExploreFragment exploreFragment;
    private Fragment exploreTabActiveFragment;
    private Fragment randomTabActiveFragment;

    private RandomFragment randomFragment;


    private Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        //можно использовать бэкстек, тогда он будет сохранять всю историю переходов и разматывать её обратно при нажатии на back
                        case R.id.action_explore:

                            if(active == randomTabActiveFragment)
                            {
                                getSupportFragmentManager().beginTransaction().hide(active).show(exploreTabActiveFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                active = exploreTabActiveFragment;
                            }
                            else
                            {
                                getSupportFragmentManager().beginTransaction().hide(active).show(exploreFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                exploreTabActiveFragment = exploreFragment;
                                active = exploreFragment;
                            }
                            return true;
                        case R.id.action_random:
                            if(active == exploreTabActiveFragment)
                            {
                                getSupportFragmentManager().beginTransaction().hide(active).show(randomTabActiveFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                active = randomTabActiveFragment;
                            }
                            else    //random fragment
                            {
                                getSupportFragmentManager().beginTransaction().hide(active).show(randomFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                randomTabActiveFragment = randomFragment;
                                active = randomFragment;
                            }
                            return true;


                        default:
                            return false;
                    }
                });

        if (savedInstanceState != null) {
            FragmentManager fm = getSupportFragmentManager();

            String tagExploreFragment = savedInstanceState.getString(EXPLORE_FRAGMENT);
            exploreFragment = (ExploreFragment) fm.findFragmentByTag(tagExploreFragment);

            String tagRandomFragment = savedInstanceState.getString(RANDOM_FRAGMENT);
            randomFragment = (RandomFragment) fm.findFragmentByTag(tagRandomFragment);

            String tagActiveFragment = savedInstanceState.getString(ACTIVE_FRAGMENT);
            active = fm.findFragmentByTag(tagActiveFragment);

            String tagExploreActiveFragment = savedInstanceState.getString(EXPLORE_ACTIVE_FRAGMENT);
            exploreTabActiveFragment = fm.findFragmentByTag(tagExploreActiveFragment);

            String tagRandomActiveFragment = savedInstanceState.getString(RANDOM_ACTIVE_FRAGMENT);
            randomTabActiveFragment = fm.findFragmentByTag(tagRandomActiveFragment);

        }
        else
        {
            exploreFragment = new ExploreFragment();
            randomFragment = new RandomFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.main_container, randomFragment, "2").hide(randomFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, exploreFragment, "3").commit();

            active =  exploreFragment;
            exploreTabActiveFragment = exploreFragment;
            randomTabActiveFragment = randomFragment;
            bottomNavigationView.setSelectedItemId(R.id.action_explore);
        }

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (exploreFragment != null) {
            outState.putString(EXPLORE_FRAGMENT, exploreFragment.getTag());
        }
        if (randomFragment != null) {
            outState.putString(RANDOM_FRAGMENT, randomFragment.getTag());
        }

        if (active != null) {
            outState.putString(ACTIVE_FRAGMENT, active.getTag());
        }

        if (exploreTabActiveFragment != null) {
            outState.putString(EXPLORE_ACTIVE_FRAGMENT, exploreTabActiveFragment.getTag());
        }

        if (randomTabActiveFragment != null) {
            outState.putString(RANDOM_ACTIVE_FRAGMENT, randomTabActiveFragment.getTag());
        }
    }

    public void setExploreTabActiveFragment(Fragment exploreTabActiveFragment) {
        this.exploreTabActiveFragment = exploreTabActiveFragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if(active == exploreFragment)
            transaction.hide(active);
        else
            transaction.remove(active);
        transaction.add(R.id.main_container, exploreTabActiveFragment, "ETAF"/* + tagCounter++*/).show(exploreTabActiveFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        active = exploreTabActiveFragment;
    }

    public void setRandomTabActiveFragment(Fragment randomTabActiveFragment) {
        this.randomTabActiveFragment = randomTabActiveFragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if(active == randomFragment)
            transaction.hide(active);
        else
            transaction.remove(active);
        transaction.add(R.id.main_container, randomTabActiveFragment, "RTAF"/* + tagCounter++*/).show(randomTabActiveFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        active = randomTabActiveFragment;
    }

    public void setSearchFragmentActive(MovieFragment movieFragment) {
        this.randomTabActiveFragment = randomTabActiveFragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if(active == randomFragment)
            transaction.hide(active);
        else
            transaction.remove(active);
        transaction.add(R.id.main_container, randomTabActiveFragment, "RTAF"/* + tagCounter++*/).show(randomTabActiveFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        active = randomTabActiveFragment;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        SearchFragment searchFragment = SearchFragment.newInstance(query);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(active);
        transaction.add(R.id.main_container, searchFragment, "SEARCH").show(searchFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }


}
