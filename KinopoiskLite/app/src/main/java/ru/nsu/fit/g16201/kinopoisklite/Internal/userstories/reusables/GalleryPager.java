package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.reusables;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.MovieRelatedImages;

public class GalleryPager extends PagerAdapter {

    public GalleryPager(List<MovieRelatedImages> images)
    {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
