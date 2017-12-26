package com.aaron.waller.buttontemplate.tabs;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aaron.waller.buttontemplate.R;

/**
 * Created by Ratan on 7/29/2015.
 */
public class Tab1 extends Fragment {


    ImageView button;

    public static MediaPlayer mp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);





        button = (ImageView)rootView.findViewById(R.id.button);
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

}



