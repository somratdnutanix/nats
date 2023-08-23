import io.nats.client.*;

public class StreamDurableConsumer {

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
                message = sub.nextMessage();
                if (message != null) { // Check if the message is not null
                    System.out.println(new String(message.getData()));
                } else {
                    System.out.println("No message received. Waiting...");
                    Thread.sleep(1000); // Sleep for a second before trying again
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
