package com.example.bai6.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.bai6.R;

public class SpinnerAdp extends BaseAdapter {
    private int[]imgs={R.drawable.img,R.drawable.img_2,R.drawable.img_1,R.drawable.img_3};
    private Context context;

    public SpinnerAdp(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item= LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        ImageView img=item.findViewById(R.id.img);
        img.setImageResource(imgs[position]);
        return item;
    }
}
