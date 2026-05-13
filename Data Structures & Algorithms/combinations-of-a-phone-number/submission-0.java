class Solution {
    List<String> answer;
    HashMap<Integer, String> hm;
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0)
            return new ArrayList<>();
        hm = new HashMap<>();
        hm.put(2, "abc");
        hm.put(3, "def");
        hm.put(4, "ghi");
        hm.put(5, "jkl");
        hm.put(6, "mno");
        hm.put(7, "pqrs");
        hm.put(8, "tuv");
        hm.put(9, "wxyz");

        answer = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder());

        return answer;
    }

    private void backtrack(String str, int index, StringBuilder builder) {
        if(index >= str.length()) {
            answer.add(builder.toString());
            return;
        }

        String letters = hm.get(str.charAt(index) - '0');
        for(char ch : letters.toCharArray()) {
            builder.append(ch);
            backtrack(str, index + 1, builder);
            builder.setLength(builder.length() - 1);
        }
    }
}
