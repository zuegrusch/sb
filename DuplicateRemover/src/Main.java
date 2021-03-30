import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Qw");
        list.add("5");
        list.add("Qw");
        list.add("1222");
        Collection<String> uniqueList = removeDuplicates(list);
        printCollection(uniqueList);
        System.out.println();

        Set<Character> set = new HashSet<>();
        set.add('q');
        set.add('\u0041');
        set.add('\u0041');
        set.add('d');
        Collection<Character> uniqueSet = removeDuplicates(set);
        printCollection(uniqueSet);
    }

    private static <T> Collection<T> removeDuplicates(Collection<T> collection) {
        /* Хотела использовать какое-то другое(более объемное) решение, только оно в любом случае предполагает приведение
            коллекции к какому-то определенному ее наследнику, чтобы можно было перебирать элементы
         */
        Set<T> set = new HashSet<>(collection);
        return set;
    }

    private static <T> void printCollection(Collection<T> collection){
        for (T element : collection) {
            System.out.println(element);
        }
    }
}


