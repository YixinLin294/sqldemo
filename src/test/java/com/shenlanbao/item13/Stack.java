package com.shenlanbao.item13;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack implements Cloneable {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    @Override
    protected Stack clone() throws CloneNotSupportedException {
        Stack result = (Stack) super.clone();
        result.elements = elements.clone();
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Stack stack = new Stack();
        Stack clone = stack.clone();
        System.out.println("stack origin "+ stack);
        System.out.println("elements origin " + stack.elements);

        System.out.println("stack copy " + clone);
        System.out.println("elements copy " + clone.elements);
    }
}
