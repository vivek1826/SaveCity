package com.example.srinivasan.database2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by SRINIVASAN on 4/21/2017.
 */

public class SwipeAdapter extends PagerAdapter {

    private int[] image_resources = { R.drawable.pic8,R.drawable.imag,R.drawable.pic2,R.drawable.logo
            ,R.drawable.pic3, R.drawable.pic4};
    private Context context;
    private LayoutInflater layoutInflater;
    public SwipeAdapter(Context context) {
        this.context=context;
    }
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view == (LinearLayout)object);
    }
    public Object instantiateItem(ViewGroup viewGroup, int position){
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.swipe,viewGroup,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(image_resources[position]);
        viewGroup.addView(view);
        return view;
    }
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((View) object);
    }
}
