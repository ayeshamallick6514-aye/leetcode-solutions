import java.util.*;

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

       
        ArrayList<Integer> ones = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }

        
        if (ones.size() < k) {
            return "";
        }

        String answer = "";
        int minLength = Integer.MAX_VALUE;

      
        for (int i = 0; i <= ones.size() - k; i++) {

            int start = ones.get(i);
            int end = ones.get(i + k - 1);

            String current = s.substring(start, end + 1);

           
            if (current.length() < minLength ||
                (current.length() == minLength &&
                 current.compareTo(answer) < 0)) {

                answer = current;
                minLength = current.length();
            }
        }

        return answer;
    }
}