package com.aaron.waller.buttontemplate.tabs;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
    View layout;
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

                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                        }
                        else{
                            savefile();
                            Intent share = new Intent(Intent.ACTION_SEND);
                            share.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().toString() + "/soundtest/sound.mp3"));
                            share.setType("audio/mp3");
                            startActivity(Intent.createChooser(share, "Sound teilen über..."));
                        }

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

                        requestPermissions();

                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                if(Settings.System.canWrite(getContext())){
                                    buildalertdielog_withpermissions();
                                    savefile();
                                }
                            }else{
                                buildalertdielog_withpermissions();
                                savefile();
                            }
                        }


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



    private void requestPermissions(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            // Check if the permission to write and read the users external storage is not granted
            // You need this permission if you want to share sounds via WhatsApp or the like
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                // You can log this little text if you want to see if this method works in your Android Monitor
                //Log.i(LOG_TAG, "Permission not granted");

                // If the permission is not granted request it
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }

            // Check if the permission to write the users settings is not granted
            // You need this permission to set a sound as ringtone or the like
            if(!Settings.System.canWrite(getContext())){

                // Displays a little bar on the bottom of the activity with an OK button that will open a so called permission management screen
                Snackbar.make(layout, "The app needs access to your settings", Snackbar.LENGTH_INDEFINITE).setAction("OK",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Context context = v.getContext();
                                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                                intent.setData(Uri.parse("package:" + context.getPackageName()));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }).show();
            }

        }


    }
    public void buildalertdielog_withpermissions(){
        AlertDialog.Builder builder;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
        } else{
            builder = new AlertDialog.Builder(getContext(), AlertDialog.THEME_HOLO_LIGHT);

        }

        builder.setItems(new CharSequence[]{"Klingelton", "Nachrichtenton", "Alarmton"}, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){

                // Decide on the users choice which information will be send to a method that handles the settings for all kinds of system audio
                switch (which){

                    // Ringtone
                    case 0:
                        Toast.makeText(getContext(), "Klingelton", Toast.LENGTH_SHORT).show();
                        setTone(1);
                        break;
                    // Notification
                    case 1:
                        Toast.makeText(getContext(), "Nachrichtenton", Toast.LENGTH_SHORT).show();
                        setTone(2);
                        break;
                    // Alarmton
                    case 2:
                        Toast.makeText(getContext(), "Alarmton", Toast.LENGTH_SHORT).show();
                        setTone(3);
                        break;
                }
            }
        });
        builder.create();
        builder.show();
    }

    public void setTone(int action){
        File soundfile=new File(directory, "sound");
        try{

            // Put all informations about the audio into ContentValues
            ContentValues values = new ContentValues();

            // DATA stores the path to the file on disk
            values.put(MediaStore.MediaColumns.DATA, soundfile.getAbsolutePath());
            // TITLE stores... guess what? Right, the title. GENIUS
            values.put(MediaStore.MediaColumns.TITLE, "sound");
            // MIME_TYPE stores the type of the data send via the MediaProvider
            values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/*");

            switch (action){

                // Ringtone
                case 1:
                    values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                    values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                    values.put(MediaStore.Audio.Media.IS_ALARM, false);
                    break;
                // Notification
                case 2:
                    values.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                    values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                    values.put(MediaStore.Audio.Media.IS_ALARM, false);
                    break;
                // Alarm
                case 3:
                    values.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                    values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                    values.put(MediaStore.Audio.Media.IS_ALARM, true);
                    break;
            }

            values.put(MediaStore.Audio.Media.IS_MUSIC, false);

            // Define a link(Uri) to the saved file and modify this link a little bit
            // DATA is set by ContenValues and therefore has to be replaced
            Uri uri = MediaStore.Audio.Media.getContentUriForPath(soundfile.getAbsolutePath());
            getActivity().getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + soundfile.getAbsolutePath() + "\"", null);
            // Fill the Uri with all the information from ContentValues
            Uri finalUri = getActivity().getContentResolver().insert(uri, values);

            // Finally set the audio as one of the system audio types
            switch (action){

                // Ringtone
                case 1:
                    RingtoneManager.setActualDefaultRingtoneUri(getContext(), RingtoneManager.TYPE_RINGTONE, finalUri);
                    break;
                // Notification
                case 2:
                    RingtoneManager.setActualDefaultRingtoneUri(getContext(), RingtoneManager.TYPE_NOTIFICATION, finalUri);
                    break;
                // Alarm
                case 3:
                    RingtoneManager.setActualDefaultRingtoneUri(getContext(), RingtoneManager.TYPE_ALARM, finalUri);
                    break;
            }

        } catch (Exception e){

            // Log error if process failed
            Log.e( "Failed to save: ", "######");
        }
    }


}



