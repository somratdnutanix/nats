import io.nats.client.*;
import java.nio.charset.StandardCharsets;

public class DurableConsumer {
    public static void main(String[] args) {
        try {

            // Configure NATS connection options to connect to the server on port 4222
            Options options = new Options.Builder()
                    .server("nats://localhost:4222") // Replace "localhost" with your server address if needed
                    .build();

            // Connect to the NATS server using the configured options
            Connection nc = Nats.connect(options);

            // Create a message dispatcher
            Dispatcher dispatcher = nc.createDispatcher((msg) -> {
                System.out.println("Received : " + new String(msg.getData()));
            });

            // Subject to which the consumer will subscribe
            String subject = "subject.one";

            // Subscribes to the subject using the dispatcher
            dispatcher.subscribe(subject);

            // Keep the program running to receive message
            // waits for 1 minute before closing the connection
            Thread.sleep(60000);

            // Close the NATS connection when you are done
            nc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
