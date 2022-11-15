package cz.vutbr.bmds.cv01;

import java.util.HashMap;

public class MapClass {
    private HashMap<Integer, String> mapa = new HashMap<>();    // mapa, která je z venku skryta

    // TŘÍDNÍ METODY

    /**
     * Vloží dvojici klíč->hodnota do asociativního pole. V případě, že se již daný klíč v poli nachází, je vyvolána výjimka.
     * @param id Vkládaný klíč
     * @param value Vkládaná hodnota
     * @throws ArrayStoreException Vyhodí v případě, že se daný klíč již v poli nachází.
     */
    public void store(int id, String value) throws ArrayStoreException {
        if (mapa.containsKey(id)) {     // Kontrola, zda se jiz dany klic nachazi v poli
            throw new ArrayStoreException("Hodnotu nelze ulozit, protoze hodnota s ID " + id + " je v poli jiz obsazena!");
        }
        else {
            mapa.put(id, value);
        }
    }

    /**
     * Získá hodnotu na základě klíče z asociativního pole. V případě, že se daný klíč v poli nenachází, je vyvolána výjimka.
     * @param id Dotazovaný klíč hodnoty
     * @throws NoSuchFieldException Vyhodí v případě, že se daný klíč v poli nenachází.
     * @return Dotazovaná hodnota
     */
    public String getValue(int id) throws NoSuchFieldException {
        String hodnota = mapa.get(id);
        // Pokud se dany klic v poli nenachazi, metoda get() vraci hodnotu null

        if (hodnota == null) {  // Test na pritomnost null
            throw new NoSuchFieldException("Hodnotu nelze ziskat, protoze hodnota s ID " + id + " se v poli nenachazi!");
        }

        return hodnota;
    }

    /**
     * Smaže hodnotu na základě klíče z asociativního pole. V případě, že se daný klíč v poli nenachází, je vyvolána výjimka.
     * @param id Klíč hodnoty ke smazání
     * @throws NoSuchFieldException Vyhodí v případě, že se daný klíč v poli nenachází.
     */
    public void deleteKey(int id) throws NoSuchFieldException {
        if (mapa.remove(id) == null) {     // Kontrola, zda se jiz dany klic nachazi v poli, pokud ano, prvek je ihned smazan
            throw new NoSuchFieldException("Hodnotu nelze ziskat, protoze hodnota s ID " + id + " se v poli nenachazi!");
        }
    }

    /**
     * Vrátí velikost asociativního pole.
     * @return Velikost pole
     */
    public int getSize() {
        return mapa.size();
    }

    /**
     * Vypíše všechny prvky (dvojice klíč->hodnota) asociativního pole.
     */
    public void print() {
        for (int key: mapa.keySet()) {
            System.out.println("<" + key + ">-><" + mapa.get(key) + ">");
        }
    }
}
