package com.cloud.consumer.LinkDemo;
/*
  T - Type（Java 类），T代表在调用时的指定类型，会进行类型推断。
  K - Key（键）
  V - Value（值）
  N - Number（数值类型）
  ？- 表示不确定的java类型，是类型通配符，代表所有类型。？不会进行类型推断
*/

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//单向链表的案例
public class LinkDemo {

    private Node head;
    private int size;

    public LinkDemo() {
        this.head = null; //头节点
        this.size = 0;//链表元素个数
    }

    public void add(T data){
        Node node=new Node(data);
        node.next=this.head;
        this.head=node;
        this.size++;
        HashMap hashMap=new HashMap();
        hashMap.put("","");
        List list=new ArrayList();
        list.add("");
    }

}
