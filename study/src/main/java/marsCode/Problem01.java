package marsCode;

/**
 * @Description 在一个班级中，每位同学都拿到了一张卡片，上面有一个整数。有趣的是，除了一个数字之外，所有的数字都恰好出现了两次。现在需要你帮助班长小C快速找到那个拿了独特数字卡片的同学手上的数字是什么。

要求：

设计一个算法，使其时间复杂度为 O(n)，其中 n 是班级的人数。
尽量减少额外空间的使用，以体现你的算法优化能力。
 * @Author sugar
 * @Date 2025/2/13 4:01 PM
 * @Version 1.0
 */

public class Problem01 {
    public static int solution(int[] cards) {
        int uniqueNumber = 0;
        for (int card : cards) {
            uniqueNumber ^= card;
        }
        return uniqueNumber;
    }

    public static void main(String[] args) {

        System.out.println(solution(new int[] { 1, 1, 2, 2, 3, 3, 4, 5, 5 }) ==4 );
        System.out.println(solution(new int[] { 0, 1, 0, 1, 2 }) ==2 );
    }
}
