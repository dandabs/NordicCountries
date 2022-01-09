package net.danielonline.NordicCountries;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class listener implements Listener {

    public interface Callback {
        void onRetrieve(String result);
    }

    public static void getCountry(InetSocketAddress ip, Callback callback) throws Exception {
        Bukkit.getScheduler().runTaskAsynchronously(main.getInstance(), (Runnable) () -> {

            URL url = null;
            try {
                url = new URL("http://ip-api.com/json/" + ip.getHostName());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedReader stream = null;
            try {
                stream = new BufferedReader(new InputStreamReader(
                        url.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuilder entirePage = new StringBuilder();
            String inputLine = "";
            while (true) {
                try {
                    if (!((inputLine = stream.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                entirePage.append(inputLine);
            }
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(entirePage.toString());
            System.out.println(entirePage.toString().split("\"countryCode\":\"")[1]);
            System.out.println(entirePage.toString().split("\"countryCode\":\"")[1].split("\",")[0]);

            String countrycode = entirePage.toString().split("\"countryCode\":\"")[1].split("\",")[0];

            Bukkit.getScheduler().runTask(main.getInstance(), () -> callback.onRetrieve(countrycode));

        });

    }



    @EventHandler
    public void onEvent(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        try {
            listener.getCountry(p.getAddress(), cc -> {
                //Result is the retrieved websiteContent


            //String cc = getCountry(p.getAddress());

            System.out.println(cc);

            if (cc.equals("FI")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add finland");
            if (cc.equals("SE")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add sweden");
            if (cc.equals("DK")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add denmark");
            if (cc.equals("NO")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add norway");
            if (cc.equals("IS")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add iceland");
            if (cc.equals("FO")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add faroe");
            if (cc.equals("AX")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add aland");

            if (cc.equals("ES")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add spain");
            if (cc.equals("PT")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add portugal");


            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }
}
