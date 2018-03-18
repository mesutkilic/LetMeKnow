package com.vsahin.letmeknow.AddEvent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vsahin.letmeknow.Entity.Event;
import com.vsahin.letmeknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by volkansahin on 17.03.2018.
 */

public class AddEventFragment extends Fragment {

    public static AddEventFragment newInstance() {
        return new AddEventFragment();
    }

    @BindView(R.id.spinner_event_group)
    Spinner spinnerEventGroup;

    @BindView(R.id.edittext_title)
    EditText editTextTitle;

    @BindView(R.id.edittext_content)
    EditText editTextContent;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        ButterKnife.bind(this, view);
        fillSpinner();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("events");

    }

    @OnClick(R.id.button_save)
    public void saveEvent(){
        String key = myRef.child("events").push().getKey();

        Event event = new Event();
        event.setId(key);
        event.setTitle(editTextTitle.getText().toString());
        event.setContent(editTextContent.getText().toString());

        if(spinnerEventGroup.getSelectedItemPosition() == 0){
            event.setGroup("General");
        } else {
            event.setGroup((String) spinnerEventGroup.getSelectedItem());
        }
        myRef.child(key).setValue(event);
        getActivity().onBackPressed();
    }

    public void fillSpinner(){
        ArrayList<String> groupList = new ArrayList<>();
        groupList.add("Select A Group");
        groupList.add("Party");
        groupList.add("Announcement");

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, groupList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEventGroup.setAdapter(adapter);
    }
}
