package com.aaron.waller.buttontemplate.tabs;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aaron.waller.buttontemplate.MainActivity;
import com.aaron.waller.buttontemplate.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ratan on 7/29/2015.
 */
public class Tab1 extends Fragment {


    ImageView button, share, set;

    public static MediaPlayer mp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);





        button = (ImageView)rootView.findViewById(R.id.button);
        share = (ImageView) rootView.findViewById(R.id.share);
        set = (ImageView) rootView.findViewById(R.id.set);




        button();
        share();
        set();








        return rootView;
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




    public void button(){
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        cleanUpMediaPlayer();
                        mp = MediaPlayer.create(getActivity(), R.raw.sound);
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



    public void share(){
        share.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        share.setImageResource(R.drawable.sharepressed);



                        break;

                    case MotionEvent.ACTION_UP:




                        share.setImageResource(R.drawable.share);



                        break;

                }

                return true;
            }
        });
    }




    public void set(){
        set.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        set.setImageResource(R.drawable.setpressed);


                        break;

                    case MotionEvent.ACTION_UP:




                        set.setImageResource(R.drawable.set);

                        break;

                }

                return true;
            }
        });
    }

}



