package com.example.demo.domain;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;

/**
 * @Author limeng
 * @Description peek pop push size
 * @Date @Date: 2019/6/25 16:55
 * @Modified by :
 **/
public class Stack<E> {

    protected Object[] objArray;

    /**
     * 元素存储个数
     */
    protected int length;

    private static final int DEFAULT_CAPACITY = 10;

    public Stack(int capacity) {
        this.objArray = new Object[capacity];
    }

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public void push(E obj) {
        ensureCapacity(length + 1);
        objArray[length] =  obj;
        length++;
    }

    /**
     * 打印所有元素
     * @return
     */
    public String printStr() {
        StringBuilder sb = new StringBuilder();
        for (int a = length -1 ; a >= 0 ; a--) {
            sb.append(objArray[a]).append(",");
        }
        return sb.substring(0, sb.length()-1);
    }

    /**
     * 偷窥顶端的男人
     * @return
     */
    public E peek() {
        E obj;
        if (length <= 0) {
            throw new EmptyStackException();
        }
        obj = (E)objArray[length - 1];
        return obj;
    }

    /**
     * 干掉最顶端的男人
     * @return
     */
    public E pop() {
        E obj;
        obj = peek();
        length--;
        objArray[length] = null;
        return obj;
    }

    /**
     * 确保数组是否需要扩容
     * @param newCapacity
     */
    private void ensureCapacity(int newCapacity) {
        if (newCapacity > objArray.length) {
            // 需要扩容 策略待完善
            objArray = Arrays.copyOf(objArray, newCapacity);
        }
    }

    public int size() {
        return length;
    }

    public static void main(String[] args) {
        //new HashMap<>(8).put();
        /*java.util.Stack<Object> objects = new java.util.Stack<>();
        objects.push(1);
        objects.push("2");
        System.out.println(objects.pop());
        System.out.println(objects.pop());*/
        Stack stack = new Stack<>();
        stack.push(1);
        stack.push("2222");
        stack.push("asdasd");
        System.out.println(stack.printStr());
    }
}
