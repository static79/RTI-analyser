package de.tbitss.rtianalyse.analyse;

import java.util.ArrayList;
import java.util.List;

public class Modul {

    public String name;
    public int samples;

    public List<Stelle> stellen = new ArrayList<Stelle>();

    public Modul(String name) {
        this.name = name;
    }

    public void sort() {
        Stelle stelle = new Stelle();
        for (int i=0; i<stellen.size(); i++) {
            for (int j=i; j<stellen.size(); j++) {
                if (stellen.get(i).sample < stellen.get(j).sample) {
                    stelle.offset = stellen.get(i).offset;
                    stelle.sample = stellen.get(i).sample;
                    stellen.get(i).offset = stellen.get(j).offset;
                    stellen.get(i).sample = stellen.get(j).sample;
                    stellen.get(j).offset = stelle.offset;
                    stellen.get(j).sample = stelle.sample;
                }
            }
        }
    }

    void setSamples(String samples) {
        int decimal = 0;
        for (short i=0; i<samples.length(); i++) {
            decimal *= 16;
            char c = samples.charAt(i);
            decimal += c - 48 < 10 ? c - 48 : c - 48 - 7;
        }
        this.samples = decimal;
    }

    void addSamples(String samples) {
        int decimal = 0;
        for (short i=0; i<samples.length(); i++) {
            decimal *= 16;
            char c = samples.charAt(i);
            decimal += c - 48 < 10 ? c - 48 : c - 48 - 7;
        }
        this.samples += decimal;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Modul) {
            Modul m = (Modul) o;
            return m.name.equals(this.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
