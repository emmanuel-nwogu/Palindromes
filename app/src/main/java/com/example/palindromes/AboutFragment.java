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
