package com.Angry_Bird.Screen;

import com.Angry_Bird.Birds.Bird_Black;
import com.Angry_Bird.Birds.Bird_Red;
import com.Angry_Bird.Birds.Bird_Yellow;
import com.Angry_Bird.Blocks.Block_Rectangle;
import com.Angry_Bird.BodyData;
import com.Angry_Bird.Buttons.Catapult;
import com.Angry_Bird.Pig.Adult_pig;
import com.Angry_Bird.Pig.Baby_pig;
import com.Angry_Bird.Pig.King_pig;
import com.Angry_Bird.Pig.Pig;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class SaveState implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<BodyData> blocks;
    private int catapults;
    private List<BodyData>adultPigs;
    private List<BodyData>kingPigs;
    private List<BodyData> pigs;

    public SaveState(List<BodyData> blocks, int catapults, List<BodyData> adultPigs, List<BodyData> kingPigs, List<BodyData> pigs) {
        this.blocks = blocks;
        this.catapults = catapults;
        this.adultPigs = adultPigs;
        this.kingPigs = kingPigs;
        this.pigs = pigs;
    }

    public List<BodyData> getBlocks() {
        return blocks;
    }

    public int getCatapults() {
        return catapults;
    }

    public List<BodyData> getAdultPigs() {
        return adultPigs;
    }

    public List<BodyData> getKingPigs() {
        return kingPigs;
    }

    public List<BodyData> getPigs() {
        return pigs;
    }
}
