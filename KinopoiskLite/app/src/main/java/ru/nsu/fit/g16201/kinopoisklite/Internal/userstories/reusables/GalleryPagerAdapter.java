package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.reusables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.MovieRelatedImages;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class GalleryPagerAdapter extends PagerAdapter {

    private final List<MovieRelatedImages> images;
    private Context context;

    public GalleryPagerAdapter(List<MovieRelatedImages> images, Context context)
    {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_gallery_item, null);
        ImageView imageView = view.findViewById(R.id.gallery_image);
        Picasso.get().load(UrlConstructor.urlSingleImage(images.get(position).getFilePath())).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
