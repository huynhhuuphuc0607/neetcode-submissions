class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int[][] pairs = new int[position.length][2];
        for(int i = 0; i < position.length; ++i) {
            pairs[i] = new int[]{position[i], speed[i]};
        }

        Arrays.sort(pairs, (p1, p2) -> p2[0] - p1[0]);
        double prevTime = (double)(target - pairs[0][0])/pairs[0][1];
        int answer = 1;
        for(int i = 1; i < position.length; ++i) {
            double currentTime = (double)(target - pairs[i][0])/pairs[i][1];
            if(currentTime > prevTime) {
                answer++;
                prevTime = currentTime;
            }
        }
        return answer;
    }
}
