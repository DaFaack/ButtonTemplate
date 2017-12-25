package com.aaron.waller.buttontemplate;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;

public class MainActivity extends AppCompatActivity {

    ImageView button;
    public static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        AdRequest adRequest = new AdRequest.Builder().build();


        button = (ImageView)findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        cleanUpMediaPlayer();
                        mp = MediaPlayer.create(MainActivity.this, R.raw.sound);
                        mp.start();

                        button.setImageResource(R.drawable.button1);


                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {

                                button.setImageResource(R.drawable.button);
                            }
                        });


                        break;

                    case MotionEvent.ACTION_UP:




                                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {

                                        button.setImageResource(R.drawable.button);
                                    }
                                });





                        break;

                }

                return true;
            }
        });
    }


    public static void cleanUpMediaPlayer() {
        if (mp != null) {
            try {
                mp.stop();
                mp.release();
                mp = null;
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }



}
