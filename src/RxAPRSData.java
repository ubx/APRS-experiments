import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by andreas on 07.07.2014.
 */
public class RxAPRSData {

    static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    static {
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }


    static void doHardbeat(DataOutputStream dataOutputStream) throws Exception {

        while (true) {
            Thread.sleep(240000);
            dataOutputStream.writeBytes("# salut...");
            dataOutputStream.flush();
        }
    }


    public static void main(String args[]) throws Exception {
        final Socket socket = new Socket("aprs.glidernet.org", 14580);
        final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                doHardbeat(dataOutputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        dataOutputStream.writeBytes("user Guest pass -1 vers homemade 1 filter r/47.0/7.0/1000 \n");
        dataOutputStream.flush();

        /*
        * todo -- parse "FLRDDDA93>APRS,qAS,EBKH:/110158h5119.50N/00457.42E'226/057/A=003798 id06DDDA93 +178fpm -0.2rot 9.8dB 0e +6.7kHz"
        *         FLARM id NOT known
        * todo -- parse "G-CKFY>APRS,qAS,Orwell:/110155h5211.73N/00008.55W'056/051/A=003565 id06DDB11F -217fpm +4.0rot 9.2dB 1e +3.6kHz gps3x4"
        *         FLARM id known
        */

        final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        reader.lines().forEach((line) -> System.out.println(formatter.format(new Date()) + " " + line));
    }
}

