package com.example.naickerbhavesh.retrofitcrud.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.naickerbhavesh.retrofitcrud.R;
import com.example.naickerbhavesh.retrofitcrud.SharedPreferences.SharedPrefManager;
import com.example.naickerbhavesh.retrofitcrud.fragment.HomeFragment;
import com.example.naickerbhavesh.retrofitcrud.fragment.SettingsFragment;
import com.example.naickerbhavesh.retrofitcrud.fragment.UserFragment;
import com.example.naickerbhavesh.retrofitcrud.models.User;

public class Profile extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(Profile.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView = findViewById(R.id.btmNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        displayFragment(new HomeFragment());
    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layRelative, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                fragment = new HomeFragment();
                break;

            case R.id.menu_settings:
                fragment = new SettingsFragment();
                break;

            case R.id.menu_user:
                fragment = new UserFragment();
                break;
        }

        if (fragment!=null){
            displayFragment(fragment);
        }
        return false;
    }


}

