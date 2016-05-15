/**
 * Created by WZP on 2016/5/15.
 */

/**
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 * Note: You may not slant the container.
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int n = height == null ? 0 : height.length;
        if (n < 2) return 0;
        int left = 0, right = n-1;
        int max = 0;
        while (left < right) {
            // 水量的高度由短的板决定
            int minHeight = Math.min(height[left], height[right]);
            max = Math.max(minHeight*(right-left), max);
            // 短板是制约水量的关键
            if (height[left]<height[right])
                left++;
            else
                right--;
        }
        return max;
    }

}
