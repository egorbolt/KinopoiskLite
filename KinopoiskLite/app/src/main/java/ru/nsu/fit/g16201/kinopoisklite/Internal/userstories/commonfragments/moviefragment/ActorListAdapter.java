package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.moviefragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PersonImagesTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Actor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Image;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PersonImagesInfo;
import ru.nsu.fit.g16201.kinopoisklite.R;

class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>{

    private static final String ACTOR_LIST_ADAPTER_TAG = "ActorListAdapter";
    private List<Actor> dataSet;

    ActorListAdapter(List<Actor> dataSet, List<PersonImagesTask> personImagesTasks) {
        this.dataSet = dataSet;
    }

    class ActorViewHolder extends RecyclerView.ViewHolder {
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
        PersonImagesTask task = null;
        try {
            task = API.loadActorPictures(actor.getId());
        } catch (MalformedURLException e) {
            Log.e(ACTOR_LIST_ADAPTER_TAG, "Malformed URL" + e.getMessage());
        }

        holder.roleView.setText(actor.getCharacter());
        holder.nameView.setText(actor.getName());

        if(task != null)
        {
            PersonImagesInfo pictures;
            try {
                pictures = task.get();
                List<Image> posters = pictures.getProfiles();
                if(!posters.isEmpty())
                    Picasso.get().load(UrlConstructor.urlSingleImage(posters.get(0).getFilePath())).into(holder.imageView);

            }
            catch (InterruptedException e)
            {
                Log.e(ACTOR_LIST_ADAPTER_TAG, "Can't retrieve data: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException e)
            {
                Log.e(ACTOR_LIST_ADAPTER_TAG, "Can't retrieve data: " + e.getMessage());
            }



        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
