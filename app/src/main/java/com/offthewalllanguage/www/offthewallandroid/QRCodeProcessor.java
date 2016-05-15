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
    public QRCodeProcessor(Context context){
        super();
        this.context = context;
    }
    @Override
    public void release() {}

    @Override
    public void receiveDetections(Detector.Detections<Barcode> detections) {
        SparseArray<Barcode> items = detections.getDetectedItems();

        //Add all of the QR code data to an arraylist of phrases and check if valid
        ArrayList<String> phrases = new ArrayList();
        for (int i = 0; i < detections.getDetectedItems().size(); i++){
            phrases.add(items.get(items.keyAt(i)).rawValue);
            Intent play = new Intent(this.context, PlaySoundFile.class);
            play.putExtra(Intent.EXTRA_TEXT, phrases.get(i));
            play.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(play);
        }

    }
}
