package gocepat;

public class ArrayQueue {
    private int[] elements;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public ArrayQueue(int initialCapacity) {
        this.capacity = Math.max(initialCapacity, 8);
        this.elements = new int[this.capacity];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    public ArrayQueue() {
        this(16);
    }

    public void enqueue(int item) {
        if (count == capacity) {
            resize();
        }
        elements[rear] = item;
        rear = (rear + 1) % capacity;
        count++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Antrean kosong (Queue Underflow)!");
        }
        int item = elements[front];
        front = (front + 1) % capacity;
        count--;
        return item;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        int[] newElements = new int[newCapacity];
        for (int i = 0; i < count; i++) {
            newElements[i] = elements[(front + i) % capacity];
        }
        elements = newElements;
        front = 0;
        rear = count;
        capacity = newCapacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public int getCapacity() {
        return capacity;
    }
}
