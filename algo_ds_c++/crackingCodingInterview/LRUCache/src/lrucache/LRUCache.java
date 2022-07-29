/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class ListNode {

    int value;
    int key;
    ListNode next;
    ListNode prev;

    public ListNode() {
    }

    public ListNode(int value, int key) {
        this.value = value;
        this.key = key;
        this.next = null;
        this.prev = null;
    }

    public ListNode(int value, int key, ListNode next, ListNode prev) {
        this.value = value;
        this.key = key;
        this.next = next;
        this.prev = prev;
    }

    public void printList() {
        ListNode temp = this;
        while (temp != null) {
            System.out.print("(" + temp.key + "," + temp.value + ") " + "-->");
            temp = temp.next;
        }
        System.out.println("");
    }
}

class DoublyList {

    private Map<Integer, ListNode> nodesMap;
    private ListNode head = null;
    private ListNode tail = null;
    private int size = 0;
    private final int maxSize;

    public DoublyList(int maxSize) {
        nodesMap = new HashMap<>(maxSize);
        this.maxSize = maxSize;
    }

    public void add(int key, int value) {
        if (nodesMap.containsKey(key)) {
            this.updateNodeValue(key, value);
            return;
        }
        ListNode newNode = new ListNode(value, key);
        this.addToNodesMap(key, newNode);
        if (size == maxSize) {
            head = head.next;
        } else {
            if (size == 0) {
                head = tail = newNode;
            }
            size++;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }
    
    private void updateNodeValue(int key, int newValue){
        ListNode node = nodesMap.get(key);
        node.value = newValue;
        this.updateNodeState(node);
    }

    private void addToNodesMap(int key, ListNode value) {
        if (size == maxSize) {
            ListNode lru = head;
            nodesMap.remove(lru.key);
            nodesMap.put(key, value);
        } else {
            nodesMap.put(key, value);
        }
    }

    public int get(int key) {
        if (!nodesMap.containsKey(key)) {
            return -1;
        }
        ListNode node = nodesMap.get(key);
        this.updateNodeState(node);
        return node.value;
    }

    private void updateNodeState(ListNode node) {
        if (tail == node) {
            return;
        } else if (node == head) {
            head = head.next;
            head.prev = null;
            tail.next = node;
            node.prev = tail;
            node.next = null;
            tail = node;
        } else {
            ListNode prev = node.prev;
            ListNode next = node.next;
            prev.next = next;
            next.prev = prev;
            node.next = null;
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void printList() {
        this.head.printList();
    }
}

public class LRUCache {

    private DoublyList doublyList;

    public LRUCache(int capacity) {
        this.doublyList = new DoublyList(capacity);
    }

    public int get(int key) {
        return this.doublyList.get(key);
    }

    public void put(int key, int value) {
        this.doublyList.add(key, value);
    }

    public void printCache() {
        this.doublyList.printList();
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(3);

        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        lRUCache.put(3, 3);
        lRUCache.printCache();
        
        lRUCache.put(4, 4);
        lRUCache.printCache();
        
        lRUCache.get(4);
        lRUCache.printCache();
        
        lRUCache.get(3);
        lRUCache.printCache();
        
        lRUCache.get(2);
        lRUCache.printCache();
        
        lRUCache.get(1);
        lRUCache.printCache();
        
        lRUCache.put(5, 5);
        lRUCache.printCache();
        
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(2));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
        System.out.println(lRUCache.get(5));
    }
}
