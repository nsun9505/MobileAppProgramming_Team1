package com.mapteam1.lumpcolletor.lump;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class LumpGenerator {
    private static final LumpGenerator inst = new LumpGenerator();
    private AssetManager am = null;
    private static final String DIR_SPRITE = "lump_parts/spr_";
    private static final String DIR_LUMPDATA = "lump_data.json";

    public String _test_error_msg = "no error";
    JSONArray blueprints;
    private Random random = new Random();

    private LumpGenerator() { }
    public static LumpGenerator getref() { return inst; }

    public void setRes(Resources res) {
        am = res.getAssets() ;

        try {
            JSONObject jsonObject = new JSONObject(getJsonString());
            /*Iterator<String> keyiter = jsonObject.keys();
            _test_error_msg = ".";
            int i = 0;
            while(keyiter.hasNext()) {
                _test_error_msg = _test_error_msg.concat(i+keyiter.next());
                if (++i > 10)
                    break;
            }*/
            blueprints = jsonObject.getJSONArray("blueprints");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Bitmap getPartsBitmap(String key, int index) {
        BufferedInputStream buf;
        Bitmap parts_img = null;
        String parts_type = "";
        String parts_index = "_"+index;
        if (key != null) {
            switch (key) {
                case "BODY":
                    parts_type = "bodytype";
                    break;
                case "EYES":
                    parts_type = "eye";
                    break;
                case "SNOT":
                    parts_type = "snout";
                    break;
                case "EARS":
                    parts_type = "ear";
                    break;
                case "FLEG":
                case "HLEG":
                    parts_type = "leg";
                    break;
                case "TAIL":
                    parts_type = "tail";
                    break;
            }
        }

        try {
            buf = new BufferedInputStream(am.open(DIR_SPRITE +parts_type+parts_index+".png"));
            parts_img = BitmapFactory.decodeStream(buf);
            buf.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return parts_img;
    }
    public int getPartsSize(String key) {
        int parts_size = 0;
        if (key != null) {
            switch (key) {
                case "BODY":
                    parts_size = 128;
                    break;
                case "EYES":
                case "SNOT":
                    parts_size = 16;
                    break;
                case "EARS":
                case "FLEG":
                case "HLEG":
                    parts_size = 32;
                    break;
                case "TAIL":
                    parts_size = 64;
                    break;
            }
        }
        return parts_size;
    }
    public int getPartsRandomIndex(String key) {
        int parts_number = 0;
        if (key != null) {
            switch (key) {
                case "BODY":
                    parts_number = 10;
                    break;
                case "EYES":
                    parts_number = 10;
                    break;
                case "SNOT":
                    parts_number = 25;
                    break;
                case "EARS":
                    parts_number = 25;
                    break;
                case "FLEG":
                case "HLEG":
                    parts_number = 15;
                    break;
                case "TAIL":
                    parts_number = 25;
                    break;
            }
        }
        return random.nextInt(parts_number);
    }
    public int getRandomInt(int max) {
        if (max <= 0) return 0;
        return random.nextInt(max);
    }
    public int getRandomSign() {
        return (random.nextBoolean())?1:-1;
    }
    private int getRotation(int _rotateflag) {
        int _rotate = 180;
        switch(_rotateflag & 26) {
            case 0: _rotate = 0; break;
            case 2: _rotate = 90; break;
            case 8: _rotate = -90; break;
            case 10: _rotate = random.nextBoolean()?90:-90; break;
            case 18: _rotate = random.nextBoolean()?0:90; break;
            case 24: _rotate = random.nextBoolean()?0:-90; break;
            case 26: _rotate = (random.nextInt(3) - 1) * 90; break;
        }
        return _rotate;
    }
    private int getFlip(int _rotateflag) {
        int _flip = 0;
        switch(_rotateflag & 5) {
            case 1: _flip = 1; break;
            case 4: _flip = -1; break;
            case 5: _flip = getRandomSign(); break;
        }
        return _flip;
    }


    public LumpBlueprint MakeBlueprint() {
        if (am == null) return null;
        LumpBlueprint lbp = null;
        try {
            JSONObject mpBlueprint = blueprints.getJSONObject(getRandomInt(blueprints.length()));
            int i, layer_length;
            String parts_key;
            JSONArray parts_info;
            PartsData parts_data, mirror_data;
            int _centerx, _centery, _randomx, _randomy;
            int _hflip, _angle;
            int _rotateflag, _absence_chance;
            int _mirror_x, _mirror_y, _mirrorlength;
            double _mirrorangle;

            JSONArray layer = mpBlueprint.getJSONArray("layer");
            layer_length = layer.length();
            lbp = new LumpBlueprint();
            for(i = 0; i < layer_length; i++) {
                parts_key = layer.getString(i);
                parts_data = new PartsData();
                parts_data.key = parts_key;
                parts_data.size = getPartsSize(parts_key);
                if (parts_key.equals("BODY")) {
                    parts_data.index = mpBlueprint.getInt(parts_key);
                    parts_data.xpos = 0;
                    parts_data.ypos = 0;
                    parts_data.angle = 0;
                    parts_data.hflip = 1;
                } else {
                    Log.d("???", parts_key);
                    parts_info = mpBlueprint.getJSONArray(parts_key);
                    _centerx = parts_info.getInt(0);
                    _centery = parts_info.getInt(1);
                    _randomx = parts_info.getInt(2);
                    _randomy = parts_info.getInt(3);
                    _rotateflag = parts_info.getInt(4);
                    _absence_chance = parts_info.getInt(5);

                    _angle = getRotation(_rotateflag);
                    _hflip = getFlip(_rotateflag);
                    _randomx = getRandomSign() * getRandomInt(_randomx);
                    _randomy = getRandomSign() * getRandomInt(_randomy);

                    parts_data.index = getPartsRandomIndex(parts_key);
                    parts_data.xpos = _centerx + _randomx;
                    parts_data.ypos = _centery + _randomy;
                    parts_data.angle = _angle;
                    parts_data.hflip = _hflip;
                    if (_absence_chance > getRandomInt(100))
                        continue;
                    if (parts_info.length() >= 8) {
                        mirror_data = new PartsData(parts_data);
                        switch(parts_key) {
                            case "EYES":
                                mirror_data.hflip *= -1;
                                break;
                            default:
                                break;
                        }
                        _mirrorangle = Math.toRadians(parts_info.getInt(6));
                        _mirrorlength = parts_info.getInt(7);
                        _mirror_x = (int)Math.cos(_mirrorangle);
                        _mirror_y = (int)Math.sin(_mirrorangle);
                        mirror_data.xpos = _centerx + _mirror_x * _mirrorlength + _randomx;
                        mirror_data.ypos = _centery + _mirror_y * _mirrorlength + _randomy;
                        lbp.layer.add(mirror_data);
                    }
                }
                lbp.layer.add(parts_data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lbp;
    }

    private String getJsonString() {
        String json = "";

        try {
            InputStream is = am.open(DIR_LUMPDATA);
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        //_test_error_msg = json;

        return json;
    }
}
