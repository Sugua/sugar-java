package marsCode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description 小R正在计划一次从地点A到地点B的徒步旅行，总路程需要 N 天。为了在旅途中保持充足的能量，小R每天必须消耗1份食物。幸运的是，小R在路途中每天都会经过一个补给站，可以先购买完食物后再消耗今天的1份食物。然而，每个补给站的食物每份的价格可能不同，并且小R在购买完食物后最多只能同时携带 K 份食物。

现在，小R希望在保证每天食物消耗的前提下，以最小的花费完成这次徒步旅行。你能帮助小R计算出最低的花费是多少吗？

 **输入 **

n 总路程需要的天数
k 小R最多能同时携带食物的份数
data[i] 第i天补给站每份食物的价格
 **输出 **

返回完成这次徒步旅行的最小花费
 **约束条件 **

1 < n,k < 1000
1 < data[i] < 10000
 * @Author sugar
 * @Date 2025/2/13 4:29 PM
 * @Version 1.0
 */
public class Problem02 {

    public static int solution(int n, int k, int[] data) {
        assert n == data.length;
        assert k < n;

        Deque<int[]> mins = new ArrayDeque<>();
        int result = 0;

        for (int j = 0; j < n; j++) {
            //移除比当前价格元素
            while (!mins.isEmpty() && mins.peekLast()[1] > data[j]) {
                mins.pollLast();
            }
            mins.addLast(new int[]{j, data[j]});
            //移除不能携带的元素
            while (mins.peekFirst()[0] <= j - k) {
                mins.pollFirst();
            }
            result += mins.peekFirst()[1];
        }

        return result;
    }

    public static void main(String[] args) {
        // 添加你的测试用例
        System.out.println(solution(5, 2, new int[]{1, 2, 3, 3, 2}) == 9);
        System.out.println(solution(6, 3, new int[]{4, 1, 5, 2, 1, 3}) == 9);
        System.out.println(solution(4, 1, new int[]{3, 2, 4, 1}) == 10);
    }
}
