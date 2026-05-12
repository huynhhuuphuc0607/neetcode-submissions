class Solution {
    public Map<Integer, Integer> shortestPath(int n, List<List<Integer>> edges, int src) {
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        for(List<Integer> edge : edges) {
            graph.computeIfAbsent(edge.get(0), k -> new ArrayList<int[]>()).add(new int[]{edge.get(1), edge.get(2)});
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{src, 0});
        Map<Integer, Integer> answer = new HashMap<>();
        answer.put(src,0);
        while(!q.isEmpty()) {
            int[] sourceInfo = q.poll();
            int source = sourceInfo[0];
            int pathLength = sourceInfo[1];

            for(int[] destinationInfo : graph.getOrDefault(source, new ArrayList<>())) {
                if(pathLength + destinationInfo[1] < answer.getOrDefault(destinationInfo[0], Integer.MAX_VALUE)) {
                    answer.put(destinationInfo[0], pathLength + destinationInfo[1]);
                    q.add(new int[]{destinationInfo[0], pathLength + destinationInfo[1]});
                }
            }
        }

        for(int i = 0; i < n; ++i) {
            if(i != src)
                answer.putIfAbsent(i, -1);
        }

        return answer;
    }  
}
