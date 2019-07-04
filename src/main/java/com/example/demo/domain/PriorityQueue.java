package com.example.demo.domain;

import com.example.demo.exception.InnerException;

/**
 * @Author limeng
 * @Description add printStr peek poll
 * @Date @Date: 2019/6/25 16:56
 * @Modified by :
 **/
public class PriorityQueue<E> {

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

    //private final Comparator<? extends E> comparator;

    private static final int DEFAULT_CAPACITY = 10;

    public PriorityQueue() {
        this(DEFAULT_CAPACITY);
    }

    public PriorityQueue(int capacity) {
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
        objArray[length -1] = obj;
        if (head == null) {
            // 第一次无需对比大小
            head = obj;
        } else {
            int index = length -1;
            while (index > 0) {
                Comparable<? super E> right = (Comparable<? super E>)objArray[index];
                Comparable<? super E> left = (Comparable<? super E>)objArray[index-1];
                if (right.compareTo((E)left) >= 0) {
                    head = objArray[length -1];
                    break;
                }
                // 文字通理
                swapObject(index);
                index--;
            }
        }
    }

    /**
     * 置换
     * @param index
     */
    private void swapObject(int index) {
        Object swapObj = objArray[index];
        objArray[index] = objArray[index - 1];
        objArray[index - 1] = swapObj;
    }

    /**
     * 遍历打印
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
        PriorityQueue<Object> pQueue = new PriorityQueue<>();
        pQueue.add(2);
        pQueue.add(1);
        pQueue.add(5);
        pQueue.add(3);pQueue.add(4);


        System.out.println(pQueue.printStr());
        System.out.println(pQueue.peek());
        System.out.println(pQueue.poll());
        System.out.println(pQueue.peek());
    }

}
