package sort;

public class SelectorSort {
    public static void main(String[] args) {
//        int[] array = {10,1, 4, 3, 2, 5, 0};
//
//        BubbleSort.printArray(array);
//        sort(array);
//
//        BubbleSort.printArray(array);

        double t=1;
        System.out.println(t++);

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
