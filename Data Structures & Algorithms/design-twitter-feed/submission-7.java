class Twitter {

    HashMap<Integer, LinkedList<Tweet>> tweetMap;
    HashMap<Integer, HashSet<Integer>> followMap;
    PriorityQueue<Tweet> feeds;
    int time;
    public Twitter() {
        tweetMap = new HashMap<Integer, LinkedList<Tweet>>();
        followMap = new HashMap<Integer, HashSet<Integer>>();
        feeds = new PriorityQueue<Tweet>((t1, t2) -> t2.time - t1.time);
        time = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new LinkedList<>());
        Tweet recentTweet = tweetMap.get(userId).isEmpty() ? null : tweetMap.get(userId).getFirst();
        tweetMap.get(userId).addFirst(new Tweet(tweetId, time, recentTweet));  

        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
        ++time;
    }
    
    public List<Integer> getNewsFeed(int userId) {
        feeds.clear();
        for(int followeeId : followMap.getOrDefault(userId, new HashSet<>())) {
            if(!tweetMap.getOrDefault(followeeId, new LinkedList<>()).isEmpty()) 
                feeds.offer(tweetMap.get(followeeId).getFirst());
        }

        List<Integer> answer = new ArrayList<>();
        int numPosts = 0;
        while(!feeds.isEmpty() && numPosts < 10) {
            numPosts++;
            Tweet tweet = feeds.poll();
            answer.add(tweet.tweetId);

            if(tweet.next != null)
                feeds.offer(tweet.next);
        }

        return answer;
    }
    
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId)
            return;
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(followerId == followeeId)
            return;
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).remove(followeeId);
    }   

    class Tweet {
        int tweetId;
        int time;
        Tweet next;

        Tweet(int tweetId, int time, Tweet next) {
            this.tweetId = tweetId;
            this.time = time;
            this.next = next;
        }
    }
}
