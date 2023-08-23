import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import io.nats.client.Nats;
import io.nats.client.Options;
import io.nats.client.api.PublishAck;

public class StreamDurablePublisherAck {
    public static void main(String[] args) {
        try {
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
            byte[] messageBytes = "This is great!".getBytes();

            // Publish the message to the JetStream stream "stream-1" and get acknowledgment
            PublishAck ack = js.publish(subject, messageBytes);

            if (ack != null && ack.getSeqno() > 0) {
                System.out.println("Success: Message published to stream!");
            } else {
                System.out.println("Error: Failed to publish message to stream.");
            }

            // Close the NATS connection
            nc.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: An exception occurred while publishing the message.");
        }
    }
}
