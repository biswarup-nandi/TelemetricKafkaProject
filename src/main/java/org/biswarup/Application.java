package src.main.java.org.biswarup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class.getSimpleName());
    public static void main(String[] args) throws InterruptedException {
        MemoryUtilization memoryUtilization = new MemoryUtilization();
        DiskUtilization diskUtilization = new DiskUtilization();
        Map<String, String> env = System.getenv();
        String kafka_user = env.get("KAFKA_BOOTSTRAP_SERVER_USERNAME");
        String kafka_pwd = env.get("KAFKA_BOOTSTRAP_SERVER_PASSWORD");
        String topic = "TelemetricData";
        String key = "";
        String msg = "";

        KafkaMessageProducer kafkaMessageProducer = new KafkaMessageProducer(kafka_user, kafka_pwd);
        while(true) {
            // Memory utilization
            key = "1";
            msg = "{\"schame_name\":\"memory utilization\",\"current_timestamp\":\"" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date()) + "\",\"machine_name\":\"" + MachineName.getMachineName() + "\",\"memory_data_info\":{\"max_heap\":\"" + memoryUtilization.getMaxHeapMemory() + "\",\"used_heap\":\"" + memoryUtilization.getUsedHeapMemory() + " MB\"}}";
            kafkaMessageProducer.produceRecord(topic, key, msg);

            // Print disk utilization
            key = "2";
            msg = "{\"schame_name\":\"disk utilization\",\"current_timestamp\":\"" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date()) + "\",\"machine_name\":\"" + MachineName.getMachineName() + "\",\"memory_data_info\":{\"max_heap\":\"" + diskUtilization.getMaxDiskMemory() + "\",\"used_heap\":\"" + diskUtilization.getUsedDiskMemory() + " MB\"}}";
            kafkaMessageProducer.produceRecord(topic, key, msg);

            sleep(2000);
        }
    }
}
