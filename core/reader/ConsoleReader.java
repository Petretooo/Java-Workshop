package WorkShop.core.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleReader implements Reader {

    private static final BufferedReader READER;
    static {
        READER = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public String readLine() {
        try {
            return READER.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


}
