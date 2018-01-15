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
    File directory;

    public static MediaPlayer mp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);


        File storage = Environment.getExternalStorageDirectory();
        directory = new File(storage.getAbsolutePath() +"/"+R.string.foldername+"/");


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

                        savefile();
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().toString() +"/"+R.string.foldername+"/"+ "sound.mp3"));
                        share.setType("audio/mp3");
                        startActivity(Intent.createChooser(share, "Sound teilen über..."));

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




    public void savefile(){
        File file = new File(directory,"sound.mp3");
        // Get the path to the users external storage
        File storage = Environment.getExternalStorageDirectory();
        // Define the directory path to the soundboard apps folder
        // Change my_soundboard to whatever you want as your folder but keep the slash
        // TODO: When changing the path be sure to also modify the path in filepaths.xml (res/xml/filepaths.xml)
        File directory = new File(storage.getAbsolutePath() +"/"+R.string.foldername+"/");
        // Creates the directory if it doesn't exist
        // mkdirs() gives back a boolean. You can use it to do some processes as well but we don't really need it.
        directory.mkdir();





        // Define an InputStream that will read the data from your sound-raw.mp3 file into a buffer
        InputStream in = this.getResources().openRawResource(R.raw.sound);

        try{

            // Log the name of the sound that is being saved
            Log.e("Saving sound ","#############");

            // Define an OutputStream/FileOutputStream that will write the buffer data into the sound.mp3 on the external storage
            OutputStream out = new FileOutputStream(file);
            // Define a buffer of 1kb (you can make it a little bit bigger but 1kb will be adequate)
            byte[] buffer = new byte[1024];

            int len;
            // Write the data to the sound.mp3 file while reading it from the sound-raw.mp3
            // if (int) InputStream.read() returns -1 stream is at the end of file
            while ((len = in.read(buffer, 0, buffer.length)) != -1){
                out.write(buffer, 0 , len);
            }

            // Close both streams
            in.close();
            out.close();



        } catch (IOException e){


            Toast.makeText(getContext(),"Sound failed to Save.s", Toast.LENGTH_SHORT).show();

            // Log error if process failed
            Log.e("Failed to save file: " ,"####################");
        }
    }


}



