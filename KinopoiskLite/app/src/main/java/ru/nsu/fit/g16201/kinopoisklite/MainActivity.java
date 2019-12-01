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

import ru.nsu.fit.g16201.kinopoisklite.fragments.tabfragments.ExploreFragment;
import ru.nsu.fit.g16201.kinopoisklite.fragments.tabfragments.ListsFragment;
import ru.nsu.fit.g16201.kinopoisklite.fragments.MovieFragment;
import ru.nsu.fit.g16201.kinopoisklite.fragments.tabfragments.RandomFragment;
import ru.nsu.fit.g16201.kinopoisklite.fragments.ShowAllFragment;

public class MainActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {

    private static final String EXPLORE_FRAGMENT = "explore_fragment";
    private static final String EXPLORE_ACTIVE_FRAGMENT = "explore_active_fragment";

    private static final String RANDOM_FRAGMENT = "random_fragment";

    private static final String LISTS_FRAGMENT = "lists_fragment";

    private static final String ACTIVE_FRAGMENT = "active_fragment";


    private ExploreFragment exploreFragment;
    private Fragment exploreTabActiveFragment;

    private RandomFragment randomFragment;

    private ListsFragment listsFragment;


    private Fragment active;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            //можно использовать бэкстек, тогда он будет сохранять всю историю переходов и разматывать её обратно при нажатии на back
                            case R.id.action_explore:

                                if(active == randomFragment || active == listsFragment)
                                {
                                    getSupportFragmentManager().beginTransaction().hide(active).show(exploreTabActiveFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                    active = exploreTabActiveFragment;
                                }
                                else
                                {
                                    getSupportFragmentManager().beginTransaction().hide(active).show(exploreFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                    /*for(; tagCounter > 0; tagCounter--)
                                    {
                                        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("ETAF" + (tagCounter - 1))).commit();
                                    }*/
                                    exploreTabActiveFragment = exploreFragment;
                                    active = exploreFragment;
                                }
                                return true;
                            case R.id.action_random:
                                getSupportFragmentManager().beginTransaction().hide(active).show(randomFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                active = randomFragment;
                                return true;
                            case R.id.action_lists:
                                getSupportFragmentManager().beginTransaction().hide(active).show(listsFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                active = listsFragment;
                                return true;
                        }
                        return false;
                    }
                });

        if (savedInstanceState != null) {
            FragmentManager fm = getSupportFragmentManager();

            String tagExploreFragment = savedInstanceState.getString(EXPLORE_FRAGMENT);
            exploreFragment = (ExploreFragment) fm.findFragmentByTag(tagExploreFragment);

            String tagRandomFragment = savedInstanceState.getString(RANDOM_FRAGMENT);
            randomFragment = (RandomFragment) fm.findFragmentByTag(tagRandomFragment);

            String tagListsFragment = savedInstanceState.getString(LISTS_FRAGMENT);
            listsFragment = (ListsFragment) fm.findFragmentByTag(tagListsFragment);

            String tagActiveFragment = savedInstanceState.getString(ACTIVE_FRAGMENT);
            active = fm.findFragmentByTag(tagActiveFragment);

            String tagExploreActiveFragment = savedInstanceState.getString(EXPLORE_ACTIVE_FRAGMENT);
            exploreTabActiveFragment = fm.findFragmentByTag(tagExploreActiveFragment);

        }
        else
        {
            exploreFragment = new ExploreFragment();
            randomFragment = new RandomFragment();
            listsFragment = new ListsFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.main_container, listsFragment, "1").hide(listsFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, randomFragment, "2").hide(randomFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, exploreFragment, "3").commit();

            active =  exploreFragment;
            exploreTabActiveFragment = exploreFragment;
            bottomNavigationView.setSelectedItemId(R.id.action_explore);
        }

    }

    //todo: создать свой стек для первой табы и переопределить поведение кнопки назад для него!

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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (exploreFragment != null) {
            outState.putString(EXPLORE_FRAGMENT, exploreFragment.getTag());
        }
        if (randomFragment != null) {
            outState.putString(RANDOM_FRAGMENT, randomFragment.getTag());
        }
        if (listsFragment != null) {
            outState.putString(LISTS_FRAGMENT, listsFragment.getTag());
        }
        if (active != null) {
            outState.putString(ACTIVE_FRAGMENT, active.getTag());
        }

        if (exploreTabActiveFragment != null) {
            outState.putString(EXPLORE_ACTIVE_FRAGMENT, exploreTabActiveFragment.getTag());
        }
    }

    private int tagCounter = 0;

    public void setExploreTabActiveFragment(Fragment exploreTabActiveFragment) {
        this.exploreTabActiveFragment = exploreTabActiveFragment;
        //todo: ? а при возврате на домашнюю страницу explore все удалять и обнулять
        getSupportFragmentManager().beginTransaction().hide(active).add(R.id.main_container, exploreTabActiveFragment, "ETAF"/* + tagCounter++*/).show(exploreTabActiveFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        active = exploreTabActiveFragment;
    }
}
