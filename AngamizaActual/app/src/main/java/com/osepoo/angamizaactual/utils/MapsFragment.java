package com.osepoo.angamizaactual.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.osepoo.angamizaactual.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.IOException;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback,
        LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Context context;
    int PERMISSION_ID = 44;
    View view;
    SearchView searchView;
    TextView textViewlats, textViewlongs, textViewcountry, textViewcity, textViewarea;
    TextView textViewlats1, textViewlongs1, textViewcountry1, textViewcity1, textViewarea1;
    CoordinatorLayout coordinatorLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_maps, container, false);

        coordinatorLayout = view.findViewById(R.id.mapscoordinator);

        AppBarLayout appBarLayout  = (AppBarLayout) getActivity().findViewById(R.id.appBarLayout);
        appBarLayout.setVisibility(View.GONE);

        if (checkPermissions()) {
            // check if location is enabled
            if (isLocationEnabled()) {
                // getting last location from FusedLocationClient object
                if (isNetworkAvailable()) {

                }else{

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
            } else {
                getActivity().getFragmentManager().popBackStack();
                FancyToast.makeText(requireContext(), "Please turn on your location", FancyToast.LENGTH_LONG,FancyToast.WARNING,R.drawable.crying,false).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);


        //mMap.setMyLocationEnabled(true);
       // mMap.getUiSettings().setMyLocationButtonEnabled(true);

        textViewlats = view.findViewById(R.id.textviewlats);
        textViewlongs = view.findViewById(R.id.textviewlongs);
        textViewcountry = view.findViewById(R.id.textviewcountry);
        textViewcity = view.findViewById(R.id.textviewcity);
        textViewarea = view.findViewById(R.id.textviewname);

        textViewlats1 = view.findViewById(R.id.textviewlats1);
        textViewlongs1 = view.findViewById(R.id.textviewlongs1);
        textViewcountry1 = view.findViewById(R.id.textviewcountry1);
        textViewcity1 = view.findViewById(R.id.textviewcity1);
        textViewarea1 = view.findViewById(R.id.textviewname1);

        searchView = view.findViewById(R.id.idSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                String location = searchView.getQuery().toString();

                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(requireContext());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                   mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    //Toast.makeText(requireContext(),address.getLatitude()+" "+address.getLongitude(),Toast.LENGTH_LONG).show();

                    Double latsprec = address.getLatitude();
                    Double longsprec = address.getLongitude();
                    String areaprec = address.getFeatureName();
                    String cityprec = address.getAdminArea();
                    String countryprec = address.getCountryName();


                    if(!latsprec.equals("") || latsprec != null){
                        textViewlats1.setText(String.valueOf(latsprec));
                        textViewlongs1.setText(String.valueOf(longsprec));
                        textViewcountry1.setText(countryprec);
                        textViewcity1.setText(cityprec);
                        textViewarea1.setText(areaprec);
                    }

                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });




        return view;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);

            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }

    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(requireContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

        Double doubletats = Double.parseDouble(String.valueOf(location.getLatitude()));
        Double doublelongs = Double.parseDouble(String.valueOf(location.getLongitude()));
        //getLocation
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(requireContext());
            try {
                addressList = geocoder.getFromLocation(doubletats,doublelongs ,1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            Double latsprec = location.getLatitude();
            Double longsprec = location.getLongitude();
           String areaprec = addressList.get(0).getFeatureName();
            String cityprec = addressList.get(0).getAdminArea();
           String countryprec = addressList.get(0).getCountryName();


            if(!latsprec.equals("") || latsprec != null){
                textViewlats.setText(String.valueOf(latsprec));
                textViewlongs.setText(String.valueOf(longsprec));
                textViewcountry.setText(countryprec);
                textViewcity.setText(cityprec);
                textViewarea.setText(areaprec);
            }
            }

        }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location on Android 10.0 and higher, use:
        // return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_ID);
    }

    // method to check if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
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

  /*  public void searchLocation() {
        EditText locationSearch = (EditText) view.findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(requireContext());
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            Toast.makeText(requireContext(),address.getLatitude()+" "+address.getLongitude(),Toast.LENGTH_LONG).show();
        }
    }

   */

}