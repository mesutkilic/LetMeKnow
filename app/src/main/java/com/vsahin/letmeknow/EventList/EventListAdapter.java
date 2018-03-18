package com.vsahin.letmeknow.EventList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vsahin.letmeknow.Entity.Event;
import com.vsahin.letmeknow.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by volkansahin on 17.03.2018.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Event> events;
    RecyclerViewItemClickListener recyclerViewItemClickListener;

    public EventListAdapter(Context context, List<Event> events, RecyclerViewItemClickListener listener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.events = events;
        this.recyclerViewItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.fragment_event_list_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        Event e = events.get(position);

        viewHolder.getTextviewTitle().setText(e.getTitle());
        viewHolder.getTextViewContent().setText(e.getContent());
        viewHolder.getTextviewGroup().setText(e.getGroup());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.textview_title)
        TextView textviewTitle;

        @BindView(R.id.textview_content)
        TextView textViewContent;

        @BindView(R.id.textview_group)
        TextView textviewGroup;

        TextView getTextviewTitle() {
            return textviewTitle;
        }

        TextView getTextViewContent() {
            return textViewContent;
        }

        TextView getTextviewGroup() {
            return textviewGroup;
        }

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            recyclerViewItemClickListener.onItemClick(events.get(position));
        }
    }
}
