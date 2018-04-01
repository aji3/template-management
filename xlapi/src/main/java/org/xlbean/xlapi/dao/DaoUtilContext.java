package org.xlbean.xlapi.dao;

public class DaoUtilContext {

    private static ThreadLocal<DaoUtilContext> INSTANCE = new ThreadLocal<>();

    public static DaoUtilContext getInstance() {
        DaoUtilContext context = INSTANCE.get();
        if (context == null) {
            context = new DaoUtilContext();
            INSTANCE.set(context);
        }
        return context;
    }

    public String getUser() {
        return "testuser";
    }
}
