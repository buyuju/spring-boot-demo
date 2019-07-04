package com.example.demo.domain;

import com.example.demo.exception.InnerException;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author limeng
 * @Description add peek poll printStr
 * @Date @Date: 2019/6/25 16:54
 * @Modified by :
 **/
public class Queue<E> {

    protected Object[] objArray;

    /**
     * 队头
     */
    protected Object head;

    /**
     * 元素大小
     */
    protected int length;

    /**
     * 数组最大值
     */
    protected int maxSize;

    private static final int DEFAULT_CAPACITY = 10;

    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    public Queue(int capacity) {
        objArray = new Object[capacity];
        maxSize = capacity;
    }

    public void add(E obj){
        if (obj == null) {
            throw new IllegalArgumentException("param is not null");
        }
        if (length++ > maxSize) {
            // 扩容 待续 1.5倍 TODO
        }
        head = obj;
        objArray[length -1] = head;
    }

    public String printStr() {
        StringBuilder sb = new StringBuilder();
        for (int a = length -1 ; a >= 0 ; a--) {
            sb.append(objArray[a]).append(",");
        }
        return sb.substring(0, sb.length()-1);
    }

    /**
     * 查看head 元素
     * @return
     */
    public E peek() {
        E obj;
        obj= (E)head;
        return obj;
    }

    /**
     * 取head元素
     * @return
     */
    public E poll() {
        if (head == null) {
            throw new InnerException("queue is empty!");
        }
        E obj;
        obj= peek();
        length--;
        objArray[length] = null;
        head = (length <= 0) ? null : objArray[length - 1];
        return obj;
    }

    public static void main(String[] args) {
        System.out.println(ss());
    }

    public static int ss() {
        int a = 0;
        try {
            return a;
        } catch (Exception e) {
            a++;
        } finally {
            a++;
        }
        return a;
    }
}
