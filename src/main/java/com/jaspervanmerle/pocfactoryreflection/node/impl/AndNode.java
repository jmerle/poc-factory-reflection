package com.jaspervanmerle.pocfactoryreflection.node.impl;

import com.jaspervanmerle.pocfactoryreflection.node.BaseNode;

public class AndNode extends BaseNode {
    @Override
    public String getDescription() {
        return "True if all inputs are true";
    }

    @Override
    public String getId() {
        return "AND";
    }

    @Override
    public Object createClone() {
        return new AndNode();
    }
}
