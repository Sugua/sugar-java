package sort;

/**
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * 提供各种订单类型结酬方式，
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 */
public class SelectorSort {
    public static void main(String[] args) {
//        int[] array = {10,1, 4, 3, 2, 5, 0};
//
//        BubbleSort.printArray(array);
//        sort(array);
//想·
//        BubbleSort.printArray(array);

        double t=1;
        System.out.println(++t);

    }
    public static void sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int k = 0;

            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    k = j;
                }

            }
            if (k != 0) {
                array[k] = array[i];
                array[i] = min;
            }


        }

    }
}
