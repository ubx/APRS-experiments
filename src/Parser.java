/**
 * Created by andreas on 09.07.2014.
 */
public class Parser {

    String pat = "(?<id>.*)>APRS,qAS,(?<src>.*):\\/(?<time>.*)h((?<lat>\\d{4}\\.\\d{2}(N|S))\\/((?<lon>\\d{5}\\.\\d{2}(E|W)))" +
            "'.*\\/A=(?<alt>\\d* )id((\\d{2})(?<fid>[0-9a-fA-F]{6}) )(?<vert>(\\+|\\-)\\d*)fpm (?<rot>(\\+|\\-).*)rot (.*))";


    public static void main(String args[]) {

    }
}
