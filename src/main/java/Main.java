package de.tbitss.rtianalyse;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import de.tbitss.rtianalyse.io.FileReader;
import de.tbitss.rtianalyse.io.StandardOutput;

public class Main {

    /**
     * Wie oft muss ein Modul fuer ein Output mind. "getroffen" worden sein?
     */
    private static int MIN_SAMPLE_MODULE = 200;
    /**
     * Wie oft muss eine Stelle/Offset fuer ein Output mind. "getroffen" worden sein?
     */
    private static int MIN_SAMPLE_OFFSET = 100;


    static {
        try {
            StandardOutput.setSystemOutput("Ausgabe-39465126.txt");
        } catch (Throwable t) {
            // do nothing
        }
    }


    public static void main(String[] args) throws Throwable {

        List<String> lines = readLines();
        List<Modul> module = createModuls(lines);
        module = groupModuls(module);
        ausgabe(module, MIN_SAMPLE_MODULE, MIN_SAMPLE_OFFSET);

    }


    private static List<String> readLines() throws Throwable {
        FileReader fr = new FileReader("39465126-1.TXT");
        List<String> lines = fr.readLinesAsList();
        fr = new FileReader("39465126-2.TXT");
        lines.addAll(fr.readLinesAsList());
        fr = new FileReader("39465126-3.TXT");
        lines.addAll(fr.readLinesAsList());
        fr = new FileReader("39465126-4.TXT");
        lines.addAll(fr.readLinesAsList());
        return lines;
    }


    private static List<Modul> createModuls(List<String> lines) {
        List<Modul> module = new ArrayList<Modul>();
        Modul modul = null;
        for (String line : lines) {
            if (line.startsWith("CSECT=")) {
                StringTokenizer st = new StringTokenizer(line, ",='");
                st.nextToken();
                if (modul != null) {
                    module.add(modul);
                }
                modul = new Modul(st.nextToken());
                st.nextToken();
                st.nextToken();
                modul.setSamples(st.nextToken());
            }
            if (line.startsWith("OFF=X")) {
                StringTokenizer st = new StringTokenizer(line, "'");
                Stelle stelle = new Stelle();
                st.nextToken();
                stelle.offset = st.nextToken();
                st.nextToken();
                stelle.setSample(st.nextToken());
                modul.stellen.add(stelle);
            }
        }
        return module;
    }

    /**
     * Falls mehrere Dateien eingelesen werden, koennen die Samples von Modulen
     * sowie deren Offsets aufaddiert werden
     * @param module
     * @return
     */
    private static List<Modul> groupModuls(List<Modul> module) {
        List<Modul> module2 = new ArrayList<Modul>();
        for (Modul m : module) {
            if (module2.contains(m)) {
                for (Modul m2 : module2) {
                    if (m.equals(m2)) {
                        m2.samples += m.samples;
                        m2.stellen.addAll(m.stellen);
                    }
                }
            } else {
                module2.add(m);
            }
        }
        return module2;
    }


    private static void ausgabe(List<Modul> module, int minSampleModule, int minSampleStelle) {
        for (Modul m : module) {
            if (m.samples < minSampleModule) {
                continue;
            }
            m.sort();
            System.out.println("Modul=" + m.name + ", Samples=" + m.samples);
            for (Stelle s : m.stellen) {
                if (s.sample < minSampleStelle) {
                    continue;
                }
                System.out.println("  Offset=" + s.offset + ", Samples=" + s.sample);
            }
        }
    }

}
