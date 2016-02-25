
import org.apache.log4j.Logger;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Initialization.class);

    public static void main(String[] args) {
        Initialization init = new Initialization();
        init.scan();
        log.info("---------Scan map-----------");
        ScanMap scanMap = new ScanMap();
        scanMap.callMap();
    }

}
