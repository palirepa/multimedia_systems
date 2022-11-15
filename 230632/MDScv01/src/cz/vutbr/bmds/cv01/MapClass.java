package cz.vutbr.bmds.cv01;

import java.util.HashMap;

public class MapClass implements ISum {
    private static int count = 0;
    public MapClass(){
        count++;
    }
    @Override
    public int sum(){
        int sum = 0;

        for (int i:mapa) {
            sum +=i;
        }
        return sum;
    }
    private HashMap<Integer, String> mapa = new HashMap<Integer, String>();

    public void store(Integer id, String value)throws ArrayStoreException{
        if (id>0){
            mapa.add(id);
        }
        else{
            throw new ArrayStoreException("ID uz je zmapovane.");
        }
    }

    public String getValue(Integer id) throws NoSuchFieldException{
        return id;
    }

    public void deleteKey(Integer id)throws NoSuchFieldException{

    }

    public int getSize(){
        return count;
    }

    public void print(){
        System.out.println("a");
    }
}
