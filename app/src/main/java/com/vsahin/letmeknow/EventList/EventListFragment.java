package com.vsahin.letmeknow.EventList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vsahin.letmeknow.AddEvent.AddEventFragment;
import com.vsahin.letmeknow.Entity.Event;
import com.vsahin.letmeknow.EventDetail.EventDetailFragment;
import com.vsahin.letmeknow.HomeActivity;
import com.vsahin.letmeknow.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by volkansahin on 17.03.2018.
 */

public class EventListFragment extends Fragment implements RecyclerViewItemClickListener {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    EventListAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Event> eventList;

    public static EventListFragment newInstance() {
        return new EventListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        ButterKnife.bind(this, view);

        eventList = new ArrayList<>();
        adapter = new EventListAdapter(getActivity(), eventList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("events");
        subscribeToEvents();
    }

    @OnClick(R.id.add_event)
    public void addEvent(){
        ((HomeActivity)getActivity()).showFragment(AddEventFragment.newInstance());
    }

    @Override
    public void onItemClick(Event event) {
        ((HomeActivity)getActivity()).showFragment(EventDetailFragment.newInstance(event));
    }

    public void subscribeToEvents(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                eventList.clear();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    eventList.add(child.getValue(Event.class));
                }

                Collections.reverse(eventList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
