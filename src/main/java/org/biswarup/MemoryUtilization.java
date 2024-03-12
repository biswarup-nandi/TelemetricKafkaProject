package src.main.java.org.biswarup;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class MemoryUtilization {
    public double getUsedHeapMemory(){
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();

        return HelperBytesToMB.bytesToMB(heapMemoryUsage.getUsed());
    }
    public double getMaxHeapMemory(){
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();

        return HelperBytesToMB.bytesToMB(heapMemoryUsage.getMax());
    }

}
