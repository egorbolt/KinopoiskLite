package ru.nsu.fit.g16201.kinopoisklite.internal.userstories.commonfragments.moviefragment;

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

import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.tasks.PersonImagesTask;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models.Actor;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models.Image;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models.PersonImagesInfo;
import ru.nsu.fit.g16201.kinopoisklite.internal.userstories.commonfragments.showallfragment.MainThreadExecutor;
import ru.nsu.fit.g16201.kinopoisklite.R;

class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>{

    private static final String ERROR_TAG = "ActorListAdapter";
    private List<Actor> dataSet;
    private List<PersonImagesTask> personImagesTasks;

    static class PictureAsyncTask extends AsyncTask<PictureAsyncTaskParams, Void, Void> {
        @Override
        protected Void doInBackground(PictureAsyncTaskParams... params) {
            PersonImagesInfo pictures = null;
            try {
                pictures = params[0].personImagesTask.get();
            } catch (InterruptedException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
            }

            List<Image> posters = pictures.getProfiles();

            if(!posters.isEmpty())
            {
                String url = posters.get(posters.size() - 1).getFilePath();
                ImageView imageView = params[0].imageView;
                new MainThreadExecutor().execute(() -> Picasso.get().load(UrlConstructor.urlSingleImage(url)).into(imageView));
            }

            return null;
        }
    }

    private static class PictureAsyncTaskParams
    {
        ImageView imageView;
        PersonImagesTask personImagesTask;

        public PictureAsyncTaskParams(ImageView imageView, PersonImagesTask personImagesTask) {
            this.imageView = imageView;
            this.personImagesTask = personImagesTask;
        }
    }


    ActorListAdapter(List<Actor> dataSet, List<PersonImagesTask> personImagesTasks) {
        this.dataSet = dataSet;
        this.personImagesTasks = personImagesTasks;
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
            new PictureAsyncTask().execute(new PictureAsyncTaskParams(holder.imageView, personImagesTask));

        }

        holder.roleView.setText(actor.getCharacter());
        holder.nameView.setText(actor.getName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
