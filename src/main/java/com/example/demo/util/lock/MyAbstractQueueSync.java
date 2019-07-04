package com.example.demo.util.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/5/24 10:30
 * @Modified by :
 **/
public abstract class MyAbstractQueueSync {


    volatile MyNode head;
    volatile MyNode tail;

    static final class MyNode {
        Thread thread;
        MyNode preN;
        MyNode nextN;

        public MyNode(Thread currentT, MyNode nextNode) {
            thread = currentT;
            nextN = nextNode;
        }

        public MyNode(MyNode preN, MyNode nextN) {
            this.preN = preN;
            this.nextN = nextN;
        }
    }

    public MyNode getHead() {
        return head;
    }

    public void setHead(MyNode head) {
        this.head = head;
    }

    public MyNode getTail() {
        return tail;
    }

    public void setTail(MyNode tail) {
        this.tail = tail;
    }

    /**
     * 锁拥有次数 0表示未占用
     */
    private volatile int state;

    protected final int getState() {
        return state;
    }

    protected final void setState(int state) {
        this.state = state;
    }

    /**
     * 排他线程
     */
    private Thread inclusiveThread;

    public Thread getInclusiveThread() {
        return inclusiveThread;
    }

    public void setInclusiveThread(Thread inclusiveThread) {
        this.inclusiveThread = inclusiveThread;
    }

    private static final Unsafe unsafe;
    private static Field getUnsafe;

    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    /*private static final long waitStatusOffset;
    private static final long nextOffset;*/

    static {
        try {
            getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            getUnsafe.setAccessible(true);
            unsafe = (Unsafe) getUnsafe.get(null);
            stateOffset = unsafe.objectFieldOffset
                    (MyAbstractQueueSync.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (MyAbstractQueueSync.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (MyAbstractQueueSync.class.getDeclaredField("tail"));
            /*waitStatusOffset = unsafe.objectFieldOffset
                    (MyAbstractQueueSync.MyNode.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset
                    (MyAbstractQueueSync.MyNode.class.getDeclaredField("next"));*/

        } catch (Exception ex) { throw new Error(ex); }
    }

    /**
     * cas入队 set tail
     * @param expect
     * @param update
     * @return
     */
    protected boolean compareAndSetTail(MyNode expect, MyNode update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    /**
     * cas入队 set head
     * @param update
     * @return
     */
    protected boolean compareAndSetHead(MyNode update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    /**
     * cas state
     * @param expect
     * @param update
     * @return
     */
    protected boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }


    protected void park() {
        unsafe.park(false, 0L);
    }

    /**
     * 放到队列中
     * @return
     */
    protected void setQueue() {
        Thread thread = Thread.currentThread();
        MyNode currentNode = new MyNode(thread, null);
        // 队尾
        MyNode pre = tail;
        if (pre != null) {
            currentNode.preN = pre;
            pre.nextN = currentNode;
            compareAndSetTail(pre, currentNode);
            return;
        }
        // 队列无值，第一次入队
        inQueue(currentNode);
    }

    /**
     * 入队
     * @param currentNode
     */
    private void inQueue(MyNode currentNode) {
        for (;;) {
            MyNode node = tail;
            if (node == null) {
                if (compareAndSetHead(currentNode)) {
                    // 第一次入队成功，则队尾即队头
                    tail = head;
                }
            } else {
                // 入队尾
                if (compareAndSetTail(tail, currentNode)) {
                    node.nextN = currentNode;
                    return;
                }

            }
        }
    }

    protected void unPark() {
        MyNode headNode = head;
        head = headNode.nextN;
        unsafe.unpark(headNode.thread);
    }
}
