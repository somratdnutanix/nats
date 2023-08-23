import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import io.nats.client.Nats;
import io.nats.client.Options;

public class StreamDurablePublisher {
    public static void main(String[] args) {
        try{

            // Configure NATS connection options to connect to the server on port 4222
            Options options = new Options.Builder()
                    .server("nats://localhost:4222") // Replace with your server address if needed
                    .build();

            // Connect to the NATS server using the configured options
            Connection nc = Nats.connect(options);

            // Create a JetStream context
            JetStream js = nc.jetStream();

            // Subject to publish the message to
            String subject = "subject.one";

            // Message content
            byte[] messageBytes = "Hello, JetStream!".getBytes();

            // Publish the message to the JetStream stream "stream-1"
            js.publish(subject, messageBytes);

            // Close the NATS connection
            nc.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
