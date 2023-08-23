import io.nats.client.Connection;
import io.nats.client.JetStreamManagement;
import io.nats.client.Nats;
import io.nats.client.api.StorageType;
import io.nats.client.api.StreamConfiguration;

public class CreateStream {
    public static void main(String[] args) {
        try {
            // Connect to the NATS server
            Connection nc = Nats.connect("nats://localhost:4222");

            // Create a JetStreamManagement context
            JetStreamManagement jsm = nc.jetStreamManagement();

            // Build the configuration for the stream
            StreamConfiguration streamConfig = StreamConfiguration.builder()
                    .name("stream-1")
                    .storageType(StorageType.Memory)
                    .subjects("subject.one", "subject.two")
                    .build();

            // Create the stream using the configuration
            jsm.addStream(streamConfig);

            // Close the NATS connection
            nc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
