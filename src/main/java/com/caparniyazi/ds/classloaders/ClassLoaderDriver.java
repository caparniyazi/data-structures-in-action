package com.caparniyazi.ds.classloaders;

import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * Class loaders are part of the Java Runtime Environment.
 * When the JVM requests a class, the class loader tries to locate the class and
 * load the class definition into the runtime using the fully qualified class name.
 * <p/>
 * Let’s say we have a request to load an application class into the JVM.
 * The system class loader first delegates the loading of that class to its parent platform class loader,
 * which in turn delegates it to the bootstrap class loader.
 * Only if the bootstrap and then the platform class loader are unsuccessful in loading the class,
 * does the system(app) class loader try to load the class itself.
 * <p/>
 * As a consequence of the delegation model, it’s easy to ensure unique classes,
 * as we always try to delegate upwards.
 * If the parent class loader isn’t able to find the class,
 * only then will the current instance attempt to do so itself.
 */
public class ClassLoaderDriver {
    public static void main(String[] args) {

        System.out.println("Platform Classloader: " + ClassLoader.getPlatformClassLoader());
        System.out.println("System Classloader: " + ClassLoader.getSystemClassLoader());

        // System(App) Class Loader loads our files in the classpath.
        System.out.println("Classloader of this class: " + ClassLoaderDriver.class.getClassLoader());

        /*
        Platform class loader – Loads the platform classes,
        which include the Java SE platform APIs,
        their implementation classes, and JDK-specific run-time classes.
         */
        System.out.println("Classloader of DriverManager: " + DriverManager.class.getClassLoader());

        /*
         Bootstrap class loader is displayed as null in the output.
         This is because the bootstrap class loader is written in native code,
         not Java, so it doesn’t show up as a Java class.
         Bootstrap ClassLoader is not a Java class, so technically it does not need to be loaded into JVM —
         it is not the bytecode. The Bootstrap ClassLoader is a part of the JVM itself
         and is written on a native platform language. So it does not need any kind of loading and initialization.

         A bootstrap or primordial class loader is the parent of all the others;
         however, it doesn’t have a parent.

         Bootstrap ClassLoader is responsible for loading classes that are located in the jre/lib/rt.jar file.
         This is the core runtime (rt stands for runtime) jar that contains all classes that are shipped
         along with every JDK. These are all classes that are a part of a Java Standard Edition:

         java.lang classes (Integer, Class, String, Object, ClassLoader, etc.)
         java.util classes (Collection, Iterable, List, Map, HashMap, etc.)
         java.io classes (InputStream, OutputStream, File, etc.)

         Notice that the ClassLoader itself,
         which is an abstract Java class that represents the ClassLoader classes in Java,
         is itself loaded by Bootstrap ClassLoader.
         */

        System.out.println("Classloader of ArrayList: " + ArrayList.class.getClassLoader());
        System.out.println(String.class.getClassLoader());
    }
}
