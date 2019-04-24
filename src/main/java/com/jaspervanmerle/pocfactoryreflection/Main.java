package com.jaspervanmerle.pocfactoryreflection;

import com.jaspervanmerle.pocfactoryreflection.node.BaseNode;
import com.jaspervanmerle.pocfactoryreflection.node.NodeFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        NodeFactory factory = NodeFactory.getInstance();

        List<BaseNode> nodes = factory.getAll();

        System.out.println("Available nodes, retrieved NodeFactory.getAll():");

        for (BaseNode node : nodes) {
            System.out.println(node.getId() + ": " + node.getDescription());
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println();

        System.out.println("Manually created notes, retrieved from NodeFactory.create():");
        System.out.println("AND: " + factory.create("AND").getDescription());
        System.out.println("NOT: " + factory.create("NOT").getDescription());
    }
}
