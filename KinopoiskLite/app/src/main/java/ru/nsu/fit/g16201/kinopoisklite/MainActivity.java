package ru.nsu.fit.g16201.kinopoisklite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.tabfragments.ExploreFragment;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.tabfragments.RandomFragment;

public class MainActivity extends AppCompatActivity {

    private static final String EXPLORE_FRAGMENT = "explore_fragment";
    private static final String EXPLORE_ACTIVE_FRAGMENT = "explore_active_fragment";
    private static final String RANDOM_FRAGMENT = "random_fragment";
    private static final String ACTIVE_FRAGMENT = "active_fragment";

    private ExploreFragment exploreFragment;
    private Fragment exploreTabActiveFragment;

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

                            if(active == randomFragment)
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
                            getSupportFragmentManager().beginTransaction().hide(active).show(randomFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                            active = randomFragment;
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

        }
        else
        {
            exploreFragment = new ExploreFragment();
            randomFragment = new RandomFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.main_container, randomFragment, "2").hide(randomFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, exploreFragment, "3").commit();

            active =  exploreFragment;
            exploreTabActiveFragment = exploreFragment;
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
}
