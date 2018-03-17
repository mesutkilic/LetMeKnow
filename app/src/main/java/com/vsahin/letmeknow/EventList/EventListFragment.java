package com.vsahin.letmeknow.EventList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vsahin.letmeknow.AddEvent.AddEvent;
import com.vsahin.letmeknow.Entity.Event;
import com.vsahin.letmeknow.HomeActivity;
import com.vsahin.letmeknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by volkansahin on 17.03.2018.
 */

public class EventListFragment extends Fragment implements RecyclerViewItemClickListener {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    EventListAdapter adapter;

    public static EventListFragment newInstance() {
        return new EventListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        ButterKnife.bind(this, view);


        Event e1 = new Event();
        e1.setTitle("title1title1title1title1title1title1");
        e1.setContent("content1content1content1content1content1content1content1content1content1");
        e1.setGroup("group1");
        Event e2 = new Event();
        e2.setTitle("title2title2title2title2title2title2");
        e2.setContent("content2content2content2content2content2content2content2content2content2content2");
        e2.setGroup("group2");
        Event e3 = new Event();
        e3.setTitle("title3title3title3title3title3title3");
        e3.setContent("content3content3content3content3content3content3content3content3content3content3");
        e3.setGroup("group3");

        ArrayList<Event> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);

        adapter = new EventListAdapter(getActivity(), list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    @OnClick(R.id.add_event)
    public void addEvent(){
        ((HomeActivity)getActivity()).showFragment(AddEvent.newInstance());
    }

    @Override
    public void onItemClick(Event clickedSpending) {
        Toast.makeText(getActivity(), "Deneme", Toast.LENGTH_SHORT).show();
    }

    public void SubscribeToEvents(){

    }
}
