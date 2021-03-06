/*
Copyright 2020 Emmanuel Nwogu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/


package com.example.palindromes;


import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private  BottomNavigationView.OnNavigationItemSelectedListener mNavListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            int menuItemId = menuItem.getItemId();

            if (menuItemId == R.id.nav_about) {
                selectedFragment = new AboutFragment();
            } else if (menuItemId == R.id.nav_explore) {
                selectedFragment = new ExploreFragment();
            } else if (menuItemId == R.id.nav_check) {
                selectedFragment = new CheckFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , selectedFragment).commit();

            return true;
        }
    };

    private BottomNavigationView.OnNavigationItemReselectedListener mNavItemReselectedListener =
            new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    int menuItemId = menuItem.getItemId();

                    if (menuItemId == R.id.nav_about) {
                        selectedFragment = new AboutFragment();
                    } else if (menuItemId == R.id.nav_explore) {
                        selectedFragment = new ExploreFragment();
                    } else if (menuItemId == R.id.nav_check) {
                        /*  If the check menu is clicked again, this prevents the activity from
                         * reloading a new CheckFragment instance and thus, clearing the EditText.
                         * */
                        return;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                            , selectedFragment).commit();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(mNavListener);
        bottomNav.setOnNavigationItemReselectedListener(mNavItemReselectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                , new ExploreFragment()).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // This is done to maintain menu consistency
        outState.putInt("SelectedItemId", bottomNav.getSelectedItemId());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // This is done to maintain menu consistency
        int selectedItemId = savedInstanceState.getInt("SelectedItemId");
        bottomNav.setSelectedItemId(selectedItemId);
    }
}