package com.jaspervanmerle.pocfactoryreflection.node.impl;

import com.jaspervanmerle.pocfactoryreflection.node.BaseNode;

public class NotNode extends BaseNode {
    @Override
    public String getDescription() {
        return "Inverts the input signal";
    }

    @Override
    public String getId() {
        return "NOT";
    }

    @Override
    public Object createClone() {
        return new NotNode();
    }
}
