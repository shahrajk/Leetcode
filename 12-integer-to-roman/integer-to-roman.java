class Solution {
    public String intToRoman(int num) {
        // Arrays of values and corresponding Roman numerals including subtractive forms
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };

        StringBuilder roman = new StringBuilder();

        // Iterate over each value-symbol pair
        for (int i = 0; i < values.length; i++) {
            // Append symbol while num is greater or equal to the value
            while (num >= values[i]) {
                roman.append(symbols[i]);
                num -= values[i];
            }
        }

        return roman.toString();
    }
}