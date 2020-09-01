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

public class Palindrome implements Comparable<Palindrome> {
    private String mPalindromeWord;

    public Palindrome(String word) {
        mPalindromeWord = word;
    }

    public String getmPalindromeWord() {
        return mPalindromeWord;
    }

/*    This allows Palindrome objects to be compared by their respective mPalindromeWord fields.
    Also, the method implements the compareTo already defined in the String class.*/
    @Override
    public int compareTo(Palindrome other) {
        return this.getmPalindromeWord().compareTo(other.mPalindromeWord);
    }
}
