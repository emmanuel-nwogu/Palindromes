package com.example.palindromes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ExploreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);

        ArrayList<Palindrome> palindromes = new ArrayList<>();
        String[] palindromesStringList = {"aba", "ada", "aga", "ala", "alula", "ana", "anna", "ara"
                , "bib", "bob", "boob", "civic", "dad", "deed", "dud", "eve", "ewe", "eye", "gag"
                , "gig", "hallah", "kayak", "kazak", "kook", "level", "ma'am", "madam", "malayalam"
                , "mam", "mem", "minim", "mom", "mum", "mym", "naan", "nan", "nauruan", "noon"
                , "nun", "ono", "oto", "pap", "peep", "pep", "pip", "poop", "pop", "pup", "race car"
                , "radar", "refer", "rotor", "seles", "siris", "succus", "sus", "tat", "tebet"
                , "tenet", "tet", "tevet", "tibit", "tit", "toot", "tot", "tut-tut", "utu", "waw"
                , "wow", "xanax", "yay"};

        for (String palindromeString: palindromesStringList) {
            Palindrome newPalindrome = new Palindrome(palindromeString);
            palindromes.add(newPalindrome);
        }

        // Maintaining alphabetical order
        Collections.sort(palindromes);

        PalindromeAdapter itemsAdapter = new PalindromeAdapter(getActivity(), palindromes);

        ListView palindromesListView = rootView.findViewById(R.id.fragment_explore_list);
        palindromesListView.setAdapter(itemsAdapter);


        return rootView;
    }
}
