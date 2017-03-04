package com.it.zzb.note.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.it.zzb.note.R;
import com.it.zzb.note.model.Note;

import java.util.List;

/**
 * Created by zzb on 2017/2/26.
 */

public class Radapter extends RecyclerView.Adapter<Radapter.ViewHolder> {
    private Context context;
    private List<Note> notes;
    private ViewHolder viewHolder;

    public Radapter(List<Note> noteList) {
        notes = noteList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView content;
        public TextView time;
        public TextView id;

        public ViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.note_id);
            content = (TextView) view.findViewById(R.id.note_content);
            time = (TextView) view.findViewById(R.id.note_time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.selet, parent, false);
        viewHolder = new ViewHolder(view);
        //viewHolder = (ViewHolder) view.getTag();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Note note = notes.get(position);
        viewHolder.id.setText(String.valueOf(note.getId()));
        viewHolder.content.setText(note.getContent());
        viewHolder.time.setText(note.getTime());
        //viewHolder.itemView.setTag(position);
        setUpEvent(holder);
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        boolean onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void setUpEvent(final ViewHolder holder) {

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, pos);
                }
            });
        }
        //长按
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemLongClick(viewHolder.itemView, pos);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void removeAll() {
        notes.clear();
        notifyDataSetChanged();
    }

    //越界异常
    public void removeItem(int position) {

        notes.remove(viewHolder.getAdapterPosition());
        notifyItemRemoved(viewHolder.getAdapterPosition());
        notifyItemRangeChanged(0, notes.size()-1);
        notifyDataSetChanged();
    }


}
