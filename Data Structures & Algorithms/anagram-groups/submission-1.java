class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        for(String str : strs) {
            hm.computeIfAbsent(encode(str), k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(hm.values());
    }

    private String encode(String str) {
        int[]counts = new int[26];
        for(char ch : str.toCharArray()) {
            counts[ch - 'a']++;
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 26; ++i)
            builder.append(i + 'a').append('#').append(String.valueOf(counts[i]));

        return builder.toString();
    }
}
