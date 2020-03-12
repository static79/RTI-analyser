package de.tbitss.rtianalyse.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read a plain text file.
 */
public class FileReader {

    private java.io.FileReader fr = null;
    private BufferedReader br = null;
    
	/**
	 * Creates file reader object which can read a file line by line or a hole file at once 
	 * @param filename
	 * @throws FileNotFoundException
	 */
    public FileReader(String filename) throws FileNotFoundException {
        initialize(filename);
    }

    public void initialize(String filename) throws FileNotFoundException {
        this.fr = new java.io.FileReader(filename);
        this.br = new BufferedReader(this.fr);
    }
    
	/**
	 * Returns one line from the opened file. Meant to be used in a loop.  
	 * @return line from file
	 * @throws IOException
	 */
    public String readLine() throws IOException {
        return this.br.readLine();
    }
    
	/**
	 * Returns the hole file line by line as an List. Be careful with extreme large files.
	 * @return all lines from file
	 * @throws IOException
	 */
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
