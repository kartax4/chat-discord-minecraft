package pl.com.cisc.newproject.commands;

import okhttp3.OkHttpClient;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import spark.Service;

public class HelloWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        var message = "Hello, world! (from the command)";

        //Example http request using OkHttp
        var client = new OkHttpClient();
        var request = new okhttp3.Request.Builder()
                .url("http://localhost:" + Service.SPARK_DEFAULT_PORT + "/hello")
                .get()
                .build();

        try {
            var response = client.newCall(request).execute();
            message = response.body().string();
        } catch (Exception ignored) {
           // Ignoring the exception
        }

        sender.sendMessage(message);
        return true;
    }
}
