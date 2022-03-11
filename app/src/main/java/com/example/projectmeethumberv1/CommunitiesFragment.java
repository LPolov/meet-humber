package com.example.projectmeethumberv1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;


public class CommunitiesFragment extends Fragment {

    private static final String COMMUNITY_NAME = "name";
    private static final String GROUPS_ENTRY_NAME = "groups";
    private static final String GROUP_NOT_FOUND_MESSAGE = "Selected group not found";

    private FirebaseListAdapter<Group> adapter;
    private ListView listOfGroups;
    private DatabaseReference databaseReference;
    private ArrayList<HashMap<String, Object>> communities;
    private ArrayList<String> communityNames;
//    private ArrayAdapter<String> adapter;

    public CommunitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_communities, parent, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listOfGroups = view.findViewById(R.id.list_of_groups_communities);
        listOfGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TopicBoardActivity.class);
//                long id = adapter.getItem(i).getId();
                Group selectedGroupFromList = (Group) (listOfGroups.getItemAtPosition(i));
                intent.putExtra("group", selectedGroupFromList);
                startActivity(intent);
//                Log.d("myTag", "This is my message");
            }
        });
        displayAllGroups();
    }


    private void displayAllGroups() {
        Query query = FirebaseDatabase.getInstance().getReference("groups");
        FirebaseListOptions<Group> options = new FirebaseListOptions.Builder<Group>()
                .setLayout(R.layout.group_item)
                .setQuery(query, Group.class)
                .setLifecycleOwner(this)
                .build();

        adapter = new FirebaseListAdapter<Group>(options) {
            @Override
            protected void populateView(View v, Group model, int position) {
                TextView gr_name;
                gr_name = (TextView) v.findViewById(R.id.group_name);
                gr_name.setText(model.getName());
            }
        };
        listOfGroups.setAdapter(adapter);
    }

}