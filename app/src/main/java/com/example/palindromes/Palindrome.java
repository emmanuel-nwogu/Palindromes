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
