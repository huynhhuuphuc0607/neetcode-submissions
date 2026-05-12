class TimeMap {
    HashMap<String, ArrayList<Pair<String, Integer>>> hm;
    public TimeMap() {
        hm = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        hm.putIfAbsent(key, new ArrayList<>());
        hm.get(key).add(new Pair<String, Integer>(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if(!hm.containsKey(key))
            return "";

        ArrayList<Pair<String, Integer>> pairs = hm.get(key);
        if(pairs.get(pairs.size()-1).getValue() <= timestamp)
            return pairs.get(pairs.size() - 1).getKey();

        return search(pairs, timestamp);
    }

    private String search(ArrayList<Pair<String, Integer>> pairs, int timestamp) {
        int l = 0;
        int r = pairs.size() - 1;

        int index = -1;
        while(l <= r) {
            int mid = l + (r - l)/2;
            Pair<String, Integer> midPair = pairs.get(mid);
            int midTimestamp = midPair.getValue();

            if(midTimestamp <= timestamp) {
                index = Math.max(index, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return index == -1 ? "" : pairs.get(index).getKey();
    }
}
