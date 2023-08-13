package com.osepoo.angamizaactual;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterPersonSearch extends RecyclerView.Adapter<AdapterPersonSearch.ProductViewHolder> implements Filterable {


    private Context mCtx;
    private List<PersonSearchUser> productList;
    private List<PersonSearchUser> productListFiltered;




    public AdapterPersonSearch(Context mCtx, List<PersonSearchUser> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        productListFiltered = new ArrayList<>(productList);
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.personsearchlist, null);
        return new ProductViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        PersonSearchUser product = productList.get(position);

        holder.textViewname.setText(product.getName());
        holder.textViewsex.setText(product.getSex());
        holder.textViewrace.setText(product.getRace());
        holder.textViewheight.setText(product.getHeight());
        holder.textViewbuild.setText(product.getBuild());
        holder.textViewlocation.setText(product.getLocation());
        holder.textViewage.setText(product.getAge());
        holder.textViewfacial.setText(product.getFacial());
        holder.textViewclothing.setText(product.getClothing());
        holder.textViewpicture.setText(product.getPicture());
        holder.textViewvoice.setText(product.getVoice());
        holder.textViewimagedesc.setText(product.getImage_desc());
        holder.textViewotherdesc.setText(product.getOther_desc());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PersonSearchUser> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productListFiltered);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (PersonSearchUser item : productListFiltered) {
                    if (item.getName().toLowerCase().contains(filterPattern) || item.getAge().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productList.clear();
            // productList.addAll((List) filterResults.values);
            // notifyDataSetChanged();
        }
    };

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewname, textViewsex, textViewrace, textViewheight, textViewbuild, textViewage, textViewfacial,
                textViewclothing, textViewvoice, textViewlocation, textViewotherdesc, textViewimagedesc, textViewpicture;

        CardView cardView;
        View mView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            cardView = itemView.findViewById(R.id.cardview1);
            textViewrace = itemView.findViewById(R.id.textViewTitle);
            textViewheight = itemView.findViewById(R.id.textViewShortDesc);
            textViewsex = itemView.findViewById(R.id.textViewRating);
            textViewbuild = itemView.findViewById(R.id.textVie2);
            textViewage = itemView.findViewById(R.id.textVie3);
            textViewfacial = itemView.findViewById(R.id.textVie4);
            textViewclothing = itemView.findViewById(R.id.textVie5);
            textViewpicture = itemView.findViewById(R.id.textVie6);
            textViewimagedesc = itemView.findViewById(R.id.textVie7);
            textViewlocation = itemView.findViewById(R.id.textVie8);
            textViewname = itemView.findViewById(R.id.textViewPrice);
            textViewvoice = itemView.findViewById(R.id.textVie9);
            textViewotherdesc = itemView.findViewById(R.id.textVie10);

            mView = itemView;
            mView.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            PersonSearchUser product = productList.get(position);
            String name = product.getName();
            String sex = product.getSex();
            String race = product.getRace();
            String height = product.getHeight();
            String build = product.getBuild();
            String age = product.getAge();
            String facial = product.getFacial();
            String clothing = product.getClothing();
            String voice = product.getVoice();
            String location = product.getLocation();
            String otherdesc = product.getOther_desc();
            String imagedesc = product.getImage_desc();
            String picture = product.getPicture();

            Intent beyonce = new Intent(mCtx, PersonSearchExtra.class);
            beyonce.putExtra("name" ,name);
            beyonce.putExtra("sex" ,sex);
            beyonce.putExtra("race" ,race);
            beyonce.putExtra("height" ,height);
            beyonce.putExtra("build" ,build);
            beyonce.putExtra("age" ,age);
            beyonce.putExtra("facial" ,facial);
            beyonce.putExtra("clothing" ,clothing);
            beyonce.putExtra("voice" ,voice);
            beyonce.putExtra("location " ,location );
            beyonce.putExtra("otherdesc " ,otherdesc );
            beyonce.putExtra("imagedesc" ,imagedesc);
            beyonce.putExtra("picture" ,picture);
            mCtx.startActivity(beyonce);
        }
    }

}