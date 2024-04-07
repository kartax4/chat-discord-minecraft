package pl.com.cisc.newproject.api.requests;

import lombok.*;

@Getter // Lombok annotation to generate getters like public String getMessage() { return message; }
@Setter // Lombok annotation to generate setters like public void setMessage(String message) { this.message = message; }
@NoArgsConstructor // Lombok's annotation to generate a no-args constructor like public MessageRequest() {}
@AllArgsConstructor // Lombok's annotation to generate a constructor with all arguments like public MessageRequest(String message) { this.message = message; }
@Builder // Lombok's annotation to generate a builder like MessageRequest.builder()

public class MessageRequest {
    private String username;
    private String message;
}
