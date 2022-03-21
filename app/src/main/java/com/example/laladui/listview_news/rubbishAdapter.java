package com.example.laladui.listview_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laladui.R;

import java.util.ArrayList;
import java.util.List;

public class rubbishAdapter extends BaseAdapter {
    private ArrayList<Rubbish> list;
    private Context context;
    public rubbishAdapter(Context context, ArrayList<Rubbish> list){
        this.context=context;
        this.list=list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        Rubbish rubbish=list.get(i);
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.adapt_news,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1=view.findViewById(R.id.title);
            viewHolder.textView2=view.findViewById(R.id.author);
            viewHolder.imageView1=view.findViewById(R.id.image1);
            viewHolder.imageView2=view.findViewById(R.id.image2);
            viewHolder.imageView3=view.findViewById(R.id.image3);
            view.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.textView1.setText(rubbish.getTitle());
        viewHolder.textView2.setText(rubbish.getAnthor());
        viewHolder.imageView1.setImageResource(rubbish.getDrawable1());
        viewHolder.imageView2.setImageResource(rubbish.getDrawable2());
        viewHolder.imageView3.setImageResource(rubbish.getDrawable3());


        return view;
    }
    class ViewHolder{
        TextView textView1,textView2;
        ImageView imageView1,imageView2,imageView3;
    }
}
