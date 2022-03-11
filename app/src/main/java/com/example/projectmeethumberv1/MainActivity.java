package com.example.projectmeethumberv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

//    CommunitiesFragment communitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
//        NavController navController = Navigation.findNavController(this,  R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    public void visitHumber(View v) {
        String url = "https://www.humber.ca";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        // Note the Chooser below. If no applications match,
        // Android displays a system message.So here there is no need for try-catch.
        startActivity(Intent.createChooser(intent, "Browse with"));

    }

//    public void onClick(View v) {
//        Intent intent = new Intent(MainActivity.this, TopicBoardActivity.class);
//        intent.putExtra("group",(communitiesFragment.getSelectedGroup((TextView)v)));
//        startActivity(intent);
//    }
}