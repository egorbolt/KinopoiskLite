package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.moviefragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PersonImagesTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Actor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Image;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PersonImagesInfo;
import ru.nsu.fit.g16201.kinopoisklite.R;

class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>{

    private static final String ERROR_TAG = "ActorListAdapter";
    private List<Actor> dataSet;
    private List<PersonImagesTask> personImagesTasks;
    private Context context;

    ActorListAdapter(List<Actor> dataSet, List<PersonImagesTask> personImagesTasks, Context context) {
        this.dataSet = dataSet;
        this.personImagesTasks = personImagesTasks;
        this.context = context;
    }

    static class ActorViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameView, roleView;

        ActorViewHolder(@NonNull View view) {
            super(view);

            imageView = view.findViewById(R.id.actor_image_view);
            roleView = view.findViewById(R.id.textViewRoleName);
            nameView = view.findViewById(R.id.textViewActorName);
        }
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actor_view, parent, false);
        return new ActorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        Actor actor = dataSet.get(position);
        PersonImagesTask personImagesTask = personImagesTasks.get(position);
        if(personImagesTask != null)
        {
            System.out.println("here");
            PersonImagesInfo pictures;
            try {
                pictures = personImagesTask.get();
                /*List<Image> posters = pictures.getProfiles();

                if(!posters.isEmpty())
                {
                    Picasso.get().load(UrlConstructor.urlSingleImage(posters.get(posters.size() - 1).getFilePath())).into(holder.imageView);
                }*/
            }
            catch (InterruptedException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
            }

        }

        holder.roleView.setText(actor.getCharacter());
        holder.nameView.setText(actor.getName());
        System.out.println("here");
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
