package com.osepoo.angamizaactual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CarSearchExtras extends AppCompatActivity {

    TextView textViewplate, textViewvin, textViewmodel, textViewmake, textViewvedesc, textViewdatestolen, textViewtheftdesc,
            textViewstatus, textViewdatelogged, textViewpicture, textViewimagedesc;

    ImageView imageViewcarpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_search_extras);

        textViewplate = findViewById(R.id.textViewa);
        textViewvin = findViewById(R.id.textViewb);
        textViewmodel = findViewById(R.id.textViewc);
        textViewmake = findViewById(R.id.textViewd);
        textViewvedesc = findViewById(R.id.textViewe);
        textViewdatestolen = findViewById(R.id.textViewf);
        textViewtheftdesc = findViewById(R.id.textViewg);
        textViewstatus = findViewById(R.id.textViewh);
        textViewdatelogged = findViewById(R.id.textViewi);
        textViewimagedesc = findViewById(R.id.textViewj);
        imageViewcarpic = findViewById(R.id.imageViewcarpic);

        Intent beyonce= getIntent();
        Bundle b = beyonce.getExtras();

        String a =(String) b.get("plate");
        textViewplate.setText(a);
        String z =(String) b.get("picture");
        //textView1.setText(z);
        String c =(String) b.get("vin");
        textViewvin.setText(c);
        String d =(String) b.get("model");
        textViewmodel.setText(d);
        String e =(String) b.get("make");
        textViewmake.setText(e);
        String f =(String) b.get("vehicledesc");
        textViewvedesc.setText(f);
        String g =(String) b.get("datestolen");
        textViewdatestolen.setText(g);
        String h =(String) b.get("theftdesc");
        textViewtheftdesc.setText(h);
        String i =(String) b.get("status");
        textViewstatus.setText(i);
        String j =(String) b.get("datelogged");
        textViewdatelogged.setText(j);
        String k =(String) b.get("imagedesc");
        textViewimagedesc.setText(k);


            Glide.with(CarSearchExtras.this)
                    .load(z)
                    .placeholder(R.drawable.loading) // any placeholder to load at start
                    .error(R.drawable.error)  // any image in case of error
                    .override(200, 200) // resizing
                    .centerCrop()
                    .into(imageViewcarpic);


    }
}