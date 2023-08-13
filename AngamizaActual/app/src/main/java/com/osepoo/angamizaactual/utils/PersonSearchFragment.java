package com.osepoo.angamizaactual.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.osepoo.angamizaactual.AdapterPersonSearch;
import com.osepoo.angamizaactual.PersonSearchUser;
import com.osepoo.angamizaactual.ProductsAdapter4;
import com.osepoo.angamizaactual.R;
import com.osepoo.angamizaactual.ReturningVisitorUser;
import com.osepoo.angamizaactual.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonSearchFragment extends Fragment {

    List<PersonSearchUser> productList;

    private ArrayList<PersonSearchUser> dataSet;
    //the recyclerview
    RecyclerView recyclerView;

    SearchView searchView;

    //NestedScrollView nestedSV;

    String name, make;

    int limit = 50;
    int offset = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_search, container, false);

        //getting the recyclerview from xml
        recyclerView = view.findViewById(R.id.recylcerView16);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        searchView = (SearchView) view.findViewById(R.id.searchview13);

        //nestedSV = findViewById(R.id.idNestedSV);

        //initializing the productlist
        productList = new ArrayList<>();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.length() >= 1) {
                    productList.clear();
                    name = newText.toString().trim();
                    make = newText.toString().trim();
                    searching();
                } else {
                    //loadProducts();
                }
                return true;
            }
        });


        return  view;

    }

    private void searching() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_SEARCHPERSON,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new PersonSearchUser(
                                        product.getInt("id"),
                                        product.getString("name"),
                                        product.getString("sex"),
                                        product.getString("race"),
                                        product.getString("height"),
                                        product.getString("build"),
                                        product.getString("age"),
                                        product.getString("facial"),
                                        product.getString("clothing"),
                                        product.getString("voice"),
                                        product.getString("location"),
                                        product.getString("other_desc"),
                                        product.getString("image_desc"),
                                        product.getString("picture")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            AdapterPersonSearch adapter = new AdapterPersonSearch (requireContext(), productList);
                            recyclerView.setAdapter(adapter);
                            /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String query) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {

                                    adapter.getFilter().filter(newText);

                                    return true;
                                }
                            });*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("age",make);
                //params.put("limit", String.valueOf(limit));
                //params.put("offset", String.valueOf(offset));
                return params;
            }
        };
        //adding our stringrequest to queue
        Volley.newRequestQueue(requireContext()).add(stringRequest);



    }
}