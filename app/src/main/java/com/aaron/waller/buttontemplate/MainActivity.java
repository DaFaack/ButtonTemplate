package com.aaron.waller.buttontemplate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;

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

        final File FILES_PATH = new File(Environment.getExternalStorageDirectory(), "Android/data/"+ getText(R.string.package_name) +"/files");


        if (Environment.MEDIA_MOUNTED.equals(

                Environment.getExternalStorageState())) {

            if (!FILES_PATH.mkdirs()) {

                Log.w("error", "Could not create " + FILES_PATH);

            }

        } else {

            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();

            finish();

        }


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
                url="https://play.google.com/store/apps/developer?id=PentaButtons";

                Boolean link;
                link=true;

                switch (menuItem.getItemId()){


                    case R.id.vorschlagen: url = "https://docs.google.com/forms/d/e/1FAIpQLSfPMxPAJyfI_Yhz4xj44cmuFErDk-oyZU-PJ4pH-ARMYZhf8A/viewform?usp=sf_link";
                        break;
                    case R.id.instagram: url = "https://redirection.lima-city.de/links/instagram.html";
                        break;
                    case R.id.allahuakbar: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.allahuakbar";
                        break;
                    case R.id.areyousureaboutthat: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.areyousureaboutthat";
                        break;
                    case R.id.bestcryever: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.bestcryever";
                        break;
                    case R.id.boomheadshot: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.boomheadshot";
                        break;
                    case R.id.crackkidyaa: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.crackkidyaa";
                        break;
                    case R.id.damndaniel: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.damndaniel";
                        break;
                    case R.id.deeznutz: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.deeznutz";
                        break;
                    case R.id.dundundun: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.dundundun";
                        break;
                    case R.id.easports: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.easports";
                        break;
                    case R.id.esketit: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.esketit";
                        break;
                    case R.id.fckherrightinthepssy: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.fckherrightinthepssy";
                        break;
                    case R.id.fatality: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.fatality";
                        break;
                    case R.id.freshavocado: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.freshavocado";
                        break;
                    case R.id.ghostbusters: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.ghostbusters";
                        break;
                    case R.id.hagay: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.hagay";
                        break;
                    case R.id.helloadele: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.helloadele";
                        break;
                    case R.id.hellomotherfcker: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.hellomotherfcker";
                        break;
                    case R.id.icantbelieveyouvedonethis: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.icantbelieveyouvedonethis";
                        break;
                    case R.id.iliketurtles: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.iliketurtles";
                        break;
                    case R.id.iloveyoubtch: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.iloveyoubtch";
                        break;
                    case R.id.imafidgetspinner: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.imafidgetspinner";
                        break;
                    case R.id.imgay: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.imgay";
                        break;
                    case R.id.itsamemario: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.itsamemario";
                        break;
                    case R.id.johncena: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.johncena";
                        break;
                    case R.id.mansnothot: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.mansnothot";
                        break;
                    case R.id.mlgairhorn: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.mlgairhorn";
                        break;
                    case R.id.momgetthecamera: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.momgetthecamera";
                        break;
                    case R.id.movebtchgetouttheway: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.movebtchgetouttheway";
                        break;
                    case R.id.mynameisjeff: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.mynameisjeff";
                        break;
                    case R.id.nothisispatrick: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.nothisispatrick";
                        break;
                    case R.id.ohbabyatriple: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.ohbabyatriple";
                        break;
                    case R.id.oke: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.oke";
                        break;
                    case R.id.pandawraa: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.pandawraa";
                        break;
                    case R.id.patricia: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.patricia";
                        break;
                    case R.id.petergriffinlaugh: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.petergriffinlaugh";
                        break;
                    case R.id.pikapikapikatchu: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.pikapikapikatchu";
                        break;
                    case R.id.pinkguystfu: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.pinkguystfu";
                        break;
                    case R.id.shootingstars: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.shootingstars";
                        break;
                    case R.id.smoketheweedeveryday: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.smoketheweedeveryday";
                        break;
                    case R.id.stopitgetsomehelp: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.stopitgetsomehelp";
                        break;
                    case R.id.thetinggoskrra: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.thetinggoskrra";
                        break;
                    case R.id.tomthetrain: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.tomthetrain";
                        break;
                    case R.id.wasted: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.wasted";
                        break;
                    case R.id.wazzap: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.wazzap";
                        break;
                    case R.id.whatisgoingoninsidetheirhead: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.whatisgoingoninsidetheirhead";
                        break;
                    case R.id.whatyouwantsomedck: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.whatyouwantsomedck";
                        break;
                    case R.id.whyyoualwayslying: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.whyyoualwayslying";
                        break;
                    case R.id.wtfboom: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.wtfboom";
                        break;
                    case R.id.youjusthavetosaythatyourefine: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.youjusthavetosaythatyourefine";
                        break;
                    case R.id.youshallnotpass: url = "https://play.google.com/store/apps/details?id=com.aaron.waller.youshallnotpass";
                        break;
                    case R.id.googleplay: url = "https://play.google.com/store/apps/developer?id=PentaButtons";
                        break;
                    case R.id.paypal: url = "https://www.paypal.me/codingempire";
                        break;


                    case R.id.teilen:
                        link=false;
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "✶"+ R.string.app_name +"✶");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "✶Go and check out the \"XXXX\" Button✶\n\n https://play.google.com/store/apps/details?id=com.aaron.waller.XXXX");
                        startActivity(Intent.createChooser(shareIntent,  "Share via"));
                        break;
                    case R.id.email:
                        link=false;
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto","pentabuttons@gmail.com", null));
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n\n\n\n\n[Packagename:com.aaron.waller.button ---Don't delete this information---]");
                        startActivity(Intent.createChooser(emailIntent, "Send E-Mail..."));
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
