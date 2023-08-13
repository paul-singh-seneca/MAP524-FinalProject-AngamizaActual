package com.osepoo.angamizaactual.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.osepoo.angamizaactual.CardAdapter;
import com.osepoo.angamizaactual.FeedModel;
import com.osepoo.angamizaactual.R;
import com.osepoo.angamizaactual.URLs;
import com.osepoo.angamizaactual.VolleySingleton;
import com.osepoo.angamizaactual.pulltorefresh.CircleRefreshLayout;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.M)
public class FeedFragment extends Fragment implements RecyclerView.OnScrollChangeListener {

    Context context;
    View view;

    CoordinatorLayout coordinatorLayout;

    //Creating a List of superheroes
    private List<FeedModel> listSuperHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //Volley Request Queue
    private RequestQueue requestQueue;

    //The request counter to send ?page=1, ?page=2  requests
    private int requestCount = 1;

    RelativeLayout relativeLayout, relativeLayout2;
    TextView textViewah, textViewcantreach;
    ImageView imageViewnoconnection;

    CircleRefreshLayout swipeRefreshLayout1,swipeRefreshLayout2;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_feed, container, false);

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.feedcoordinator);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.mainfeedlay);
        relativeLayout2 = (RelativeLayout) view.findViewById(R.id.mainfeedlay2);
        imageViewnoconnection = (ImageView) view.findViewById(R.id.noconnectionview1);
        textViewah = (TextView) view.findViewById(R.id.textViewah);
        textViewcantreach = (TextView) view.findViewById(R.id.textViewcantreach);
        swipeRefreshLayout1 = (CircleRefreshLayout) view.findViewById(R.id.feedswipe);
        swipeRefreshLayout2 = (CircleRefreshLayout) view.findViewById(R.id.feedswipe2);

        AppBarLayout appBarLayout  = (AppBarLayout) getActivity().findViewById(R.id.appBarLayout);
        appBarLayout.setVisibility(View.VISIBLE);




        swipeRefreshLayout1.setOnRefreshListener(new CircleRefreshLayout.OnCircleRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                getData();
                swipeRefreshLayout1.finishRefreshing();
            }
        });



        swipeRefreshLayout2.setOnRefreshListener(new CircleRefreshLayout.OnCircleRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                swipeRefreshLayout2.setEnabled(true);
                getData();
                swipeRefreshLayout2.finishRefreshing();
            }
        });



        getData();


        //Initializing Views
        recyclerView = (RecyclerView) view.findViewById(R.id.feedrecyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        context = getContext();

        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();
       // requestQueue = Volley.newRequestQueue(context);

        //Calling method to get data to fetch data

        //Adding an scroll change listener to recyclerview
        recyclerView.setOnScrollChangeListener(this);

        //initializing our adapter
        adapter = new CardAdapter(listSuperHeroes, context);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return view;
    }

   /* public void doEverything(){
        if (isNetworkAvailable()) {

            getData();
            relativeLayout.setVisibility(View.VISIBLE);
            relativeLayout2.setVisibility(View.INVISIBLE);
            swipeRefreshLayout2.setEnabled(false);
            swipeRefreshLayout1.setEnabled(true);
        }else{
            swipeRefreshLayout2.setEnabled(true);
            swipeRefreshLayout1.setEnabled(false);
            relativeLayout.setVisibility(View.INVISIBLE);
            relativeLayout2.setVisibility(View.VISIBLE);

            Glide.with(requireContext())
                    .load(R.raw.noconn)
                    .placeholder(R.drawable.error) // any placeholder to load at start
                    .error(R.drawable.error)  // any image in case of error
                    .override(200, 200) // resizing
                    .centerCrop()
                    .into(imageViewnoconnection);

            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Please turn on your internet connection", Snackbar.LENGTH_SHORT)
                    .setAction("Turn On",
                            // If the Undo button
                            // is pressed, show
                            // the message using Toast
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view)
                                {
                                    Intent intent2 = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    startActivity(intent2);
                                }
                            });



            snackbar.show();

        }
    }

    */

    //Request to get json from server we are passing an integer here
    //This integer will used to specify the page number for the request ?page = requestcount
    //This method would return a JsonArrayRequest that will be added to the request queue
    private JsonArrayRequest getDataFromServer(int requestCount) {
        //Initializing ProgressBar
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);

        //Displaying Progressbar
        progressBar.setVisibility(View.VISIBLE);
        //setProgressBarIndeterminateVisibility(true);

        //JsonArrayRequest of volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLs.URL_FEED + String.valueOf(requestCount),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Calling method parseData to parse the json response

                        relativeLayout.setVisibility(View.VISIBLE);
                        relativeLayout2.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout2.setEnabled(false);
                        swipeRefreshLayout1.setEnabled(true);

                        parseData(response);
                        //Hiding the progressbar
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        //If an error occurs that means end of the list has reached
                        //Toast.makeText(getContext(), "No More Items Available", Toast.LENGTH_SHORT).show();
                       //FancyToast.makeText(getContext(), "You're all caught up", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                        //Snackbar snackbar = Snackbar.make(coordinatorLayout, "You're all caught up", Snackbar.LENGTH_SHORT);
                        //snackbar.show();

                        relativeLayout.setVisibility(View.VISIBLE);
                        relativeLayout2.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout2.setEnabled(false);
                        swipeRefreshLayout1.setEnabled(true);

                        if(error instanceof NoConnectionError){
                            ConnectivityManager cm = (ConnectivityManager)getActivity()
                                    .getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo activeNetwork = null;
                            if (cm != null) {
                                activeNetwork = cm.getActiveNetworkInfo();
                            }
                            if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
                                errorPage();
                                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Server is not connected to internet.", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                //Toast.makeText(getActivity(), "Server is not connected to internet.",Toast.LENGTH_SHORT).show();
                            } else {
                                errorPage();
                                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Your device is not connected to internet.", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                //Toast.makeText(getActivity(), "Your device is not connected to internet.",Toast.LENGTH_SHORT).show();
                            }
                      /*  } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                                || (error.getCause().getMessage() != null
                                && error.getCause().getMessage().contains("connection"))){
                            Toast.makeText(getActivity(), "Your device is not connected to internet.",
                                    Toast.LENGTH_SHORT).show();

                       */
                        } else if (error.getCause() instanceof MalformedURLException){
                            errorPage();
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Bad Request.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Bad Request.", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                                || error.getCause() instanceof JSONException
                                || error.getCause() instanceof XmlPullParserException){
                            //Snackbar snackbar = Snackbar.make(coordinatorLayout, "Parse Error (because of invalid json or xml).", Snackbar.LENGTH_SHORT);
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "You're all caught up.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Parse Error (because of invalid json or xml).",Toast.LENGTH_SHORT).show();
                        } else if (error.getCause() instanceof OutOfMemoryError){
                            errorPage();
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Out Of Memory Error.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
                        }else if (error instanceof AuthFailureError){
                            errorPage();
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "server couldn't find the authenticated request.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "server couldn't find the authenticated request.", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                            errorPage();
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Server is not responding.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Server is not responding.", Toast.LENGTH_SHORT).show();
                        }else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                                || error.getCause() instanceof ConnectTimeoutException
                                || error.getCause() instanceof SocketException
                                || (error.getCause().getMessage() != null
                                && error.getCause().getMessage().contains("Connection timed out"))) {
                            errorPage();
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Connection timeout error", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Connection timeout error",Toast.LENGTH_SHORT).show();
                        } else {
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "You're all caught up", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "An unknown error occurred.",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        //Returning the request
        return jsonArrayRequest;
    }

    //This method will get data from the web api
    private void getData() {
        //Adding the method to the queue by calling the method getDataFromServer
       // requestQueue.add(getDataFromServer(requestCount));
        //CustomVolleyRequest.getInstance(context).addToRequestQueue(getDataFromServer(requestCount));
        VolleySingleton.getInstance(requireContext()).addToRequestQueue(getDataFromServer(requestCount));
        //Incrementing the request counter
        requestCount++;
    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            //Creating the superhero object
            FeedModel superHero = new FeedModel();
            JSONObject json = null;
            try {
                //Getting json
                json = array.getJSONObject(i);

                //Adding data to the superhero object
                superHero.setPhoto(json.getString(URLs.TAG_PHOTO));
                superHero.setDescription(json.getString(URLs.TAG_DESCRIPTION));
                superHero.setPolice_precinct(json.getString(URLs.TAG_POLICE_P));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Adding the superhero object to the list
            listSuperHeroes.add(superHero);
        }

        //Notifying the adapter that data has been added or changed
        adapter.notifyDataSetChanged();
    }

    //This method would check that the recyclerview scroll has reached the bottom or not
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }

    //Overriden method to detect scrolling
    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        //Ifscrolled at last then
        if (isLastItemDisplaying(recyclerView)) {
            //Calling the method getdata again
            getData();
        }
    }
    private boolean isNetworkAvailable() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void errorPage(){
        swipeRefreshLayout2.setEnabled(true);
        swipeRefreshLayout1.setEnabled(false);
        relativeLayout.setVisibility(View.INVISIBLE);
        relativeLayout2.setVisibility(View.VISIBLE);

        Glide.with(requireContext())
                .load(R.raw.noconn)
                .placeholder(R.drawable.error) // any placeholder to load at start
                .error(R.drawable.error)  // any image in case of error
                .override(200, 200) // resizing
                .centerCrop()
                .into(imageViewnoconnection);
    }

}