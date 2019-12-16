package ru.nsu.fit.g16201.kinopoisklite.fragments;

import org.junit.Test;

import ru.nsu.fit.g16201.kinopoisklite.internal.userstories.commonfragments.moviefragment.MovieFragment;

import static org.junit.Assert.*;

public class MovieFragmentTest {


    @Test
    public void newInstance() {
        MovieFragment mf = MovieFragment.newInstance(44);
        assertNotNull(mf);
    }

}