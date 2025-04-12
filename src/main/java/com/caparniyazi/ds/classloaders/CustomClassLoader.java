package com.caparniyazi.ds.classloaders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

/**
 * The class that loads classes from a file using a custom class loader.
 * <p/>
 * Custom class loaders are helpful for more than just loading the class during runtime. A few cases might include:
 * 1. Helping to modify the existing bytecode, e.g., weaving agents.
 * 2. Creating classes dynamically suited to the user’s needs, e.g., in JDBC,
 * switching between different driver implementations is done through dynamic class loading.
 */
public class CustomClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        String classFileName = "C:/Users/niyaz/IdeaProjects/data-structures-in-action/target/classes/com/caparniyazi/ds/arrays/ArrayUtils";
        Class<?> clazz = new CustomClassLoader().findClass(classFileName);
        Arrays.stream(clazz.getMethods()).forEach(System.out::println);
    }

    @Override
    public Class<?> findClass(String name) {
        byte[] b = loadClassFromFile(name);
        return defineClass(null, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName) {
        int nextValue = 0;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             InputStream is = Files.newInputStream(Path.of(fileName + ".class"))) {
            while ((nextValue = Objects.requireNonNull(is).read()) != -1) {
                bos.write(nextValue);
            }

            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
