package pl.com.cisc.newproject;

import org.bukkit.plugin.java.JavaPlugin;
import pl.com.cisc.newproject.api.API;
import pl.com.cisc.newproject.commands.HelloWorldCommand;
import pl.com.cisc.newproject.listeners.ChatListener;

public final class NewProject extends JavaPlugin {
    public static NewProject plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Setting the plugin variable to this instance of the plugin, so we can access it from other classes
        // This will be useful to for example send message to players from other classes
        plugin = this;

        // Getting the PluginManager
        var pluginManager = this.getServer().getPluginManager();

        // Registering the ChatListener class as an EventListener
        pluginManager.registerEvents(new ChatListener(), this);

        // Registering the HelloWorld command
        this.getCommand("hello").setExecutor(new HelloWorldCommand());

        // Starting the REST API server
        API.start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
