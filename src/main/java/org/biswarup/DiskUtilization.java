package src.main.java.org.biswarup;
import java.io.File;

public class DiskUtilization {
    public double getUsedDiskMemory(){
        File file = new File("/");
        long totalSpace = file.getTotalSpace();
        long freeSpace = file.getFreeSpace();
        long usedSpace = totalSpace - freeSpace;

        return HelperBytesToMB.bytesToMB(usedSpace);
    }
    public double getMaxDiskMemory(){
        File file = new File("/");
        long totalSpace = file.getTotalSpace();

        return HelperBytesToMB.bytesToMB(totalSpace);
    }
}
