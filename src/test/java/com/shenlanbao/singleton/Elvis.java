package com.shenlanbao.singleton;

import org.apache.poi.util.BoundedInputStream;

import java.io.*;
import java.util.function.Supplier;

public class Elvis implements Serializable {
    private transient Integer age;

    private static final Elvis INSTANCE = new Elvis();
    private Elvis() {
        age = 100;
    }
    public static Elvis getInstance() {
        return INSTANCE;
    }
    public void leaveTheBuilding() {
        System.out.println("leaveTheBuilding");
    }

    private Object readResolve() {
        System.out.println("read resolve");
        return getInstance();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
/*        Supplier<Elvis> sup = Elvis::getInstance;
        Elvis elvis = sup.get();
//        elvis.leaveTheBuilding();
        System.out.println(elvis);
        System.out.println(elvis.age);
        Elvis elvis1 = sup.get();
        System.out.println(elvis1);
        System.out.println(elvis1.age);


        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(elvis);
        objectOutputStream.flush();
        objectOutputStream.close();

        elvis = null;
        elvis1 = null;
        System.gc();*/

        File file = new File("Elvis");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Elvis elvis2 = (Elvis) objectInputStream.readObject();
        System.out.println(elvis2);
        System.out.println(elvis2.age);
    }
}
