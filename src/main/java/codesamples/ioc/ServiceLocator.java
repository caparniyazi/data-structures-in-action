package codesamples.ioc;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    // Data fields
    private static Map<String, Object> services = new HashMap<>();

    public static void register(String name, Object service) {
        services.put(name, service);
    }

    public static Object getService(String name) {
        return services.get(name);
    }
}
