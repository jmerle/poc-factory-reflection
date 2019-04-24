package com.jaspervanmerle.pocfactoryreflection.node;

import com.jaspervanmerle.pocfactoryreflection.factory.BaseFactory;

public class NodeFactory extends BaseFactory<BaseNode> {
    private NodeFactory() {
        super("node.impl");
    }

    private static class Holder {
        private static final NodeFactory INSTANCE = new NodeFactory();
    }

    public static NodeFactory getInstance() {
        return Holder.INSTANCE;
    }
}
