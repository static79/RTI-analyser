package de.tbitss.rtianalyze.io

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lesen einer Text-Datei
 */
public class FileReader {

    private java.io.FileReader fr = null;
    private BufferedReader br = null;

    public FileReader(String filename) throws FileNotFoundException {
        initialize(filename);
    }

    public void initialize(String filename) throws FileNotFoundException {
        this.fr = new java.io.FileReader(filename);
        this.br = new BufferedReader(this.fr);
    }

    public String readLine() throws IOException {
        return this.br.readLine();
    }

    public List<String> readLinesAsList() throws IOException {
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = this.br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    public void close() throws IOException {
        try {
            this.br.close();
        } finally {
            try {
                this.fr.close();
            } finally {
                this.br = null;
                this.fr = null;
            }
        }
    }
}
