import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите текст");
        String text = new Scanner(System.in).nextLine();
        dictonaryParser(text);
    }

    public static void dictonaryParser(String text){
        //Считается только английский алфавит, остальные символы пропускаю
        text = text.replaceAll("[^a-zA-Z]","").toLowerCase();

        TreeMap<Character, Integer> dictonary = new TreeMap<>();
        for(Character symbol = 'a'; symbol <= 'z'; symbol++){
            String regexp = "[^" + symbol + "]";
            String s = text.replaceAll(regexp, "");
            dictonary.put(symbol, s.length());
        }

        for (Map.Entry<Character, Integer> entry : dictonary.entrySet()){
            System.out.println("Буква " + entry.getKey() + " встречается " + entry.getValue() + " раз");
        }
    }
}
