package com.aaron.waller.buttontemplate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {


    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    public InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         *
         *
         *
         */
        mNavigationView.setItemIconTintList(null);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                String url;
                url="https://play.google.com/store/apps/dev?id=7433427674094948561";

                Boolean link;
                link=true;

                switch (menuItem.getItemId()){
                    case R.id.instagram: url = "https://instagram.com/_u/coding.empire/?r=sun1";
                        break;
                    case R.id.flappyjulien: url = "https://play.google.com/store/apps/details?id=com.pentagames.flappyjulien.android&hl=de";
                        break;
                    case R.id.flappybibi: url = "https://play.google.com/store/apps/details?id=com.pentagames.flappybibi.android&hl=de";
                        break;
                    case R.id.bibisepilierer: url = "https://play.google.com/store/apps/details?id=com.jakob2000.bibisepilierer&hl=de";
                        break;
                    case R.id.miimii: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.miimiisoundboard&hl=de";
                        break;
                    case R.id.simondesue: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.simondesuesoundboard&hl=de";
                        break;
                    case R.id.elotrix: url = "https://play.google.com/store/apps/details?id=com.penta.games.elotrixsoundboard&hl=de";
                        break;
                    case R.id.gronkh: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.gronkhsoundboard&hl=de";
                        break;
                    case R.id.tanzverbot: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.tanzverbotsoundboard&hl=de";
                        break;
                    case R.id.merkel: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.angelasoundboard&hl=de";
                        break;
                    case R.id.freshtorge: url = "https://play.google.com/store/apps/details?id=com.penta.games.freshtorgesoundboard&hl=de";
                        break;
                    case R.id.dummefage: url = "https://play.google.com/store/apps/details?id=com.penta.games.dummefragenet&hl=de";
                        break;
                    case R.id.teilen:
                        link=false;
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "✶"+ R.string.app_name +"✶");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "✶Hol dir das xxxxxxxx mit den besten Sprüchen✶\n\n xxxxxLINKxxxx");
                        startActivity(Intent.createChooser(shareIntent,  "Teilen über..."));
                        break;
                    case R.id.email:
                        link=false;
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto","pentabuttons@gmail.com", null));
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n\n\n\n\n[Packagename:com.pentagames."+ R.string.packagename + "  ---Diese Info nicht löschen---]");
                        startActivity(Intent.createChooser(emailIntent, "E-Mail senden..."));
                        break;

                }
                if(link){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    onPause();
                    startActivity(intent);
                }






                return false;
            }

        });


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();



    }






}
