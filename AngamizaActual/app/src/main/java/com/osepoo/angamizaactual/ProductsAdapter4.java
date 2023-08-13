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

public class ProductsAdapter4 extends RecyclerView.Adapter<ProductsAdapter4.ProductViewHolder> implements Filterable {


    private Context mCtx;
    private List<ReturningVisitorUser> productList;
    private List<ReturningVisitorUser> productListFiltered;




    public ProductsAdapter4(Context mCtx, List<ReturningVisitorUser> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        productListFiltered = new ArrayList<>(productList);
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list4, null);
        return new ProductViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ReturningVisitorUser product = productList.get(position);

        holder.textViewplate.setText(product.getVehicle_plate());
        holder.textViewvin.setText(product.getVin());
        holder.textViewmodel.setText(product.getModel());
        holder.textViewmake.setText(product.getVehicle_make());
        holder.textViewvedesc.setText(product.getVehicle_desc());
        //holder.textViewidno.setText(product.getId_no());
        holder.textViewdatestolen.setText(product.getTheft_date());
        holder.textViewtheftdesc.setText(product.getTheft_details());
        holder.textViewstatus.setText(product.getStatus());
        holder.textViewpicture.setText(product.getImage());
        holder.textViewdatelogged.setText(product.getDate_logged());
        holder.textViewimagedesc.setText(product.getImage_desc());
       // holder.textViewCompany.setText(product.getCompany());
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
            List<ReturningVisitorUser> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productListFiltered);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ReturningVisitorUser item : productListFiltered) {
                    if (item.getVehicle_plate().toLowerCase().contains(filterPattern) || item.getVehicle_make().toLowerCase().contains(filterPattern)) {
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

        TextView textViewplate, textViewvin, textViewmodel, textViewmake, textViewvedesc, textViewdatestolen, textViewtheftdesc,
                textViewstatus, textViewdatelogged, textViewpicture, textViewimagedesc;

        CardView cardView;
        View mView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            cardView = itemView.findViewById(R.id.cardview1);
            textViewmake = itemView.findViewById(R.id.textViewTitle);
            textViewvedesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewvin = itemView.findViewById(R.id.textViewRating);
            textViewdatestolen = itemView.findViewById(R.id.textVie2);
            textViewtheftdesc = itemView.findViewById(R.id.textVie3);
            textViewstatus = itemView.findViewById(R.id.textVie4);
            textViewdatelogged = itemView.findViewById(R.id.textVie5);
            textViewpicture = itemView.findViewById(R.id.textVie6);
            textViewimagedesc = itemView.findViewById(R.id.textVie7);
            textViewmodel = itemView.findViewById(R.id.textVie8);
            textViewplate = itemView.findViewById(R.id.textViewPrice);
            //textViewCompany = itemView.findViewById(R.id.textVie9);

            mView = itemView;
            mView.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            ReturningVisitorUser product = productList.get(position);
            String plate = product.getVehicle_plate();
            String picture = product.getImage();
            String vin = product.getVin();
            String model = product.getModel();
            String make = product.getVehicle_make();
            String vehicledesc = product.getVehicle_desc();
            String datestolen = product.getTheft_date();
            String theftdesc = product.getTheft_details();
            String status = product.getStatus();
            String datelogged = product.getDate_logged();
            String imagedesc = product.getImage_desc();
           // String Company = product.getCompany();

            Intent beyonce = new Intent(mCtx, CarSearchExtras.class);
            beyonce.putExtra("plate" ,plate);
            beyonce.putExtra("picture" ,picture);
            beyonce.putExtra("vin" ,vin);
            beyonce.putExtra("model" ,model);
            beyonce.putExtra("make" ,make);
            beyonce.putExtra("vehicledesc" ,vehicledesc);
            beyonce.putExtra("datestolen" ,datestolen);
            beyonce.putExtra("theftdesc" ,theftdesc);
            beyonce.putExtra("status" ,status);
            beyonce.putExtra("datelogged " ,datelogged );
            beyonce.putExtra("imagedesc" ,imagedesc);
            //beyonce.putExtra("Company" ,Company);
            mCtx.startActivity(beyonce);
        }
    }

}

