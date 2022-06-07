package com.example.soundwave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomnavigationview);
        viewPager = findViewById(R.id.viewpager);

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        setUpAdapter(viewPager);
    }
    public void setUpAdapter(ViewPager viewPager)
    {
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new HomeFragment());
        viewPagerAdapter.addFragment(new SearchFragment());
        viewPagerAdapter.addFragment(new ProfileFragment());
        viewPager.setAdapter(viewPagerAdapter);
    }



    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){
                case(R.id.nav_home):
                    viewPager.setCurrentItem(0);
                    return true;

                case(R.id.nav_search):
                    viewPager.setCurrentItem(1);
                    return true;

                case(R.id.nav_profile):
                    viewPager.setCurrentItem(2);
                    return true;

                default:
                    return false;
            }

        }
    };

    public String getUsername() {
        String username;
        return username=getIntent().getStringExtra("username");
    }

    public String getEmail() {
        String email;
        return email=getIntent().getStringExtra("email");
    }
  public String getProfilePicture()
   {
       String profilePicture;
     return profilePicture=getIntent().getStringExtra("profilePhoto");
   }
    public String getAboutMe()
    {
        String aboutMe;
        return aboutMe=getIntent().getStringExtra("aboutme");
    }
    public String getPassword()
    {
        String password;
        return password=getIntent().getStringExtra("password");
    }


}