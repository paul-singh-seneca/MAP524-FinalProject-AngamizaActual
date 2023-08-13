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

/**
 * Created by Belal on 11/9/2015.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    //Imageloader to load image
    private ImageLoader imageLoader;
    private Context context;

    //List to store all superheroes
    List<FeedModel> superHeroes;

    //Constructor of this class
    public CardAdapter(List<FeedModel> superHeroes, Context context){
        super();
        //Getting all superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Getting the particular item from the list
        FeedModel superHero =  superHeroes.get(position);

        //Loading image from url
        //loading the image

        if (context != null) {
            Glide.with(context)
                    .load(superHero.getPhoto())
                    .placeholder(R.drawable.loading) // any placeholder to load at start
                    .error(R.drawable.error)  // any image in case of error
                    .override(200, 200) // resizing
                    .centerCrop()
                    .into(holder.imageView);
        }


        //imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        //imageLoader.get(superHero.getPhoto(), ImageLoader.getImageListener(holder.imageView, R.drawable.angamizalogo, android.R.drawable.ic_dialog_alert));

        //Showing data on the views
       //holder.imageView.setImageUrl(superHero.getPhoto(), imageLoader);
        holder.textViewDesc.setText(superHero.getDescription());
       holder.textViewUser1.setText(superHero.getPolice_precinct());
        holder.textViewUser2.setText(superHero.getPolice_precinct());

    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Views
        public ImageView imageView;
        public TextView textViewDesc,textViewUser1,textViewUser2;


        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.ivFeedCenter);
            textViewDesc = (TextView) itemView.findViewById(R.id.ivFeedBottom);
            textViewUser1 = (TextView) itemView.findViewById(R.id.username1);
            textViewUser2 = (TextView) itemView.findViewById(R.id.username2);
        }
    }
}
