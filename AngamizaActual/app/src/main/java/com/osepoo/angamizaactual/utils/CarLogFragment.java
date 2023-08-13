package com.osepoo.angamizaactual.utils;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.osepoo.ImagePicker;
import com.osepoo.angamizaactual.R;
import com.osepoo.angamizaactual.URLs;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import de.hdodenhof.circleimageview.CircleImageView;


public class CarLogFragment extends Fragment {

    Button uploadeverything, UploadImageOnServerButton;
    RelativeLayout relativeLayoutimage, relativeLayouttexts;
    EditText imageName;

    Bitmap FixBitmap;

    String ImageTag = "image_tag" ;

    String ImageName = "image_data" ;

    ProgressDialog progressDialog ;

    ByteArrayOutputStream byteArrayOutputStream ;

    byte[] byteArray ;

    String ConvertImage ;

    String image_desc;

    HttpURLConnection httpURLConnection ;

    URL url;

    OutputStream outputStream;

    BufferedWriter bufferedWriter ;

    int RC ;

    BufferedReader bufferedReader ;

    StringBuilder stringBuilder;

    boolean check = true;

    private int GALLERY = 1, CAMERA = 2;

    CircleImageView circleImageView;
    private static final int PICK_IMAGE_ID = 253;

    EditText editTextplate,editTextvin,editTextmodel,editTextmake,editTextvehicledesc,editTextdatestolen,editTexttheftdesc,edittextsatus;
    String vehicleplate,vehiclevin,vehmodel,vehmake,vehdesc,vehdatestolen,vehtheftdetails,vehstatus;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_log, container, false);

        byteArrayOutputStream = new ByteArrayOutputStream();

        circleImageView = (CircleImageView) view.findViewById(R.id.imageviewcar);

        UploadImageOnServerButton = (Button)view.findViewById(R.id.carloguploadimage);

        imageName=(EditText)view.findViewById(R.id.imageName);

        editTextplate = view.findViewById(R.id.carlogplate);
        editTextvin = view.findViewById(R.id.carlogvin);
        editTextmodel = view.findViewById(R.id.carlogmodel);
        editTextmake = view.findViewById(R.id.carlogmake);
        editTextvehicledesc = view.findViewById(R.id.carlogdesc);
        editTextdatestolen = view.findViewById(R.id.carlogtheftdate);
        editTexttheftdesc = view.findViewById(R.id.carlogtheftdetails);
        edittextsatus = view.findViewById(R.id.carlogstatus);
        uploadeverything = view.findViewById(R.id.carloguploadfinish);


        relativeLayoutimage = view.findViewById(R.id.layoutimage2);
        relativeLayouttexts = view.findViewById(R.id.layoutstuff2);

        relativeLayoutimage.setVisibility(View.VISIBLE);
        relativeLayouttexts.setVisibility(View.GONE);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooseImageIntent = ImagePicker.getPickImageIntent(requireContext());
                startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
            }
        });

        UploadImageOnServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image_desc = imageName.getText().toString();

                if (TextUtils.isEmpty(image_desc)) {
                    imageName.setError("Please enter a valid description");
                    imageName.requestFocus();
                    return;
                }

                UploadImageToServer();

                relativeLayoutimage.setVisibility(View.GONE);
                relativeLayouttexts.setVisibility(View.VISIBLE);

            }
        });

        uploadeverything.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carlogUpload();
            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode) {
            case PICK_IMAGE_ID:
                FixBitmap = ImagePicker.getImageFromResult(requireContext(), resultCode, data);
                circleImageView.setImageBitmap(FixBitmap);
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;

        }
    }

    public void UploadImageToServer(){

        FixBitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);

        byteArray = byteArrayOutputStream.toByteArray();

        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                progressDialog = ProgressDialog.show(requireContext(),"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                progressDialog.dismiss();

                Toast.makeText(requireContext(),string1,Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageTag, image_desc);

                HashMapParams.put(ImageName, ConvertImage);

                String FinalData = imageProcessClass.ImageHttpRequest("http://192.168.137.1/angamiza/uploadcarlogimage.php", HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {
                url = new URL(requestURL);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(20000);

                httpURLConnection.setConnectTimeout(20000);

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);

                httpURLConnection.setDoOutput(true);

                outputStream = httpURLConnection.getOutputStream();

                bufferedWriter = new BufferedWriter(

                        new OutputStreamWriter(outputStream, "UTF-8"));

                bufferedWriter.write(bufferedWriterDataFN(PData));

                bufferedWriter.flush();

                bufferedWriter.close();

                outputStream.close();

                RC = httpURLConnection.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReader.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            stringBuilder = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check)
                    check = false;
                else
                    stringBuilder.append("&");

                stringBuilder.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilder.append("=");

                stringBuilder.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilder.toString();
        }

    }

    private void carlogUpload() {

        vehicleplate = editTextplate.getText().toString();
        vehiclevin = editTextvin.getText().toString();
        vehmodel= editTextmodel.getText().toString();
        vehmake= editTextmake.getText().toString();
        vehdesc= editTextvehicledesc.getText().toString();
        vehdatestolen= editTextdatestolen.getText().toString();
        vehtheftdetails= editTexttheftdesc.getText().toString();
        vehstatus= edittextsatus.getText().toString();
        image_desc = imageName.getText().toString();

        if (TextUtils.isEmpty(vehicleplate)) {
            editTextplate.setError("Please enter a plate number");
            editTextplate.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(vehmake)) {
            editTextmake.setError("Please enter the vehicle's make");
            editTextmake.requestFocus();
            return;
        }


        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_CARLOGTEXTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(requireContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(requireContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vehicle_plate", vehicleplate);
                params.put("vin", vehiclevin);
                params.put("model", vehmodel);
                params.put("vehicle_make", vehmake);
                params.put("vehicle_desc", vehdesc);
                params.put("theft_date", vehdatestolen);
                params.put("theft_details", vehtheftdetails);
                params.put("status", vehstatus);
                params.put("image_desc", image_desc);
                return params;
            }
        };

        Volley.newRequestQueue(requireContext()).add(stringRequest);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //AdapterShower2.this.finish();
                //Intent i = new Intent(AdapterShower2.this,VisitorsPage.class);
               // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // startActivity(i);

                //getActivity().getSupportFragmentManager().popBackStack();

                getActivity().finish();
            }
        },1000);



    }
}