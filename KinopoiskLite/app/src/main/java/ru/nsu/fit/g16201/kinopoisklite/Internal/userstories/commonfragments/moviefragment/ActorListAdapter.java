package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.moviefragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Actor;
import ru.nsu.fit.g16201.kinopoisklite.R;

class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>{

    private List<Actor> dataSet;

    ActorListAdapter(List<Actor> dataSet) {
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
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actor_view, parent, false);
        return new ActorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        Actor actor = dataSet.get(position);


        holder.roleView.setText(actor.getCharacter());
        holder.nameView.setText(actor.getName());

        /*if(actor.getPosterPath().isPresent())
            Picasso.get().load(UrlConstructor.urlSingleImage(movie.getPosterPath().get())).into(holder.imageView);*/
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
