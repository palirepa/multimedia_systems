import cz.vutbr.bmds.cv01.MyClass;

public class Main {
    public static void main(String[] args) {
        MyClass prvni = new MyClass();
        MyClass druha = new MyClass();
        MyClass treti = null;

        try{
            treti = new MyClass(1,2,3,4,5,6);

            prvni.addInteger(10);
            prvni.addInteger(20);
            prvni.addInteger(30);

            druha.addInteger(60);
            druha.addInteger(1);
        }catch (IllegalArgumentException e){
            System.err.println("Chyba: " + e.getMessage());
        }
        System.out.println("Pocet vytvorenych trid: " + MyClass.getCount());

        System.out.println("Existuje v trojke 4ka? " + treti.integerExists(4));

        MyClass united = MyClass.createUnited(prvni, druha);
        united.print();

        System.out.println(united);
    }
}
