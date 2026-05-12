class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)
            return false;
        int r = findRow(matrix, target);
        int c = findCol(matrix, target, r);
        return c != -1;
    }

    private int findRow(int[][]matrix, int target) {
        int l = 0;
        int r = matrix.length-1;

        while(l < r) {
            int mid = l + (r - l + 1)/2;
            if(target == matrix[mid][0])
                return mid;
            if(target > matrix[mid][0])
                l = mid;
            else
                r = mid - 1;
        }

        return l;
    }

    private int findCol(int[][]matrix, int target, int row) {
        int l = 0;
        int r = matrix[0].length - 1;

        while(l <= r) {
            int mid = l + (r-l)/2;
            if(target == matrix[row][mid])
                return mid;
            if(target > matrix[row][mid])
                l = mid + 1;
            else
                r = mid - 1;
        }

        return -1;
    }
}
