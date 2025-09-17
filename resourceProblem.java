import java.util.*;

class Result {
    int sum, start, end;
    Result(int sum, int start, int end) {
        this.sum = sum;
        this.start = start;
        this.end = end;
    }
}

class MaxSubarrayDivideConquer {

    public Result maxCrossingSum(int arr[], int l, int m, int h, int constraint) {
        int sum = 0, left_sum = 0, maxLeft = m;
        for (int i = m; i >= l; i--) {
            sum += arr[i];
            if (sum <= constraint && sum > left_sum) {
                left_sum = sum;
                maxLeft = i;
            }
        }

        sum = 0;
        int right_sum = 0, maxRight = m + 1;
        for (int i = m + 1; i <= h; i++) {
            sum += arr[i];
            if (sum <= constraint && sum > right_sum) {
                right_sum = sum;
                maxRight = i;
            }
        }

        if (left_sum + right_sum <= constraint) {
            return new Result(left_sum + right_sum, maxLeft, maxRight);
        } else if (left_sum >= right_sum) {
            return new Result(left_sum, maxLeft, m);
        } else {
            return new Result(right_sum, m + 1, maxRight);
        }
    }

    public Result maximumSubarraySum(int arr[], int l, int h, int constraint) {
        if (l > h) return new Result(0, -1, -1);

        if (l == h) {
            if (arr[l] <= constraint) return new Result(arr[l], l, l);
            else return new Result(0, -1, -1);
        }

        int m = (l + h) / 2;
        Result leftRes = maximumSubarraySum(arr, l, m, constraint);
        Result rightRes = maximumSubarraySum(arr, m + 1, h, constraint);
        Result crossRes = maxCrossingSum(arr, l, m, h, constraint);

        if (leftRes.sum >= rightRes.sum && leftRes.sum >= crossRes.sum) return leftRes;
        else if (rightRes.sum >= leftRes.sum && rightRes.sum >= crossRes.sum) return rightRes;
        else return crossRes;
    }
}

public class resourceProblem {
    public static void main(String[] args) {
        MaxSubarrayDivideConquer solver = new MaxSubarrayDivideConquer();

        int [] bigTestCase = new int [100000];
        for(int i=0; i<bigTestCase.length;i++){
            bigTestCase[i]= i+1;
        }

        int[][] testcases = {
            {2, 1, 3, 4, 5},
            {2, 2, 2, 2},
            {1, 5, 2, 3},
            {6, 7, 8},
            {1, 2, 3, 2, 1},
            {1, 1, 1, 1, 1},
            {4, 2, 3, 1},
            {},
            {1, 2, 3},
            bigTestCase
        };

        int last_constraint = (int)Math.pow(10, 9);
        int[] constraints = {5, 4, 5, 5, 5, 4, 5, 10, 0, last_constraint};

        for(int i = 0; i < testcases.length; i++) {
            int[] arr = testcases[i];
            if (arr.length == 0) {
                System.out.println("Test " + (i+1) + ": No feasible subarray");
                continue;
            }

            Result res = solver.maximumSubarraySum(arr, 0, arr.length - 1, constraints[i]);

            if (res.sum == 0) {
                System.out.println("Test " + (i+1) + ": No feasible subarray");
            } else {
                System.out.print("Test " + (i+1) + ": Max sum = " + res.sum + ", Subarray = [ ");
                for (int j = res.start; j <= res.end; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println("]");
            }
        }
    }
}

