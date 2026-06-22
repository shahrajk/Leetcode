class Solution {
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);  // Base index for valid substring calculation

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // Push index of '(' onto stack
                stack.push(i);
            } else {
                // Pop the last '(' index
                stack.pop();

                if (stack.isEmpty()) {
                    // No base for valid substring, push current index
                    stack.push(i);
                } else {
                    // Calculate current valid substring length
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }
}