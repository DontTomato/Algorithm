package Sort;

public class Sort {
    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            insertionSort(arr1);
            comparator(arr2);
            if(!isEqual(arr1,arr2)){
                //打印arr1
                //打印arr2
                succeed = false;
                break;
            }
        }
    }

    public static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    //选择排序
    public static void selectionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) { //i~ N-1上找最小值的小标
                minIndex = arr[j] < arr[minIndex] ? j :minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    //冒泡排序
    public static void bubleSort(int arr[]){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int e = arr.length - 1; e > 0; e--) {//0`e
            for (int i = 0; i < e; i++){
                if(arr[i] > arr[i+1]){
                    swap(arr, i,i+1);
                }
            }
        }
    }

    //插入排序
    public static void insertionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        //0`0有序的
        //0·i
        for (int i = 1; i < arr.length; i++) { //0`1 做到有序 正序
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--){//逆序比较
                swap(arr,j,j+1);
            }
        }
    }

    //用递归寻找数组最大值
    public static int getMax(int[] arr){
        return process(arr, 0, arr.length - 1);
    }
    public static int process(int[] arr, int L, int R){
        if(L ==R){//arr[L....R] 范围上只有一个数，直接返回 base case
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);  //中点
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax,rightMax);
    }

    //merge排序
    public static void mergeSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        Process(arr,0,arr.length - 1);
    }

    public static void Process(int[] arr, int L, int R){
        if(L == R){
            return;
        }
        int mid = L + ((R - L) >> 1);
        Process(arr, L, mid);
        Process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }
    //使用
    public static void merge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M){
            help[i++] = arr[p2++];
        }
        while (p2 <= M){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++){
            arr[L + i] = help[i];
        }
    }

    /*//merge扩展 求小和
    public static int merge2(int[] arr, int L, int m, int r){
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        int res = 0;
        while (p1 <= m && p2 <= r){
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for(i = 0; i < help.length; i++){
            arr[L + i] = help[i];
        }
        return res;
    }*/


    //快排
    public  static void quickSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        quickSort(arr,0,arr.length - 1);
    }
    public  static void quickSort(int[] arr, int L, int R){
        if (L < R){
            swap(arr, L + (int)(Math.random() * (R - L + 1)),R);
            int[] p = partition(arr,L,R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr,p[1]+1,R);
        }
    }

    //这是一个处理arr[1...r]的函数
    //默认以arr[r]做划分， arr[r] -> p   <p   == p    >p
    //返回等于区域（左边界，右边界）,所以返回一个长度为2的数组res，res[0] res[1]
    public static int[] partition(int[] arr, int L, int R){
        int less = L -1;
        int more = R;
        while (L < more){//表示当前数的位置 arr[R] -> 划分值
            if(arr[L] < arr[R]){ //当前值 < 划分值
                swap(arr, --more, L);
            }else if (arr[L] > arr[R]){
                swap(arr,--more,L);
            }else{
                L++;
            }
        }
        swap(arr,more,R);
        return new int[]{less + 1, more};
    }


    //二分查找


    //面试题
    public static void selectOdd(int arr[]){
        for (int i = 0; i < arr.length - 1; i++) {
            int result = arr[i] ^ arr[i + 1];
        }
    }
    public static void selectTwoOdd(int[] arr){
        int eor = 0;
        for(int curNum : arr){
            eor ^= curNum;
        }
        //eor = a^b
        //eor != 0
        //eor 必然有一个位置上是1
        int rightOne = eor & (~eor + 1);//提取出最右的1
        int onlyOne = 0; //eor'
        for(int cur : arr){
            if((cur & rightOne) == 0){
                onlyOne ^= cur;
            }
        }
        System.out.println(onlyOne + "" + (eor ^ onlyOne));
    }
}
