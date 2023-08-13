package com.osepoo.angamizaactual.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.osepoo.angamizaactual.FancyAboutPage;
import com.osepoo.angamizaactual.R;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
       /* setTitle("About Page");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        */

        FancyAboutPage fancyAboutPage = view.findViewById(R.id.fancyaboutpage);
        //fancyAboutPage.setCoverTintColor(Color.BLUE); //Optional
        fancyAboutPage.setCover(R.drawable.osepooactual);
        fancyAboutPage.setName("Ghali Muga");
        fancyAboutPage.setDescription("Android App Developer | Cyber Security Enthusiast.");
        fancyAboutPage.setAppIcon(R.mipmap.icon);
        fancyAboutPage.setAppName("Angamiza");
        fancyAboutPage.setVersionNameAsAppSubTitle("1.0");
        fancyAboutPage.setAppDescription("Angamiza is a law enforcement mobile app that functions as a data terminal for the user.\n\n" +
                "Angamiza uses state of the art material design components to bring forth a comfortable feel when using the application, it is compatible with 96.7% of the devices out there.\n\n" +
                "A fresh and new take on how the police system should function, Angamiza delivers on all fronts on what it was meant for ");
        fancyAboutPage.addEmailLink("benardghali31@gmail.com");
        /*fancyAboutPage.addFacebookLink("https://");
        fancyAboutPage.addTwitterLink("https://");
        fancyAboutPage.addLinkedinLink("https://");

         */
        fancyAboutPage.addGitHubLink("https://github.com/osepoo");
        return view;
    }
}