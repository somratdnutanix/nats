import io.nats.client.*;

import java.time.Duration;

public class StreamConsumer {

    public static void main(String[] args) {
        String server = "nats://localhost:4222";
        String subject = "subject.one"; // Replace with your subject name

        try (Connection nc = Nats.connect(server)) {
            JetStream js = nc.jetStream();

            // Create a subscription to the subject
            JetStreamSubscription sub = js.subscribe(subject);

            // Continuously consume messages and display them on the console
            Message message;
            while (true) {
                message = sub.nextMessage(Duration.ofSeconds(100));
                    System.out.println(new String(message.getData()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
