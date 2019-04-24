package com.jaspervanmerle.pocfactoryreflection.factory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class BaseFactory<T extends FactoryInstance> {
    private static final String PACKAGE_ROOT = "com.jaspervanmerle.pocfactoryreflection";

    private String basePackage;

    private boolean isInstantiated = false;
    private Map<String, T> instances = new HashMap<>();

    public BaseFactory(String packageName) {
        this.basePackage = PACKAGE_ROOT;

        if (packageName.length() > 0) {
            this.basePackage += "." + packageName;
        }
    }

    public T create(String id) {
        ensureInstantiated();

        if (!instances.containsKey(id)) {
            throw new IllegalArgumentException(String.format("There is no instance with id '%s'", id));
        }

        return (T) instances.get(id).createClone();
    }

    public List<T> getAll() {
        ensureInstantiated();
        return new ArrayList<>(instances.values());
    }

    private void ensureInstantiated() {
        if (isInstantiated) {
            return;
        }

        isInstantiated = true;

        URL rootURL = Thread
            .currentThread()
            .getContextClassLoader()
            .getResource(basePackage.replace('.', '/'));

        if (rootURL == null) {
            System.err.println(String.format("Can't get url for package %s, are you sure it exists?", basePackage));
            return;
        }

        try {
            File[] files = new File(rootURL.toURI()).listFiles();
            if (files != null) {
                for (File file : files) {
                    findImplementations(file, basePackage);
                }
            }
        } catch (URISyntaxException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    private void findImplementations(File currentFile, String currentPackage) {
        String resource = currentPackage + "." + currentFile.getName();

        if (currentFile.isDirectory()) {
            File[] files = currentFile.listFiles();

            if (files != null) {
                for (File file : files) {
                    findImplementations(file, resource);
                }
            }

            return;
        }

        if (!resource.endsWith(".class") || resource.contains("$")) {
            return;
        }

        String className = resource.substring(0, resource.length() - ".class".length());
        addClassIfValid(className);
    }

    private void addClassIfValid(String className) {
        try {
            Class clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor();
            T instance = (T) constructor.newInstance();

            instances.put(instance.getId(), instance);
        } catch (Exception e) {
            // Class is not an instance of T, continue searching
        }
    }
}
