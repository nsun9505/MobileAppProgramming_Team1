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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LumpGenerator {
    private static final LumpGenerator inst = new LumpGenerator();
    private AssetManager am = null;
    private static final String DIR_SPRITE = "lump_parts/spr_";
    private static final String DIR_LUMPDATA = "lump_data.json";
    private static final int FORWARD = 8;
    private static final int REVERSE = 16;
    private static final int RMIRROR = 32;
    private static final int RORIGIN = 64;

    public String _test_error_msg = "no error";
    JSONArray blueprints;
    JSONObject parts_size;
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
            parts_size = jsonObject.getJSONObject("parts_size");
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
        switch(_rotateflag & 7) {
            case 1: _rotate = 0; break;
            case 2: _rotate = 90; break;
            case 4: _rotate = -90; break;
            case 3: _rotate = random.nextBoolean()?0:90; break;
            case 5: _rotate = random.nextBoolean()?0:-90; break;
            case 6: _rotate = random.nextBoolean()?90:-90; break;
            case 7: _rotate = (random.nextInt(3) - 1) * 90; break;
        }
        return _rotate;
    }
    private int getFlipFlag(int _rotateflag) {
        int _arrsize = 0;
        int[] _flip_kind = new int[4];
        if ((_rotateflag & FORWARD) != 0) _flip_kind[_arrsize++] = FORWARD;
        if ((_rotateflag & REVERSE) != 0) _flip_kind[_arrsize++] = REVERSE;
        if ((_rotateflag & RMIRROR) != 0) _flip_kind[_arrsize++] = RMIRROR;
        if ((_rotateflag & RORIGIN) != 0) _flip_kind[_arrsize++] = RORIGIN;

        if (_arrsize == 0) return 0;
        return _flip_kind[getRandomInt(_arrsize)];
    }
    private int getFlipOrigin(int flipflag) {
        int _flip_origin = 0;
        switch(flipflag) {
            case FORWARD: case RMIRROR: _flip_origin = 1;  break;
            case REVERSE: case RORIGIN: _flip_origin = -1; break;
        }
        return _flip_origin;
    }
    private int getFlipMirror(int flipflag) {
        int _flip_mirror = 0;
        switch(flipflag) {
            case FORWARD: case RORIGIN: _flip_mirror = 1; break;
            case REVERSE: case RMIRROR: _flip_mirror = -1; break;
        }
        return _flip_mirror;
    }

    public int getPartsRandomIndex(String key, int _angle, int _randomw, int _randomh) {
        int _profit_index = 0;
        int _limit_size = ((_angle % 180) == 0)?_randomw:_randomh;
        try {
            if (key.equals("FLEG") || key.equals("HLEG")) key = "LEGS";
            JSONArray _parts_size_array = parts_size.getJSONArray(key);
            int i, parts_number = _parts_size_array.length();
            int[] _profit_parts_indexes = new int[parts_number];
            for(i = 0; i < parts_number; i++) {
                if (_parts_size_array.getInt(i) <= _limit_size)
                    _profit_parts_indexes[_profit_index++] = i;
            }
            _profit_index = _profit_parts_indexes[getRandomInt(_profit_index)];
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return _profit_index;
    }
    public int getPartsSizeLimit(String key, int index) {
        int _size = 0;
        try {
            if (key.equals("FLEG") || key.equals("HLEG")) key = "LEGS";
            JSONArray _parts_size_array = parts_size.getJSONArray(key);
            _size = _parts_size_array.getInt(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return _size;
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
            int _index, _centerx, _centery, _angle;
            int _randomw, _randomh, _randomx, _randomy;

            int _flip_flag, _flip_origin, _flip_mirror;
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
                    parts_info = mpBlueprint.getJSONArray(parts_key);
                    _centerx = parts_info.getInt(0);
                    _centery = parts_info.getInt(1);
                    _randomw = parts_info.getInt(2);
                    _randomh = parts_info.getInt(3);
                    _rotateflag = parts_info.getInt(4);
                    _absence_chance = parts_info.getInt(5);

                    _angle = getRotation(_rotateflag);
                    _flip_flag = getFlipFlag(_rotateflag);
                    _flip_origin = getFlipOrigin(_flip_flag);

                    _index = getPartsRandomIndex(parts_key, _angle, _randomw, _randomh);
                    if ((_angle % 180) == 0)
                        _randomw -= getPartsSizeLimit(parts_key, _index);
                    else
                        _randomh -= getPartsSizeLimit(parts_key, _index);

                    _randomx = getRandomSign() * getRandomInt(_randomw);
                    _randomy = getRandomSign() * getRandomInt(_randomh);

                    parts_data.index = _index;
                    parts_data.xpos = _centerx + _randomx;
                    parts_data.ypos = _centery + _randomy;
                    parts_data.angle = _angle;
                    parts_data.hflip = _flip_origin;
                    if (_absence_chance > getRandomInt(100))
                        continue;
                    if (parts_info.length() >= 8) {
                        mirror_data = new PartsData(parts_data);
                        _mirrorangle = Math.toRadians(parts_info.getInt(6));
                        _mirrorlength = parts_info.getInt(7);

                        _flip_mirror = getFlipMirror(_flip_flag);

                        _mirror_x = (int)Math.cos(_mirrorangle);
                        _mirror_y = (int)Math.sin(_mirrorangle);

                        _randomx = getRandomSign() * getRandomInt(_randomw);
                        _randomy = getRandomSign() * getRandomInt(_randomh);

                        mirror_data.xpos = _centerx + _mirror_x * _mirrorlength + _randomx;
                        mirror_data.ypos = _centery - _mirror_y * _mirrorlength + _randomy;
                        mirror_data.angle = _angle * _flip_origin * _flip_mirror;
                        mirror_data.hflip = _flip_mirror;
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
