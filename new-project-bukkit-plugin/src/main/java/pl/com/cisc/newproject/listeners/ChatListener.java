package pl.com.cisc.newproject.listeners;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.com.cisc.newproject.api.requests.MessageRequest;

/**
 * Represents a listener for chat events.
 */
public class ChatListener implements Listener {

    // This method will be called when a player sends a message in the chat
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        var player = event.getPlayer();
        var playerName = player.getName();
        var message = event.getMessage();

        var jsonMessage = new MessageRequest(playerName, message);

        var gson = new Gson();
        var json = gson.toJson(jsonMessage);

        var client = new OkHttpClient();
        var request = new okhttp3.Request.Builder()
                .url("http://192.168.9.177:8000/message")
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .build();

        try {
            client.newCall(request).execute();

        } catch (Exception ignored) {
            // Ignoring the exception
        }

//         Usage example: changing the chat format
//         Setting the chat format to: "[playerName]: message"
//         event.setFormat("[" + playerName + "]" + ": " + message);
//
//         Other example: changing the message
//         Adding "Hello, " to the beginning of the message
//         event.setMessage("Hello, " + message);


    }
}
