package de.tbitss.analyse.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class StandardOutput {

    @SuppressWarnings("resource")
    public static void setSystemOutput(String file) throws FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream(file)));
    }

    @SuppressWarnings("resource")
    public static void setSystemErrorOutput(String file) throws FileNotFoundException {
        System.setErr(new PrintStream(new FileOutputStream(file)));
    }

}
