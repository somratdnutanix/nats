import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import java.nio.charset.StandardCharsets;

public class DurablePublisher {
    public static void main(String[] args) {
        try {



            // Configure NATS connection options to connect to the server on port 4222
            Options options = new Options.Builder()
                    .server("nats://localhost:4222") // Replace "localhost" with your server address if needed
                    .build();

            // Connect to the NATS server using the configured options
            Connection nc = Nats.connect(options);

            // Subject to which messages will be published
            String subject = "subject.one";

            // Message content
            String message = "Hello, I am a NATS Publisher!";

            // Publish the message with durable interest
            nc.publish(subject, message.getBytes(StandardCharsets.UTF_8));

            // Close the NATS connection when you are done
            nc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
