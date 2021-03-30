import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "q");
        map.put(2, "q");
        map.put(3, "w");
        printMap(changeMap(map));
    }

    public static <K, V> Map<V, Collection<K>> changeMap(Map<K, V> map) {
        Map<V, Collection<K>> result = new HashMap<>();
        for(Map.Entry<K,V> entry: map.entrySet()){
            Collection<K> curVal = result.get(entry.getValue());
            if(curVal != null) {
                if (!curVal.contains(entry.getKey())) {
                    curVal.add(entry.getKey());
                }
            } else {
                //Можно ли было избежать инициализации тут?
                curVal = new ArrayList<>();
                curVal.add(entry.getKey());
            }
            result.put(entry.getValue(), curVal);
        }

        return result;
    }

    public static <K, V> void printMap(Map<V, Collection<K>> map){
        for (Map.Entry<V, Collection<K>> entry: map.entrySet()){
            System.out.print("Ключ: "+ entry.getKey() + ", значения: ");
            for (K el: entry.getValue()){
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}
