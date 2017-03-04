package com.it.zzb.note.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.it.zzb.note.R;
import com.it.zzb.note.model.Note;

import java.util.List;

/**
 * Created by zzb on 2017/2/23.    使用ListView
 */

public class Myadapter extends BaseAdapter {
    private Context context;
    private List<Note> notes;
    private ViewHolder viewHolder;


    public Myadapter(Context context, List<Note> notes){
        this.context=context;
        this.notes =notes;
    }

    public void removeAll(){
        notes.clear();
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        notes.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#b3FFFFFF"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#b3FAFAFA"));
        }

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.selet,null);
            viewHolder = new ViewHolder();
            viewHolder.id =(TextView) convertView.findViewById(R.id.note_id);
            viewHolder.content = (TextView) convertView.findViewById(R.id.note_content);
            viewHolder.time = (TextView) convertView.findViewById(R.id.note_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(notes.get(position).getId());
        viewHolder.content.setText(notes.get(position).getContent());
        viewHolder.time.setText(notes.get(position).getTime());
        return convertView;
    }

     class ViewHolder {
        public TextView content;
        public TextView time;
        public TextView id;

    }

}

