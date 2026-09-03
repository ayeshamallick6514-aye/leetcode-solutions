class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        for (int i = n - 1; i >= 0; i--) {

            int[] count = new int[26];

            for (int j = 0; j < i; j++) {
                count[target.charAt(j) - 'a']++;
            }

            int[] available = new int[26];

            for (char c : s.toCharArray()) {
                available[c - 'a']++;
            }

            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int idx = target.charAt(j) - 'a';

                if (available[idx] == 0) {
                    possible = false;
                    break;
                }

                available[idx]--;
            }

            if (!possible) {
                continue;
            }

            int targetIndex = target.charAt(i) - 'a';

            for (int c = targetIndex + 1; c < 26; c++) {

                if (available[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    for (int j = 0; j < i; j++) {
                        ans.append(target.charAt(j));
                    }

                    ans.append((char) ('a' + c));

                    available[c]--;

                    for (int k = 0; k < 26; k++) {
                        while (available[k] > 0) {
                            ans.append((char) ('a' + k));
                            available[k]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}