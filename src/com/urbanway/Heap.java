package com.urbanway;

import java.util.ArrayList;
import java.util.HashMap;

public class Heap<T extends Comparable<T>> {
    ArrayList<T> data = new ArrayList<>();
    HashMap<T, Integer> map = new HashMap<>();

    public void add(T item) {
        data.add(item);
        map.put(item, this.data.size() - 1);
        upheapify(data.size() - 1);
    }

    private void upheapify(int ci) {
        int pi = (ci - 1) / 2;
        if (ci > 0 && data.get(ci).compareTo(data.get(pi)) > 0) {
            swap(pi, ci);
            upheapify(pi);
        }
    }

    private void swap(int i, int j) {
        T ith = data.get(i);
        T jth = data.get(j);

        data.set(i, jth);
        data.set(j, ith);
        map.put(ith, j);
        map.put(jth, i);
    }

    public void display() {
        System.out.println(data);
    }

    public int size() {
        return this.data.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty. Cannot remove elements.");
        }
        swap(0, this.data.size() - 1);
        T rv = this.data.remove(this.data.size() - 1);
        map.remove(rv);
        downheapify(0);
        return rv;
    }

    private void downheapify(int pi) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int largest = pi;

        if (lci < this.data.size() && data.get(lci).compareTo(data.get(largest)) > 0) {
            largest = lci;
        }

        if (rci < this.data.size() && data.get(rci).compareTo(data.get(largest)) > 0) {
            largest = rci;
        }

        if (largest != pi) {
            swap(largest, pi);
            downheapify(largest);
        }
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty. Cannot get elements.");
        }
        return this.data.get(0);
    }

    public void updatePriority(T item) {
        Integer index = map.get(item);
        if (index != null) {
            upheapify(index);
            downheapify(index);
        }
    }
}
