package com.example.mykhailoshevchenko05.components;

import com.example.mykhailoshevchenko05.CurrentDatasource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DatasourceContextHolder {

    private static ThreadLocal<CurrentDatasource> CONTEXT = new ThreadLocal<>();

    public static void set(CurrentDatasource clientDatabase) {
        CONTEXT.set(clientDatabase);
    }

    public static CurrentDatasource getCurrentDatasource() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
