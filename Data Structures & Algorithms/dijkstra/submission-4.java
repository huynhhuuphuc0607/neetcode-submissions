class Solution {
    public Map<Integer, Integer> shortestPath(int n, List<List<Integer>> edges, int src) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(List<Integer> edge : edges) {
            graph.computeIfAbsent(edge.get(0), k -> new ArrayList<>()).add(new int[]{edge.get(1), edge.get(2)});
        }

        Map<Integer, Integer> answer = new HashMap<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((i1,i2) -> i1[1] - i2[1]);
        q.offer(new int[]{src, 0});

        while(!q.isEmpty()) {
            int info[] = q.poll();
            int node = info[0];
            int path = info[1];
            if(answer.containsKey(node))
                continue;
            answer.put(node, path);

            for(int[] destinationInfo : graph.getOrDefault(node, new ArrayList<>())) {
                if(!answer.containsKey(destinationInfo[0]))
                    q.offer(new int[]{destinationInfo[0], path + destinationInfo[1]});
            }
        }

        for(int i = 0; i < n; ++i)
            answer.putIfAbsent(i, -1);
        return answer;
    }  
}
