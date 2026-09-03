class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int half = n / 2;

        // Frequency of characters
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // More than one odd frequency -> palindrome impossible
        int oddChar = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                if (oddChar != -1) {
                    return "";
                }
                oddChar = i;
            }
        }

        // Only half of each frequency is needed in left half
        for (int i = 0; i < 26; i++) {
            freq[i] /= 2;
        }

        char[] ans = new char[n];

        /*
         * First, try to keep target's left half
         * as it is.
         */
        int pos = 0;

        while (pos < half) {

            int ch = target.charAt(pos) - 'a';

            if (freq[ch] == 0) {
                break;
            }

            ans[pos] = target.charAt(pos);
            freq[ch]--;
            pos++;
        }

        /*
         * If we successfully copied the complete
         * left half, check the palindrome.
         */
        if (pos == half) {

            if (oddChar != -1) {
                ans[half] = (char) ('a' + oddChar);
            }

            for (int i = 0; i < half; i++) {
                ans[n - 1 - i] = ans[i];
            }

            String result = new String(ans);

            if (result.compareTo(target) > 0) {
                return result;
            }
        }

        /*
         * Now we need to make the palindrome
         * slightly greater than target.
         *
         * We try from the current position.
         */
        while (true) {

            if (pos < half) {

                int targetChar = target.charAt(pos) - 'a';

                // Find smallest character > target[pos]
                for (int ch = targetChar + 1; ch < 26; ch++) {

                    if (freq[ch] > 0) {

                        ans[pos] = (char) ('a' + ch);
                        freq[ch]--;

                        // Fill remaining left half
                        int index = pos + 1;

                        for (int c = 0; c < 26; c++) {
                            while (freq[c] > 0) {
                                ans[index++] = (char) ('a' + c);
                                freq[c]--;
                            }
                        }

                        // Middle character
                        if (oddChar != -1) {
                            ans[half] = (char) ('a' + oddChar);
                        }

                        // Create right half
                        for (int i = 0; i < half; i++) {
                            ans[n - 1 - i] = ans[i];
                        }

                        return new String(ans);
                    }
                }
            }

            // Backtrack
            if (pos == 0) {
                return "";
            }

            pos--;

            // Return target[pos] back to available characters
            int ch = target.charAt(pos) - 'a';
            freq[ch]++;
        }
    }
}