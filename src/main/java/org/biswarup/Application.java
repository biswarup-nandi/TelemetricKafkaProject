package src.main.java.org.biswarup;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.io.File;
import java.lang.Thread;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        MemoryUtilization memoryUtilization = new MemoryUtilization();
        DiskUtilization diskUtilization = new DiskUtilization();

        while (true) {
            // Print memory utilization
            System.out.println("Machine Name: " + MachineName.getMachineName() + " Heap Memory Usage: " + memoryUtilization.getUsedHeapMemory() + " MB / " + memoryUtilization.getMaxHeapMemory() + " MB");
            // Print disk utilization
            System.out.println("Machine Name: " + MachineName.getMachineName() + " Disk Usage: " + diskUtilization.getUsedDiskMemory() + " MB / " + diskUtilization.getMaxDiskMemory() + " MB");

            Thread.sleep(2000);
        }
    }
}
