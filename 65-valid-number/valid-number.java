class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExp = false;
        boolean digitAfterExp = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
                digitAfterExp = true;
            } 
            else if (c == '+' || c == '-') {
                // Sign is valid only at the beginning or just after e/E
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E')
                    return false;
            } 
            else if (c == '.') {
                // Dot cannot appear after exponent or twice
                if (seenDot || seenExp)
                    return false;
                seenDot = true;
            } 
            else if (c == 'e' || c == 'E') {
                // Exponent must appear only once and after a number
                if (seenExp || !seenDigit)
                    return false;
                seenExp = true;
                digitAfterExp = false;
            } 
            else {
                return false;
            }
        }

        return seenDigit && digitAfterExp;
    }
}