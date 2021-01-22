package ua.polina.message_api.command;

/**
 *  * Command message for topic. It must be in the API of the module that processes it
 *  *
 *  * @param <P> payload type
 *
 * @param <P>
 */
public interface Command<P> {
    /**
     * Command payload
     */
    P payload();

    /**
     * Kafka topic name
     */
    String queue();
}
