package gocepat;

public class DynamicIntArray {
    private int[] data;
    private int size;
    private int capacity;

    public DynamicIntArray(int initialCapacity) {
        this.capacity = Math.max(initialCapacity, 2);
        this.data = new int[this.capacity];
        this.size = 0;
    }

    public DynamicIntArray() {
        this(4);
    }

    public void add(int element) {
        if (size == capacity) {
            resize();
        }
        data[size++] = element;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        int[] newData = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
}
