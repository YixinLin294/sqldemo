package com.shenlanbao.singleton;

import java.io.*;

public enum Elvis1 {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("leaveTheBuilding");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Elvis1 instance = Elvis1.INSTANCE;
        Elvis1 instance1 = Elvis1.INSTANCE;

        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());

        File file = new File("Elvis1");
        file.createNewFile();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(instance);
        objectOutputStream.flush();
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        Elvis1 elvis1 = (Elvis1) objectInputStream.readObject();
        System.out.println(elvis1.hashCode());

    }
}
