package com.jaspervanmerle.pocfactoryreflection.factory;

public interface FactoryInstance {
    /**
     * The id of this instance.
     * <p>
     * Every instance class in a factory needs a unique id.
     */
    String getId();

    /**
     * Create a shallow clone of this instance.
     * <p>
     * Can't be named clone() because that would clone with the protected Object.clone() method.
     */
    Object createClone();
}
