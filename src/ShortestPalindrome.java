/**
 * Created by WZP on 2016/5/15.
 */

import java.util.*;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * For example:
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".
 */
public class ShortestPalindrome {

    public String shortestPalindrome(String s) {
        if (s == null || s.equals("")) return "";
        int max_length_start_with0 = 1;

        // 将字符串保存到一个列表中，
        // 并且做间隔，处理奇数长度和偶数长度的字符串
        List<Character> charList = new ArrayList<Character>();
        charList.add(null);
        for (char ch : s.toCharArray()) {
            charList.add(ch);
            charList.add(null);
        }
        int length = charList.size();
        // 记录回文长度的数组
        int[] palindromeLengths = new int[length];
        // right标记当前已经访问到最右侧的位置
        // mid表示访问到最右侧时候的中心位置
        int mid = 0, right = 1;
        // 回文长度最长时候对应的mid, 以及最长回文右跨的长度
        int max_mid = 0, max_length = 1;
        palindromeLengths[0] = 1;
        int l, r;
        for (int i = 1; i < length; i++) {
            // 无法利用前面已得到的信息，需要重新计算
            if (i >= right) {
                l = i;r = i;
            }
            else {
                // i在最远距离之内，可以使用部分已经计算好的信息
                // 找到i关于mid对称的点j
                int j = 2*mid-i;
                // 找到right关于mid对称的点left
                int left = 2*mid-right;
                // 以j为中心的最大回文字串左侧的位置
                int j_l = j - palindromeLengths[j]+1;
                if (j_l > left) {
                    palindromeLengths[i] = palindromeLengths[j];
                    continue;
                }
                else {
                    r = right; l = 2*i-r;
                }
            }
            while (l>0&&r<length-1) {
                Character ch1 = charList.get(l-1);
                Character ch2 = charList.get(r+1);
                // 判断脸变字符串是否相等
                if ((ch1 == null && ch2 == null) ||
                        (ch1!=null&&ch2!=null&&ch1.equals(ch2))) {
                    l--;
                    r++;
                }
                else {
                    break;
                }
            }
            // 最右侧赋值，中间值赋值
            right = r;
            mid = i;
            palindromeLengths[i] = r-i+1;
            if (palindromeLengths[i] > max_length) {
                max_length = palindromeLengths[i];
                max_mid = i;
            }
            if (i - palindromeLengths[i] <= 0)
                max_length_start_with0 = Math.max(palindromeLengths[i]-1, max_length_start_with0);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= max_length_start_with0; i--) {
            sb.append(s.charAt(i));
        }
        sb.append(s);
        return sb.toString();
    }

}
