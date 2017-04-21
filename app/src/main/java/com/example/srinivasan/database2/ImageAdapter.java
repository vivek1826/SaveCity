package com.example.srinivasan.database2;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by SRINIVASAN on 4/18/2017.
 */

public  class ImageAdapter extends BaseAdapter {
    private Context mContext;
    DatabaseHelperTwo db2;
    ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
        db2=new DatabaseHelperTwo(mContext);
        bitmapArray=db2.searchpass();
    }
    public int getCount() {
        return bitmapArray.size() ;
        //return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }

        //v imageView.setImageResource(mThumbIds[position]);
         imageView.setImageBitmap(bitmapArray.get(position));
        return imageView;
    }
    public Integer[] mThumbIds = {
            R.drawable.clean, R.drawable.bmwz,
            R.drawable.calvin, R.drawable.doggy,
            R.drawable.babysmile,R.drawable.cutebunny
    };
}
