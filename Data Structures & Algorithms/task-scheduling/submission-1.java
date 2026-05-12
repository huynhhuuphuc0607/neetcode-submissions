class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for(char task : tasks) {
            hm.put(task, hm.getOrDefault(task, 0) + 1);
        }

        PriorityQueue<int[]> q = new PriorityQueue<int[]>((i1,i2) -> {
            if(i1[2] != i2[2])
                return i1[2] - i2[2];
            return i2[1] - i1[2];
        });

        for(Map.Entry<Character, Integer> entry : hm.entrySet()) {
            q.offer(new int[]{entry.getKey() - 'A', entry.getValue(), 1});
        }

        int current = 0;
        while(!q.isEmpty()) {
            int tuple[] = q.poll();
            int remaining = tuple[1]-1;
            int cycle = tuple[2];

            current++;
            current = Math.max(current, cycle);
            if(remaining > 0) {
                tuple[1] = remaining;
                tuple[2] = cycle + n + 1;
                q.offer(tuple);
            }
        }

        return current;
    }

}
