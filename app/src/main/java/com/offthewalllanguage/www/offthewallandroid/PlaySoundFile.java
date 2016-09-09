package com.offthewalllanguage.www.offthewallandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.offthewalllanguage.www.offthewallandroid.Objects.SoundFile;
import com.offthewalllanguage.www.offthewallandroid.Objects.SoundFileMap;

import java.io.IOException;
import java.util.Map;


public class PlaySoundFile extends Activity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private GestureDetector mGestureDetector;
    private MediaPlayer mMediaPlayer;
    public static final String TAG = PlaySoundFile.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate layout for displaying a sound file
        setContentView(R.layout.activity_play_sound_file);

        // Set the gesture detector for the tap and double tap
        mGestureDetector = new GestureDetector(this, this);
        mGestureDetector.setOnDoubleTapListener(this);

        // Grab the file from the intent and display the correct image while playing the sound file.
        Intent callingIntent = getIntent();
        String fileName = callingIntent.getStringExtra(Intent.EXTRA_TEXT);
        mMediaPlayer = new MediaPlayer();
        playAudio(fileName);
    }

    /**
     * Plays the requested sound file audio
     * */
    public void playAudio(String filename) {
        // Select the views to be manipulated
        ImageView image = (ImageView) findViewById(R.id.descriptive_image);

        // Check we have that sound file
        SoundFileMap soundFilesObj = new SoundFileMap();
        Map<String, SoundFile> soundFilesMap = soundFilesObj.getSoundFiles();
        if(soundFilesMap.containsKey(filename)){
            // Define the layout
            SoundFile fileToPlay = soundFilesMap.get(filename);
            ((TextView) findViewById(R.id.target_lang)).setText(fileToPlay.getNativeText());
            ((TextView) findViewById(R.id.english)).setText(fileToPlay.getEnglishText());
            View backgroundFrame = findViewById(R.id.border);
            switch (fileToPlay.getBorderColor()){
                case BLACK:
                    backgroundFrame.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.black_overlay));
                    break;
                case RED:
                    backgroundFrame.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.colorPrimary));
                    break;
                case WHITE:
                    backgroundFrame.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.white_background));
                    break;
            }
            // image.setImageResource(R.drawable.f_b_1_1); TODO: Add the image to the activity
            // Play the sound file
            this.mMediaPlayer = MediaPlayer.create(PlaySoundFile.this, fileToPlay.getSoundFileRID());
            this.mMediaPlayer.start();
        } else {
            Intent scanner = new Intent(this, main.class);
            startActivity(scanner);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mGestureDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Intent scanner = new Intent(this, main.class);
        startActivity(scanner);
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        if (mMediaPlayer != null)
            mMediaPlayer.start();
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event) {return true;}

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {return true;}

    @Override
    public void onLongPress(MotionEvent event) {}

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {return true;}

    @Override
    public void onShowPress(MotionEvent event) {}

    @Override
    public boolean onSingleTapUp(MotionEvent event) {return true;}

    @Override
    public boolean onDoubleTap(MotionEvent event) {return true;}

    }

