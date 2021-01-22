package ua.polina.message_api.command;
/**
 * Event message for publish-subscribe channel. It must be in the API of the module that produces it
 *
 * @param <P> payload type
 */
public interface Event<P> {
    /** Event payload */
    P payload();

    /** Publish-subscribe channel name */
    String topic();
}
