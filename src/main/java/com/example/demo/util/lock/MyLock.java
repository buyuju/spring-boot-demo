package com.example.demo.util.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author limeng
 * @Description 可重入锁
 * @Date @Date: 2019/5/24 10:16
 * @Modified by :
 **/
public class MyLock {

    private static final int acquire = 1;

    private final MySync sync;

    public  MyLock() {
        this.sync = new MySync();
    }

    public static void main(String[] args) {
        new StringBuilder("2").append(1);
    }

    /**
     * 解锁
     */
    public void unlock() {
        sync.unlock();
    }

    static class MySync extends MyAbstractQueueSync {
        void lock() {
            // 尝试修改state 0表示为占用
            if (!tryAcquire()) {
                setQueue();
                // 如果未成功 阻塞线程
                System.out.println(Thread.currentThread() + "==锁已被占用");
                park();
            }
        }

        boolean tryAcquire() {
            Thread thread = Thread.currentThread();
            // 先判断state是否0
            int state = getState();
            if (state == 0) {
                // 未有线程获得锁  cas state
                if (compareAndSetState(0, MyLock.acquire)) {
                    setInclusiveThread(thread);
                    System.out.println(Thread.currentThread() +"获得了锁， state ：" + getState());
                    return true;
                }
            } else {
                // 已经有人获取锁，判断是否是本人 可重入
                if (thread == getInclusiveThread()) {
                    setState(state + MyLock.acquire);
                    System.out.println(Thread.currentThread() +"获得了锁， state ：" + getState());
                    return true;
                }
            }
            return false;
        }

        void unlock() {
            if (tryRelease()) {
                // 唤醒队列头
                unPark();
            }
        }

        boolean tryRelease() {
            Thread thread = Thread.currentThread();
            if (thread != getInclusiveThread()) {
                System.out.println("释放锁失败， 线程不匹配！");
                return false;
            }
            // 先判断state是否0
            int newState = getState() - MyLock.acquire;
            if (newState < 0) {
                System.out.println("释放锁失败， state 不匹配！");
                return false;
            }
            if (newState == 0) {
                setInclusiveThread(null);
                return true;
            }
            setState(newState);
            return false;
        }
    }

    public void lock() {
        sync.lock();
    }
}
