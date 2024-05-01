package src.main.java.org.biswarup;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaMessageProducer {
    String user = null;
    String pwd = null;

    KafkaMessageProducer(String u, String p){
        this.user = u;
        this.pwd = p;
    }

    //Producer Properties
    private Properties setProperties(){
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "https://thorough-redfish-7112-us1-kafka.upstash.io:9092");
        properties.setProperty("sasl.mechanism", "SCRAM-SHA-256");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"" + this.user + "\" password=\"" + this.pwd + "\";");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

    public void produceRecord(String topic, String msg){
        ProducerRecord<String, String> producerRecord = null;
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(setProperties())) {
            producerRecord = new ProducerRecord<>(topic, msg);
            producer.send(producerRecord);
            producer.flush();
            producer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }




}
