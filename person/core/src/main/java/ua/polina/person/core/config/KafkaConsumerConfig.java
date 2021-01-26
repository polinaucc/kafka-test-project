package ua.polina.person.core.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import ua.polina.person.api.dto.PersonDto;
import ua.polina.person.api.messaging.command.CreatePerson;
import ua.polina.person.api.messaging.event.SuccessfulCreationPersonEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Autowired
    KafkaTemplate<String, SuccessfulCreationPersonEvent> responseDtoKafkaTemplate;
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public ConsumerFactory<String, CreatePerson> personDtoConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "person");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(CreatePerson.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreatePerson> personDtoConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CreatePerson> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(personDtoConsumerFactory());
        factory.setRetryTemplate(retryTemplate());
        factory.setRecoveryCallback(retryContext -> {
            String errorTopic = SuccessfulCreationPersonEvent.TOPIC;
            responseDtoKafkaTemplate.send(errorTopic, new SuccessfulCreationPersonEvent(new PersonDto()));
            return Optional.empty();
        });
        return factory;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(3000);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(2);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }
}

