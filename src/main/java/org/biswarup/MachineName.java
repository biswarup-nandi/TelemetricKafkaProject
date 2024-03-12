package src.main.java.org.biswarup;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MachineName {
    public static String getMachineName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "UNKNOWN HOSTNAME";
        }
    }
}
