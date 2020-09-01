package com.example.palindromes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PalindromeAdapter extends ArrayAdapter<Palindrome> {
    // All definitions will be found at https://www.thefreedictionary.com/.
    private String DICTIONARY_URL = "https://www.thefreedictionary.com/";

    public PalindromeAdapter(@NonNull Activity context, ArrayList<Palindrome> palindromes) {
        super(context, 0, palindromes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.palindrome_list_item, parent, false);
        }

        Palindrome currentPalindrome= getItem(position);

        TextView palindromeTextView = listItemView.findViewById(R.id.palindrome_text_view);

        final String wordPalindrome = currentPalindrome.getmPalindromeWord();
        palindromeTextView.setText(wordPalindrome);

        ImageView palindromeWebSearchIcon = listItemView
                .findViewById(R.id.palindrome_web_search_icon);

        palindromeWebSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wordDefinitionUrl = DICTIONARY_URL + wordPalindrome;
                Uri webpage = Uri.parse(wordDefinitionUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(Intent.createChooser(intent
                            , "Look up definition with..."));
                }
            }
        });

        return listItemView;
    }
}
