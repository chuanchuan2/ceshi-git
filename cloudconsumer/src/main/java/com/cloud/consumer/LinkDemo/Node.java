package com.cloud.consumer.LinkDemo;

import org.apache.poi.ss.formula.functions.T;

public class Node {

    private T data;
    //private Node next;
    public Node next;

    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Node(T data) {
        this(data,null);
    }



/* Node next=null;//节点引用，指向下一个节点
    Node head=null;
    int data;//节点的对象即内容
    public Node(int data){
        this.data=data;
    }

    public void addNode(int d){
        Node node=new Node(d);//实例化一个节点
        if (head==null){

        }
    }*/



}
