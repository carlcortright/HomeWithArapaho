package com.offthewalllanguage.www.offthewallandroid.Objects;

import com.offthewalllanguage.www.offthewallandroid.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Carl Cortright
 * Date: 8/8/2016
 *
 * Stores a map of sound files to be easily accessed
 * */
public class SoundFileMap {

    private Map<String, SoundFile> mSoundFileMap;

    /**
     * Constructor
     * */
    public SoundFileMap(){
        populateSoundFiles();
    }

    /**
     * Populates mSoundFileMap with SoundFileObjects
     * */
    private void populateSoundFiles() {
        mSoundFileMap = new HashMap<String, SoundFile>();
        SoundFile soundFileToPut = new SoundFile(1,
                "héeyóu hei-beet-itéyo.o?",
                "What do you want?",
                BorderColor.BLACK,
                R.raw.sa0001);
        mSoundFileMap.put("qa0001", soundFileToPut);

        soundFileToPut = new SoundFile(2,
                "koo-hei-beet-bíí3?",
                "Do you want to eat...?",
                BorderColor.BLACK,
                R.raw.sa0002);
        mSoundFileMap.put("qa0002", soundFileToPut);

        soundFileToPut = new SoundFile(3,
                "koo-hei-beet-bíín-oo?",
                "Do you want to eat...?",
                BorderColor.RED,
                R.raw.sa0003);
        mSoundFileMap.put("qa0003", soundFileToPut);

        soundFileToPut = new SoundFile(4,
                "nihíí",
                "Say...",
                BorderColor.WHITE,
                R.raw.sa0004);
        mSoundFileMap.put("qa0004", soundFileToPut);

        soundFileToPut = new SoundFile(5,
                "benéet-bíí3i-noo",
                "I want to eat...",
                BorderColor.BLACK,
                R.raw.sa0005);
        mSoundFileMap.put("qa0005", soundFileToPut);

        soundFileToPut = new SoundFile(6,
                "benéet-bíín-o'",
                "I want to eat...",
                BorderColor.RED,
                R.raw.sa0006);
        mSoundFileMap.put("qa0006", soundFileToPut);

        soundFileToPut = new SoundFile(7,
                "nii'cóóti-'",
                "It's yummy",
                BorderColor.BLACK,
                R.raw.sa0007);
        mSoundFileMap.put("qa0007", soundFileToPut);

        soundFileToPut = new SoundFile(8,
                "nii'céíhi-t",
                "It's yummy",
                BorderColor.RED,
                R.raw.sa0008);
        mSoundFileMap.put("qa0008", soundFileToPut);

        soundFileToPut = new SoundFile(9,
                "koo-híí3itonííhi'? Hii3ítonííhi'",
                "More? More!",
                BorderColor.WHITE,
                R.raw.sa0009);
        mSoundFileMap.put("qa0009", soundFileToPut);

        soundFileToPut = new SoundFile(10,
                "koo-wóów? Woow",
                "All done? All done!",
                BorderColor.WHITE,
                R.raw.sa0010);
        mSoundFileMap.put("qa0010", soundFileToPut);

        soundFileToPut = new SoundFile(11,
                "có'oc",
                "some bread",
                BorderColor.BLACK,
                R.raw.sa0011);
        mSoundFileMap.put("qa0011", soundFileToPut);

        soundFileToPut = new SoundFile(12,
                "noon",
                "an egg",
                BorderColor.BLACK,
                R.raw.sa0012);
        mSoundFileMap.put("qa0012", soundFileToPut);

        soundFileToPut = new SoundFile(13,
                "híteehíbino'",
                "some strawberries",
                BorderColor.RED,
                R.raw.sa0013);
        mSoundFileMap.put("qa0013", soundFileToPut);

        soundFileToPut = new SoundFile(14,
                "nih'oo3ou-nii'éíhii",
                "some chicken",
                BorderColor.RED,
                R.raw.sa0014);
        mSoundFileMap.put("qa0014", soundFileToPut);

        soundFileToPut = new SoundFile(15,
                "nohkúseic-bii3híít",
                "some cereal",
                BorderColor.RED,
                R.raw.sa0015);
        mSoundFileMap.put("qa0015", soundFileToPut);
    }

    /**
     * Returns a reference to the SoundFileMap
     * */
    public Map<String, SoundFile> getSoundFiles(){
        return this.mSoundFileMap;
    }
}
