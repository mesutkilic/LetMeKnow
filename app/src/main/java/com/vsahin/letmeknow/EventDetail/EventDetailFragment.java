package com.vsahin.letmeknow.EventDetail;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vsahin.letmeknow.Entity.Event;
import com.vsahin.letmeknow.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by volkansahin on 18.03.2018.
 */

public class EventDetailFragment extends Fragment {

    public static EventDetailFragment newInstance(Event event) {

        Bundle args = new Bundle();
        args.putSerializable("event", event);
        EventDetailFragment fragment = new EventDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.image_detail)
    ImageView imageDetail;

    @BindView(R.id.textview_title)
    TextView textViewTitle;

    @BindView(R.id.textview_content)
    TextView textViewContent;


    Event event;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        event = (Event) bundle.getSerializable("event");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this, view);


        textViewTitle.setText(event.getTitle());
        textViewContent.setText(event.getContent());
        int image = 0;

        String group = event.getGroup();

        switch (group){
            case "General":
                image = R.drawable.general;
                break;
            case "Party":
                image = R.drawable.party;
                break;
            case "Announcement":
                image = R.drawable.announcement;
        }

        Picasso.with(getActivity()).load(image).into(imageDetail);

        return view;
    }


}
