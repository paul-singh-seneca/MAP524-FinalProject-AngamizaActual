package com.osepoo.angamizaactual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import java.util.List;


public class TasksLeaderAdapter extends RecyclerView.Adapter<TasksLeaderAdapter.ViewHolder> {

    //Imageloader to load image
    private ImageLoader imageLoader;
    private Context context;

    //List to store all superheroes
    List<TasksLeaderModel> tasksLeaderModels;

    //Constructor of this class
    public TasksLeaderAdapter(List<TasksLeaderModel> tasksLeaderModels, Context context){
        super();
        //Getting all superheroes
        this.tasksLeaderModels = tasksLeaderModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tasks2, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Getting the particular item from the list
        TasksLeaderModel superHero =  tasksLeaderModels.get(position);

        //Loading image from url
        //loading the image

        if (context != null) {
            Glide.with(context)
                    .load(superHero.getPhoto())
                    .placeholder(R.drawable.loading) // any placeholder to load at start
                    .error(R.drawable.error)  // any image in case of error
                    .override(230, 123) // resizing
                    .centerCrop()
                    .into(holder.imageView);
        }


        //imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        //imageLoader.get(superHero.getPhoto(), ImageLoader.getImageListener(holder.imageView, R.drawable.angamizalogo, android.R.drawable.ic_dialog_alert));

        //Showing data on the views
        //holder.imageView.setImageUrl(superHero.getPhoto(), imageLoader);
        holder.textViewname.setText(superHero.getName());
        holder.textViewprecinct.setText(superHero.getPrecinct());


    }

    @Override
    public int getItemCount() {
        return tasksLeaderModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Views
        public ImageView imageView;
        public TextView textViewname,textViewprecinct;


        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageviewleader);
            textViewname = (TextView) itemView.findViewById(R.id.textviewnameleader);
            textViewprecinct = (TextView) itemView.findViewById(R.id.textviewprecinct);

        }
    }
}
