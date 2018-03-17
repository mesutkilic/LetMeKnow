package com.vsahin.letmeknow.AddEvent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsahin.letmeknow.R;

import butterknife.ButterKnife;

/**
 * Created by volkansahin on 17.03.2018.
 */

public class AddEvent extends Fragment {

    public static AddEvent newInstance() {
        return new AddEvent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
