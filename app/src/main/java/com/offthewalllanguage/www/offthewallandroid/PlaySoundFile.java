package com.offthewalllanguage.www.offthewallandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class PlaySoundFile extends Activity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    String filename;
    private GestureDetector mGestureDetector;
    private MediaPlayer mMediaPlayer;
    public static final String TAG = PlaySoundFile.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflate layout for displaying a sound file
        setContentView(R.layout.activity_play_sound_file);

        mGestureDetector = new GestureDetector(this, this);
        mGestureDetector.setOnDoubleTapListener(this);

        Intent callingIntent = getIntent();
        this.filename = callingIntent.getStringExtra(Intent.EXTRA_TEXT);

        ImageView image = (ImageView) findViewById(R.id.descriptive_image);
        switch (this.filename){
            case "F_B_1_1":
                this.mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.f_b_1_1);
                this.mMediaPlayer.start();
                ((TextView) findViewById(R.id.target_lang)).setText("qu'est-ce que tu veux?");
                ((TextView) findViewById(R.id.english)).setText("What do you want?");
                image.setImageResource(R.drawable.f_b_1_1);
                break;
            case "F_B_1_2":
                this.mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.f_b_1_2);
                this.mMediaPlayer.start();
                ((TextView) findViewById(R.id.target_lang)).setText("(de l')eau");
                ((TextView) findViewById(R.id.english)).setText("Water");
                image.setImageResource(R.drawable.f_b_1_2);
                break;
            case "F_B_1_4":
                this.mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.f_b_1_4);
                this.mMediaPlayer.start();
                ((TextView) findViewById(R.id.target_lang)).setText("Maman");
                ((TextView) findViewById(R.id.english)).setText("Mom");
                image.setImageResource(R.drawable.f_b_1_4);
                break;
            case "F_B_1_5":
                this.mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.f_b_1_5);
                this.mMediaPlayer.start();
                ((TextView) findViewById(R.id.target_lang)).setText("Papa");
                ((TextView) findViewById(R.id.english)).setText("Dad");
                image.setImageResource(R.drawable.f_b_1_5);
                break;
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
