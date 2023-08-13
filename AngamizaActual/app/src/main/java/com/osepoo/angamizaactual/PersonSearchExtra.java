package com.osepoo.angamizaactual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PersonSearchExtra extends AppCompatActivity {

    TextView textViewname, textViewsex, textViewrace, textViewheight, textViewbuild, textViewage, textViewfacial,
            textViewclothing, textViewvoice, textViewlocation, textViewotherdesc, textViewimagedesc, textViewpicture;

    ImageView imageViewpersonpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_search_extra);

        textViewname = findViewById(R.id.textViewa);
        textViewsex = findViewById(R.id.textViewb);
        textViewrace = findViewById(R.id.textViewc);
        textViewheight = findViewById(R.id.textViewd);
        textViewbuild = findViewById(R.id.textViewe);
        textViewage = findViewById(R.id.textViewf);
        textViewfacial = findViewById(R.id.textViewg);
        textViewclothing = findViewById(R.id.textViewh);
        textViewvoice = findViewById(R.id.textViewi);
        textViewlocation = findViewById(R.id.textViewk);
        textViewotherdesc = findViewById(R.id.textViewl);
        textViewimagedesc = findViewById(R.id.textViewj);
        imageViewpersonpic = findViewById(R.id.imageViewpersonpic);

        Intent beyonce= getIntent();
        Bundle b = beyonce.getExtras();

        String a =(String) b.get("name");
        textViewname.setText(a);
        String z =(String) b.get("sex");
        textViewsex.setText(z);
        String c =(String) b.get("race");
        textViewrace.setText(c);
        String d =(String) b.get("height");
        textViewheight.setText(d);
        String e =(String) b.get("build");
        textViewbuild.setText(e);
        String f =(String) b.get("age");
        textViewage.setText(f);
        String g =(String) b.get("facial");
        textViewfacial.setText(g);
        String h =(String) b.get("clothing");
        textViewclothing.setText(h);
        String i =(String) b.get("voice");
        textViewvoice.setText(i);
        String j =(String) b.get("location");
        textViewlocation.setText(j);
        String l =(String) b.get("otherdesc");
        textViewotherdesc.setText(l);
        String m =(String) b.get("imagedesc");
        textViewimagedesc.setText(m);
        String k =(String) b.get("picture");
        textViewimagedesc.setText(k);

        Glide.with(PersonSearchExtra.this)
                .load(z)
                .placeholder(R.drawable.loading) // any placeholder to load at start
                .error(R.drawable.error)  // any image in case of error
                .override(200, 200) // resizing
                .centerCrop()
                .into(imageViewpersonpic);

    }
}