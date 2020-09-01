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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AboutFragment extends Fragment {
    // URL for the developer's github
    final private static String CHECK_OUT_DEVELOPER_URL = "https://github.com/emmanuel-nwogu";

    // Listener for the Check out the Developer button
    private View.OnClickListener mCheckOutDeveloperListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            // This method is called when the Check out the Developer button is clicked.
            checkOutDeveloper();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_about, container, false);

        Button checkOutDeveloperButton  = rootView.findViewById(R.id.check_out_developer);
        checkOutDeveloperButton.setOnClickListener(mCheckOutDeveloperListener);

        return rootView;
    }

    /* This method directs the user to the developer's Github.
     */
    private void checkOutDeveloper() {
        Uri developerWebPage = Uri.parse(CHECK_OUT_DEVELOPER_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, developerWebPage);

        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            Log.v("AboutFragment.java", "Intent loading...");
            getContext().startActivity(Intent.createChooser(intent
                    , "Check out developer with..."));
        }
    }
}
