package sort;

/**
 * 插入排序
 * 算法思想：直接插入排序每次从无序表中取出第一个元素，把它插入到有序表的合适为止，是的有序表仍然有序
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {0,1, 4, 3, 2, 5, 10};

        BubbleSort.printArray(array);
        sort(array);

        BubbleSort.printArray(array);
    }

    private static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && temp < array[j]) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;
        }


    }

    public static void sort1(int[] array) {
        //直接从第1个开始，因为第0个就是一个元素默认是有序的
        for (int i = 1; i < array.length; i++) {
            //记录要插入的数据
            int temp = array[i];
            int j = i;
            //从已经排好序的序列最右边的开始比较，找到比其小的数
            while (j > 0 && temp < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            //存在比其小的数直接插入
            if (j != i) {
                array[j] = temp;
            }
        }
    }
}
