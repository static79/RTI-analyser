package de.tbitss.analyse.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Lesen von Bytes einer beliebigen Datei
 */
public class ReadBytes {

    public static byte[] readBytes(String filename) throws IOException {
        RandomAccessFile rafile = null;
        try {
            rafile = new RandomAccessFile(filename, "r");
            byte[] b = new byte[(int) rafile.length()];
            rafile.readFully(b);
            return b;
        } finally {
            if (rafile != null) {
                rafile.close();
            }
        }
    }
}
