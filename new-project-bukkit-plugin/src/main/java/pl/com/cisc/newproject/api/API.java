// Package declaration
package pl.com.cisc.newproject.api;

// Imports
import com.google.gson.Gson;
import pl.com.cisc.newproject.NewProject;
import pl.com.cisc.newproject.api.requests.MessageRequest;
import pl.com.cisc.newproject.api.responses.DefaultResponse;
import spark.Service;

// Static import for Spark Java's methods
import static spark.Service.ignite;
import static spark.Spark.*;

/**
 * API class for starting a REST API server and defining its routes.
 */
public class API {

    /**
     * The Gson object that will be used to parse JSONs
     */
    private static final Gson GSON = new Gson();

    /**
     * Starts the REST API server on a specified port and defines the routes
     */
    public static void start(){
        //define the route for GET /hello
        get("/hello", (req, res) -> {
            //set the Content-Type header to text/plain
            res.type("text/plain");

            //return "Hello, world!" as a response
            //this is not a JSON, so the Content-Type header will be text/plain
            return "Hello, world!";
        });

        //define the route for POST /message
        post("/message", (req, res) -> {
            //message is in body of the request
            String body = req.body();

            //parse the body into a MessageRequest object
            MessageRequest messageRequest;

            //parsing is contained in a try-catch block, because the body might not be a valid JSON and parsing would throw an exception
            //which would result in a 500 Internal Server Error response
            try {
                messageRequest = GSON.fromJson(body, MessageRequest.class);
            } catch (Exception e) {
                //if the body is not a valid JSON, return 400 Bad Request
                res.status(400);
                return "Invalid request body";
            }

            //if the message request is null, return 400 Bad Request
            //message request is null if the body is an empty string
            if (messageRequest == null) {
                res.status(400);
                return "Invalid request body";
            }

            //if the username is empty, return 400 Bad Request
            if (messageRequest.getUsername() == null || messageRequest.getUsername().isEmpty()) {
                res.status(400);
                return "Username cannot be empty";
            }

            //if the message is empty, return 400 Bad Request
            if (messageRequest.getMessage() == null || messageRequest.getMessage().isEmpty()) {
                res.status(400);
                return "Message cannot be empty";
            }

            //process the message request asynchronously in a new thread
            //creating new thread will not block the main thread, so the server can continue handling the rest of request
            //and the REST client will receive a response quicker
            new Thread(() -> processMessageRequest(messageRequest)).start();

            //if everything is ok, return 200 OK with message "{\"message\":\"Message received\"}"
            res.status(200);

            //create a DefaultResponse object with message "Message received" using Logbook's builder defined in DefaultResponse class
            //with @Builder annotation
            var response = DefaultResponse
                    .builder()
                    .message("Message received")
                    .build();

            //convert the DefaultResponse object to JSON string
            String responseJson = GSON.toJson(response);

            //set the Content-Type header to application/json
            res.type("application/json");

            //return the JSON string as a response
            return responseJson;
        });

//          Print a message to the console that the REST API server is starting on the specified port
//          System.out.printf("Starting REST API server on port %d%n", Service.SPARK_DEFAULT_PORT);

        // Starts the REST API server on port 4567
        ignite();
    }

    /**
     * Processes the given message request.
     *
     * @param messageRequest The message request to be processed
     */

    public static void processMessageRequest(MessageRequest messageRequest) {
        // Minecraft server
        var server = NewProject.plugin.getServer();

        // Here your task will be to send the message to the chat in the game
        // Expected message format: (Discord) <username>: message
        String formattedMessage = String.format("(Discord) <%s>: %s", messageRequest.getUsername(), messageRequest.getMessage());

        // Send message
        server.broadcastMessage(formattedMessage);
    }
}
