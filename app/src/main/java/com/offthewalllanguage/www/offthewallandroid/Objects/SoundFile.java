package com.offthewalllanguage.www.offthewallandroid.Objects;

/**
 * Author: Carl Cortright
 * Date: 8/8/2016
 *
 * Storage object that contains a sound file ID, Native text, english text, and resource ID of the
 * sound file itself.
* */
public class SoundFile {
    private int ID;
    private String NativeText;
    private String EnglishText;
    private int soundFileRID;
    private BorderColor borderColor;

    /**
     * Default constructor
     * */
    public SoundFile(int ID, String NativeText, String EnglishText, BorderColor borderColor,int RID){
        this.ID = ID;
        this.NativeText = NativeText;
        this.EnglishText = EnglishText;
        this.soundFileRID = RID;
        this.borderColor = borderColor;
    }

    /**
     * Accessors
     * */
    public int getID(){ return this.ID; }
    public String getNativeText(){ return this.NativeText; }
    public String getEnglishText(){ return this.EnglishText; }
    public int getSoundFileRID(){ return this.soundFileRID; }
    public BorderColor getBorderColor(){ return this.borderColor; }

}
