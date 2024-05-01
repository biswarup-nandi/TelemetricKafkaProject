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

        KafkaMessageProducer kafkaMessageProducer = new KafkaMessageProducer(kafka_user, kafka_pwd);
        while(true) {
            // Memory utilization
            String topic = "machine_health";
            String msg = "{\"schame_name\":\"memoryutilization\",\"current_timestamp\":\"" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date()) + "\",\"machine_name\":\"" + MachineName.getMachineName() + "\",\"memory_data_info\":{\"max_heap\":\"" + memoryUtilization.getMaxHeapMemory() + "\",\"used_heap\":\"" + memoryUtilization.getUsedHeapMemory() + " MB\"}}";
            kafkaMessageProducer.produceRecord(topic, msg);
//            log.info("Machine Name: " + MachineName.getMachineName() + " Heap Memory Usage: " + memoryUtilization.getUsedHeapMemory() + " MB / " + memoryUtilization.getMaxHeapMemory() + " MB");
            // Print disk utilization
//            log.info("Machine Name: " + MachineName.getMachineName() + " Disk Usage: " + diskUtilization.getUsedDiskMemory() + " MB / " + diskUtilization.getMaxDiskMemory() + " MB");

            sleep(200);
        }

    }
}
