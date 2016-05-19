package com.offthewalllanguage.www.offthewallandroid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.ArrayList;


public class QRCodeProcessor implements Detector.Processor<Barcode> {

    public static final String TAG = QRCodeProcessor.class.getSimpleName();
    public Context context;
    private String[] fileNames = {"F_B_1_1", "F_B_1_2", "F_B_1_4", "F_B_1_5"};
    private final ArrayList<String> validFilenames = new ArrayList<String>();
    public QRCodeProcessor(Context context){
        super();
        this.context = context;
        for(int i = 0 ; i < fileNames.length; i++)
            validFilenames.add(fileNames[i]);
    }
    @Override
    public void release() {}

    /*
     Starts a new activity and plays the sound file that is detected
     */
    @Override
    public void receiveDetections(Detector.Detections<Barcode> detections) {
        SparseArray<Barcode> items = detections.getDetectedItems();
        //Add all of the QR code data to an arraylist of phrases and check if valid
        ArrayList<String> phrases = new ArrayList();
        for (int i = 0; i < detections.getDetectedItems().size(); i++){
            phrases.add(items.get(items.keyAt(i)).rawValue);
            if (validFilenames.contains(phrases.get(i))){
                Intent play = new Intent(this.context, PlaySoundFile.class);
                play.putExtra(Intent.EXTRA_TEXT, phrases.get(i));
                play.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(play);
            }
        }

    }
}
