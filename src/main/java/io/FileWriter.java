package de.tbitss.rtianalyse.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Schreiben einer Text-Datei
 */
public class FileWriter {

    private Writer fw = null;
    private Writer bw = null;

    public FileWriter(String filename) throws IOException {
        initialize(filename);
    }

    public void initialize(String filename) throws IOException {
        this.fw = new java.io.FileWriter(filename);
        this.bw = new BufferedWriter(this.fw);
    }

    public void write(String text) throws IOException {
        this.bw.write(text);
    }

    public void writeln(String text) throws IOException {
        this.bw.write(text + System.lineSeparator());
    }

    public void close() throws IOException {
        try {
            this.fw.flush();
        } finally {
            try {
                this.bw.flush();
            } finally {
                try {
                    this.bw.close();
                } finally {
                    try {
                        this.fw.close();
                    } finally {
                        this.bw = null;
                        this.fw = null;
                    }
                }
            }
        }
    }
}
