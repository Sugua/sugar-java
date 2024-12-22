package sort;


/**
 * 冒泡排序
 *
 * 一次比较两个元素，如果顺序错误就进行交换
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array={1,4,3,2,5,0};
        printArray(array);
        sort(array);
        printArray(array);


    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + "\t");
        }
        System.out.println();
    }

    public static void sort(int[] array){

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }

        }
    }
}
