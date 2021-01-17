public class main {
    public static void main(String[] args) {
        int[] arr = {8, 2, 5, 4, 3};
        ArrayCustom customArr = new ArrayCustom(arr);
        System.out.println("Изначальный массив: " + customArr);
        customArr.add(11);
        System.out.println("После добавления элемента в конец: " + customArr);
        customArr.add(44,3);
        System.out.println("После добавления элемента на 4 позицию: " + customArr);
        System.out.println("Теперь элементов в массиве стало: " + customArr.length());
        customArr.remove(4);
        System.out.println("После удаления элемента на 5 позиции: " + customArr);
        customArr.edit(55, 4);
        System.out.println("После замены элемента на 5 позиции: " + customArr);
        System.out.println("Отсортированный массив: " + new ArrayCustom(customArr.sort()));
        System.out.println("Отсортированный массив в обратном порядке: " + new ArrayCustom(customArr.sortDesc()));
        System.out.println("Но наш исходный массив не менялся: " + customArr);
        System.out.println("Минимальное значение в массиве: " + customArr.minValue() + ", а максимальное: " + customArr.maxValue());
        customArr.fill(4);
        System.out.println("А теперь добьем массив, заполнив четверками: " + customArr);
    }
}
