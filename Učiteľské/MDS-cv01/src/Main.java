import cz.vutbr.bmds.cv01.MyClass; // Je naimportován balíček, protože třída Main je ve výchozím balíčku.
import cz.vutbr.bmds.cv01.MapClass;

public class Main {

    public static void main(String[] args) {

        /////////////////////////////
        // Testování třídy MyClass
        /////////////////////////////

        // Jsou vytvořeny dvě instance třídy MyClass, třetí je pouze prázdná (hodnota null) reference
        MyClass prvni = new MyClass();
        MyClass druha = new MyClass();
        MyClass treti = null;

        // V bloku try je spuštěn kód, který může vyvolat výjimku IllegalArgumentException
        try {
            treti = new MyClass(1, 2, 3, 4, 5, 6); // Zde byla "treti" inicializovana az v bloku try.
            // Díky již existující referenci je viditelná v celé metodě main.

            // Přidáme nějaká čísla do polí
            prvni.addInteger(20);
            prvni.addInteger(20);
            prvni.addInteger(40);

            druha.addInteger(68);
            druha.addInteger(1);

            // Výpis
            prvni.print();
            druha.print();
        }
        catch (IllegalArgumentException e) {
            // V případě, že je zachycena výjimka, je vypsána chybová hláška na chybový výstup (STDERR), ukáže se červeně.
            System.err.println("Chyba: " + e.getMessage());
        }

        // Zobrazení počtu vytvořených objetů
        System.out.println("Počet vytvořených objektů: " + MyClass.getCount());

        // Test zda ve třetím objektu existuje číslo 4
        System.out.println("Existuje 4ka: " + treti.integerExists(4));

        // Součet všech prvků seznamu
        System.out.println("Součet prvků prvního seznamu: " +  prvni.sum()); // 80 - dvacítka je skutečně vložena dvakrát (vlastnost ArrayListu)

        // Ukázka vytvoření sjednoceného objektu pomocí statické metody (továrna)
        MyClass united = MyClass.createUnited(prvni, druha);
        united.print(); // výpis sjednoceného objektu

        // Ukázka použití metody toString. K objektu se chovám jako by to byl string
        System.out.println(united);
        String unitedInfo = united.toString(); // případně lze i uložit jako jiný String
        System.out.println("Info ze stringu: " + unitedInfo);

        /////////////////////////////
        // Testování třídy MapClass
        /////////////////////////////

        // Vytvoření instance objektu MapClass
        MapClass mapObject = new MapClass();

        try {
            // Naplníme objekt dvojicemi klíč->hodnota
            mapObject.store(10, "desitka");
            mapObject.store(20, "dvacitka");

            // Zde dojde k vyvolání a zachycení výjimky
            mapObject.store(10, "druha desitka ma smulu");
        }
        catch (ArrayStoreException e) {
            // V případě, že je zachycena výjimka, je vypsána chybová hláška na chybový výstup (STDERR), ukáže se červeně.
            System.err.println("Chyba: " + e.getMessage());
        }

        try {
            // Dotaz na hodnotu s ID 10
            String vysledek = mapObject.getValue(10);
            System.out.println("Hodnota s ID 10: " + vysledek);

            // Dotaz na neexistující hodnotu -> vyvolání a zachycení výjimky
            vysledek = mapObject.getValue(30);
            System.out.println("Hodnota s ID 30: " + vysledek);
        }
        catch (NoSuchFieldException e) {
            // V případě, že je zachycena výjimka, je vypsána chybová hláška na chybový výstup (STDERR), ukáže se červeně.
            System.err.println("Chyba: " + e.getMessage());
        }

        try {
            // Smazani hodnoty s ID 10
            mapObject.deleteKey(10);

            // Smazání na neexistující hodnotu -> vyvolání a zachycení výjimky
            mapObject.deleteKey(30);
        }
        catch (NoSuchFieldException e) {
            // V případě, že je zachycena výjimka, je vypsána chybová hláška na chybový výstup (STDERR), ukáže se červeně.
            System.err.println("Chyba: " + e.getMessage());
        }

        // Test metody getSize()
        System.out.println("Pocet dvojic v poli: " + mapObject.getSize());

        // Test metody print()
        mapObject.print();
    }
}