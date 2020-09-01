package com.example.palindromes;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CheckFragment extends Fragment{

    // Listener for the check_button Button.
    private View.OnClickListener checkButtonListener = new View.OnClickListener() {

        @Override
        /* When the check_button Button is clicked, the input is checked and the result is displayed
         * based on whether said word is a palindrome or not.
         */
        public void onClick(View view) {
            boolean isPalindrome = isPalindrome(getPalindromeString());
            setPalindromeCheckResult(isPalindrome);
        }
    };

    /* This TextWatcher is used to watch the palindrome_input_edit_text so that the check results
     * and hint can be hidden when the user starts typing.
     */
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Calling this method hides the hint and check result appropriately.
            setResultsAndHintsVisibility(false);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_check, container, false);

        Button checkButton = rootView.findViewById(R.id.check_button);
        checkButton.setOnClickListener(checkButtonListener);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.check_button).setOnClickListener(checkButtonListener);

        final View fragmentCheckRoot = view;
        final EditText palindromeInputEditText = fragmentCheckRoot
                .findViewById(R.id.palindrome_input_edit_text);
        palindromeInputEditText.addTextChangedListener(textWatcher);

        /*   This OnGlobalLayoutListener is used to put the check_button Button in view so the user
         * can click it without closing the keyboard. This is done by always comparing the visible
         * screen size to the actual screen size and is based on the assumption that if more
         * than 25% of the actual screen size is covered up, the obstructing component
         * is probably a keyboard.
         */
        fragmentCheckRoot.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                // r will be populated with the coordinates visible screen area.
                fragmentCheckRoot.getWindowVisibleDisplayFrame(r);
                int fragmentCheckRootHeight = fragmentCheckRoot.getRootView().getHeight();

                int heightDiff = fragmentCheckRootHeight - r.height();

                /* This FrameLayout was solely created to elongate the child view of the
                 * fragmentScrollView so as to enable smooth scrolling. When the keyboard is down,
                 * said FrameLayout remains a zero-height, zero-width view while becoming a view
                 * wide and long enough to ease scrolling whenever the keyboard comes up.
                 * */
                FrameLayout compensationFrameLayout = fragmentCheckRoot.findViewById(
                        R.id.compensation_frame_layout);

                if (heightDiff > 0.25*fragmentCheckRootHeight) {

                    ScrollView fragmentScrollView = (ScrollView) fragmentCheckRoot;

                    LinearLayout.LayoutParams compensationParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, fragmentCheckRootHeight);
                    compensationFrameLayout.setLayoutParams(compensationParams);

                    fragmentScrollView.scrollTo(0, palindromeInputEditText.getBottom());

                } else {
                    LinearLayout.LayoutParams compensationParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT
                            ,  LinearLayout.LayoutParams.WRAP_CONTENT);
                    compensationFrameLayout.setLayoutParams(compensationParams);
                }
            }
        });
    }

    /*This method converts the text in the palindrome_input_edit_text to a String and returns it.
     * @return      The converted string.
     * */
    private String getPalindromeString() {
        EditText palindromeInput = getView().findViewById(R.id.palindrome_input_edit_text);
        return palindromeInput.getText().toString();
    }

    /* This method returns a String where all whitespaces, apostrophes and dashes in the
     input string have been replaced by an empty string.
     * @param  str   Input String
     * @return      The converted string.
     * */
    private String refineInput(String str) {
        return str.replace("'", "").replace("-", "")
                .replace(" ", "");
    }

    /* This method returns a boolean indicating if the input String is a palindrome or not.
    * @param  str   Input String
    * @return      true if input String is a palindrome else false.
    * */
    private boolean isPalindrome(String str) {
        if (str.isEmpty()) {
            return false;
        }
        str = refineInput(str.toLowerCase());
        int n = str.length();

        for (int i = 0; i < (n / 2); i++) {
            if (str.charAt(i) != str.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /*  This method sets the appropriate result based on the input boolean intended to be the result
     *  of the method isPalindrome.
     * @param   isPalindrome   Intended to be the results of the method isPalindrome.
     * */
    private void setPalindromeCheckResult(boolean isPalindrome) {
        ImageView palindromeCheckResult = getView().findViewById(R.id.palindrome_check_result);
        if (isPalindrome) {
            palindromeCheckResult.setImageResource(R.drawable.ic_baseline_good_sign);
        } else {
            palindromeCheckResult.setImageResource(R.drawable.ic_baseline_bad_sign);
        }
        setResultsAndHintsVisibility(true);
    }

    /*  This method sets the visibility of the palindrome_check_result and enter_another_word_hint
     *  views based on the passed boolean argument - visible.
     * @param   visible   The view in concern are set to visible or invisible if this parameter
     *                    is true or false respectively.
     * */
    private void setResultsAndHintsVisibility(boolean visible) {
        ImageView palindromeCheckResult = getView().findViewById(R.id.palindrome_check_result);
        TextView anotherWordHint = getView().findViewById(R.id.enter_another_word_hint);
        if (visible) {
            palindromeCheckResult.setVisibility(View.VISIBLE);
            anotherWordHint.setVisibility(View.VISIBLE);
        } else {
            palindromeCheckResult.setVisibility(View.INVISIBLE);
            anotherWordHint.setVisibility(View.INVISIBLE);
        }
    }
}
