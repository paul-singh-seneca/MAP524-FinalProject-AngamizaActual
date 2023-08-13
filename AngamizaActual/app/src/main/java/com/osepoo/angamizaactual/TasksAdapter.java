package com.osepoo.angamizaactual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {


    private Context context;

    //List to store all superheroes
    List<TasksModel> tasks;

    //Constructor of this class
    public TasksAdapter(List<TasksModel> tasks, Context context){
        super();
        //Getting all superheroes
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tasks, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Getting the particular item from the list
       TasksModel taskss =  tasks.get(position);


        holder.textViewpriority.setText(taskss.getPriority());
        holder.textViewtask.setText(taskss.getTask());
        holder.textViewdate.setText(taskss.getDate_created());

        if(taskss.getPriority().equals("HIGH")){
            holder.textViewpriority.setBackgroundResource(R.drawable.red_rounded_solid);
        } else if (taskss.getPriority().equals("MEDIUM")){
            holder.textViewpriority.setBackgroundResource(R.drawable.yellow_rounded_solid);
        } else if (taskss.getPriority().equals("LOW")){
            holder.textViewpriority.setBackgroundResource(R.drawable.blue_rounded_solid);
        } else {
            holder.textViewpriority.setBackgroundResource(R.drawable.blue_rounded_solid);
        }

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Views
        public ImageView imageView;
        public TextView textViewpriority,textViewtask,textViewdate;


        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);

            textViewpriority = (TextView) itemView.findViewById(R.id.textviewpriority);
            textViewtask = (TextView) itemView.findViewById(R.id.textviewthetask);
            textViewdate = (TextView) itemView.findViewById(R.id.textviewdate);
        }
    }
}
