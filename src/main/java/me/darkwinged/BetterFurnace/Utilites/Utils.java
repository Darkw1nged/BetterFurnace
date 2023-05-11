package me.darkwinged.BetterFurnace.Utilites;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utils {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> color(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String word : list)
            newList.add(ChatColor.translateAlternateColorCodes('&', word));
        return newList;
    }

    public static String formatAmount(int number) {
        return NumberFormat.getInstance(new Locale("en", "US")).format(number);
    }

    public static String formatAmount(double number) {
        return NumberFormat.getInstance(new Locale("en", "US")).format(number);
    }

    public static String getTime(Player player) {
        long rawTime = player.getWorld().getTime();
        long hours = rawTime / 1000L + 6L;
        long rawMinutes = rawTime % 1000L * 60L / 1000L;
        String clock = "am";
        if (hours >= 12L) {
            hours -= 12L;
            clock = "pm";
        }
        if (hours >= 12L) {
            hours -= 12L;
            clock = "am";
        }
        if (hours == 0L)
            hours = 12L;
        String minutes = "0" + rawMinutes;
        minutes = minutes.substring(minutes.length() - 2);
        return hours + ":" + minutes + "&e" + clock;
    }

}
