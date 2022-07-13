/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

/**
 *
 * @author youssef hany
 */
public class Queue {

    private Integer[] queue;
    private Integer capacity;
    private Integer front;
    private Integer rear;

    public Queue(Integer size) {
        this.queue = new Integer[size];
        this.front = this.rear = -1;
        this.capacity = size;
    }

    public Integer deQueue() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("QUEUE IS EMPTY, CAN'T DEQUEUE");
        }
        if (this.front >= this.rear) {
            this.front = this.rear = -1;
        }
        return this.queue[this.front++];
    }

    public void enQueue(Integer element) throws Exception {
        if (this.isFull()) {
            throw new Exception("CAN'T INSERT, QUEUE IS FULL");
        }
        if (this.front == -1) {
            this.front = 0;
        }
        this.queue[++this.rear] = element;
    }

    public boolean isFull() {
        if (this.front == 0 && this.rear == this.capacity - 1) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return this.front == -1 && this.rear == -1;
    }

    void display() {
        /* Function to display elements of Queue */
        int i;
        if (isEmpty()) {
            System.out.println("Empty Queue");
        } else {
            System.out.println("\nFront index-> " + front);
            System.out.println("Items -> ");
            for (i = front; i <= rear; i++) {
                System.out.print(queue[i] + "  ");
            }

            System.out.println("\nRear index-> " + rear);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Queue q = new Queue(5);

        // deQueue is not possible on empty queue
//        q.deQueue();

        // enQueue 5 elements
        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);
        q.enQueue(4);
        q.enQueue(5);

        // 6th element can't be added to because the queue is full
//        q.enQueue(6);

        q.display();

        // deQueue removes element entered first i.e. 1
        System.out.println("dequeue element -> {" + q.deQueue() + "}");

//         Now we have just 4 elements
        q.display();

    }

}
