import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String[] info = {"Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1"};
        System.out.println(getWinner(info));
    }

    public static String getWinner(String[] playersInfo){
        String result = "";
        HashMap<String, Integer> gamers = new HashMap<>();
        Pattern pattern = Pattern.compile("([a-zA-Z]+) ([0-9]+)");
        int maxValue = 0;

        for(String player : playersInfo){
            Matcher matcher = pattern.matcher(player);
            if(matcher.find()){
                String playerName = matcher.group(1);
                int playerVal = Integer.parseInt(matcher.group(2));
                if (gamers.containsKey(playerName)){
                    playerVal += gamers.get(playerName);
                }
                gamers.put(playerName, playerVal);
                //Смотрим победителя по очкам на данный момент
                if(playerVal > maxValue){
                    maxValue = playerVal;
                    result = playerName;
                }
            }
            else {
                System.out.println("Неверно задан игрок "+ player+ ", в подсчете он учитываться не будет");
            }
        }
        return result;
    }
}
