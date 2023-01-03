package files.pe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        while (true) {
            System.out.println("Input file path:");

            String path = null;

            try {
                path = reader.readLine();
                PEFileReader peFileReader = new PEFileReader(path);
                System.out.println(peFileReader.extractPEInfo());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
