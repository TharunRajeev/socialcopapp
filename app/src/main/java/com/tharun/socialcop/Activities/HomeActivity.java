package com.tharun.socialcop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tharun.socialcop.Fragments.HomeFragment;
import com.tharun.socialcop.Fragments.NotificationsFragment;
import com.tharun.socialcop.Fragments.ProfileFragment;
import com.tharun.socialcop.Fragments.SearchFragment;
import com.tharun.socialcop.R;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView homeBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        SharedPreferences sharedPreferences = getSharedPreferences("DEFAULT_PREFERENCES", Context.MODE_PRIVATE);

        if(!sharedPreferences.getBoolean("isLoggedIn",false)){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }



        homeBottomNavigationView  = findViewById(R.id.homebottomnavigationviewid);





        homeBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homemenuid:
                        replaceFragment(new HomeFragment());
                        return true;
                    case R.id.searchmenuid:
                        replaceFragment(new SearchFragment());
                        return true;
                    case R.id.writemenuid:
                        startActivity(new Intent(HomeActivity.this, PostActivity.class));
                        return true;
                    case R.id.notificationmenuid:
                        replaceFragment(new NotificationsFragment());
                        return true;
                    case R.id.accountmenuid:
                        replaceFragment(new ProfileFragment());
                        return true;
                    default:
                        return false;
                }

            }
        });

        homeBottomNavigationView.setSelectedItemId(R.id.homebottomnavigationviewid);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.homefragmentcontainerid,fragment);
        fragmentTransaction.commit();


    }

}
