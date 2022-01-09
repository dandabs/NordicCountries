package net.danielonline.NordicCountries;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    private static main instance;

    @Override
    public void onEnable() {

        instance = this;
        getServer().getPluginManager().registerEvents(new listener(), this);

    }

    public static main getInstance() {
        return instance;
    }

    public static main getPlugin() {
        return main.getPlugin(main.class);
    }

}
