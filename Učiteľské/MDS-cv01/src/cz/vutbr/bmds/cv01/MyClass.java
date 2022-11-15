package cz.vutbr.bmds.cv01;

import java.util.ArrayList;

/**
 * Třída MyClass, která implementuje rozhraní ISum.
 * Třída obsahuje seznam prvků Integer a zveřejnuje metody umožnující práci s tímto seznamem.
 */
public class MyClass implements ISum {


    private static int count = 0;	// statická proměnná, ve které je uchováno, kolikrát již byl vytvořen objekt typu MyClass
    // proměnná je privátní - pro přístup z vnější je nutno použít statický getter getCount()
    private ArrayList<Integer> list = new ArrayList<Integer>(); // samotný seznam, který je zvenku skryt

    // KONSTRUKTORY //

    /**
     * Konstruktor třídy. Po jeho zavolání dojde k navýšení počítadla počtu vytvořených objektů.
     */
    public MyClass() {
        count++;
    }

    /**
     * Druhý konstruktor třídy, který umožnuje automaticky vytvořit objekt, ve kterém jsou již předplněny některé prvky.
     * Jde o nekončený seznam parametrů (args), použije se např. jako: new MyClass(1,2,3,4);
     * @throws IllegalArgumentException
     */
    public MyClass(int...numbers) throws IllegalArgumentException {
        this(); // tímto voláním se zavolá konstruktor metody MyClass() - bez parametru.

        for (int i : numbers) {
            addInteger(i);
        }
    }

    // STATICKÉ METODY //

    /**
     * Statická metoda! Vrací počet již vytvořených objektů třídy MyClass.
     * Lze volat například MyClass.getCount()!
     * @return
     */
    public static int getCount() {
        return count;
    }

    /**
     * Staticka metoda! Na základě dvou objektů třídy MyClass vytvoří nový objekt, který bude obsahovat prvky z obou objektů v parametru.
     * Použití např. MyClass united = MyClass.createUnited(prvniObjekt, druhyObjekt);
     * @return MyClass
     */
    public static MyClass createUnited(MyClass prvniObjekt, MyClass druhyObjekt) {
        MyClass newClass = new MyClass(); // Vytvoření prázdného objektu

        // Díky tomu, že pracujeme stále v MyClass, list v objektech (které jsou jinak private), je viditelný a lze s ním pracovat.
        // Vně MyClass by podobný přístup nefungoval, pokud bude list private.
        newClass.list.addAll(prvniObjekt.list); // Vložení prvků ze seznamu z prvního objektu
        newClass.list.addAll(druhyObjekt.list); // Vložení prvků ze seznamu z druhého objektu

        return newClass;

        // POZOR! Objekt nejde zkopírovat takto: MyClass newClass = prvni; předala by se pouze reference na první objekt!

    }

    // TŘÍDNÍ METODY //

    /**
     * Vloží číslo do seznamu. V případě, že je menší než nula, je vyhozena výjimka IllegalArgumentException.
     * @param i	Vkládané číslo
     * @throws IllegalArgumentException	Vyhodí v případě, že je vloženo záporné číslo.
     */
    public void addInteger(int i) throws IllegalArgumentException {
        if (i > 0) {
            list.add(i);
        }
        else {
            // Seznam vestavěných výjimek v javě: https://www.tutorialspoint.com/java/java_builtin_exceptions.htm
            throw new IllegalArgumentException("Nelze vkladat zaporna cisla.");
        }

    }

    /**
     * Zjistí, zda parametr i existuje v seznamu
     * @param i
     * @return Vraci zda existuje.
     */
    public boolean integerExists(int i) {
        return list.contains(i);
    }

    /**
     * Implementuje metodu toString. Při pokusu o převedení objektu na řetězec (napr. při System.out.println) je vrácen výstup této metody.
     * Jde o překrytí metody toString() definované ve třídě java.lang.Object, ze které dědí všechny třídy v Javě.
     * @return String
     */
    @Override
    public String toString() {
        return "Seznam o velikosti " + list.size() + " se souctem " + this.sum();
    }

    /**
     * Vrátí součet všech prvků v seznamu. @Override znamená, že implementuje funkci rozhraní ISum.
     * @return Součet všech prvků.
     */
    @Override
    public int sum() {

        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return sum;

    }

    /**
     * Vypíše obsah seznamu na System.out
     * Seznam (počet prvků): X X X
     */
    public void print() {
        System.out.print("Seznam (" + list.size() + "): ");
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
}
