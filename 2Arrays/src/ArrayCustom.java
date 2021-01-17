import java.util.Arrays;

public class ArrayCustom {
    private int[] array;

    public ArrayCustom(int[] array){
        this.array = array;
    }

    @Override
    public String toString(){
        String res = "";
        for(int val: array){
            res+= val + ", ";
        }
        return res.substring(0, res.length()-2);
    }

    public int length(){
        return array.length;
    }

    public void add(int value){
        int[] arr = new int[array.length + 1];
        for(int i = 0; i < array.length; i++){
            arr[i] = array[i];
        }
        arr[array.length] = value;
        array = arr;
    }

    public void add(int value, int index){
        if(!(index < 0 || index > array.length)){
            int[] arr = new int[array.length + 1];
            for(int i = 0; i < arr.length; i++){
                if(i < index) arr[i] = array[i];
                else if(i == index) arr[i] = value;
                else arr[i] = array[i-1];
            }
            array = arr;
        }
    }

    public void remove(int index){
        if(!(index < 0 || index > array.length)) {
            int[] arr = new int[array.length - 1];
            for(int i = 0; i < arr.length; i++){
                if(i < index) arr[i] = array[i];
                else arr[i] = array[i+1];
            }
            array = arr;
        }
    }

    public void edit(int value, int index){
        if(!(index < 0 || index > array.length)) {
            array[index] = value;
        }
    }

    public int[] sort(){
        int[] arr = Arrays.copyOf(array, array.length);
        for(int i = 1; i < arr.length; i++){
            for(int j = i; j > 0; j--){
                int temp = arr[j];
                if(arr[j-1] > temp){
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        return arr;
    }

    public int[] sortDesc(){
        int[] arr = this.sort();
        for(int i = 0; i < arr.length / 2; i++){
            int temp  = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return arr;
    }

    public int maxValue(){
        return this.sort()[array.length-1];
    }

    public int minValue(){
        return this.sort()[0];
    }

    public void fill(int value){
        for(int i = 0; i < array.length; i++){
            array[i] = value;
        }
    }


}
