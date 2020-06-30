package org.example;

 import org.apache.kafka.clients.producer.KafkaProducer;
 import org.apache.kafka.clients.producer.ProducerConfig;
 import org.apache.kafka.clients.producer.ProducerRecord;
 import org.apache.kafka.common.serialization.IntegerSerializer;
 import org.apache.kafka.common.serialization.StringSerializer;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import java.util.Properties;

public class HelloProducer {

    private static final Logger logger = LogManager.getLogger(HelloProducer.class);

    public static void main(String[] args) {
        logger.info("Creating Kafka Producer...");

        Properties props = new Properties();

        props.put(ProducerConfig.CLIENT_ID_CONFIG, "HelloProducer");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);

        logger.info("Start sending messages...");
        for (int i = 1; i <= 10; i++) {
            logger.info("====================Simple Massage "+i);
            producer.send(new ProducerRecord<>("Students", i, "Simple Message-" + i));
        }

        logger.info("Finished - Closing Kafka Producer.");
        producer.close();
    }
}