package com.company;

/*1. Напишите программу, которая каждую секунду отображает на экране данные о времени, прошедшем от
начала сессии, а другой ее поток выводит сообщение каждые 5 секунд. Предусмотрите возможность
ежесекундного оповещения потока, воспроизводящего сообщение, потоком, отсчитывающим время.
2. Не внося изменений в код потока-"хронометра" , добавьте еще один поток, который выводит
на экран другое сообщение каждые 7 секунд. Предполагается использование методов wait(), notifyAll().*/

public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();
        MyThread myThread = new MyThread(counter);
        ThreadMessage5  threadMessage5 = new ThreadMessage5(counter);
        ThreadMessage7  threadMessage7 = new ThreadMessage7(counter);
        myThread.start();
        threadMessage5.start();
        threadMessage7.start();
    }
}

class MyThread extends Thread{

    private Counter count;

    public MyThread(Counter count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (count) {
            count.increment();
            System.out.println(count);
            count.notifyAll();
            }
        }
    }
}

class ThreadMessage5 extends Thread{

    private Counter count;

    public ThreadMessage5(Counter count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (;;){
            synchronized (count){
                if ((count.getCount() % 5) == 0 && count.getCount()!=0)
                    System.out.println("Message5");

                try {
                    count.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ThreadMessage7 extends Thread{

    private Counter count;

    public ThreadMessage7(Counter count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (;;){
            synchronized (count){
                if ((count.getCount() % 7) == 0 && count.getCount()!=0)
                    System.out.println("Message7");

                try {
                    count.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}