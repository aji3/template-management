package org.xlbean.xlapi.dao;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import org.apache.commons.beanutils.BeanUtils;

public class DaoUtil {

    public static void updateSystemValueForInsert(Object record) {
        LocalDateTime now = LocalDateTime.now();
        try {
            BeanUtils.setProperty(record, "createdBy", DaoUtilContext.getInstance().getUser());
            BeanUtils.setProperty(record, "createdTimestamp", now);
            BeanUtils.setProperty(record, "updatedBy", DaoUtilContext.getInstance().getUser());
            BeanUtils.setProperty(record, "updatedTimestamp", now);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // ignore
        }
    }

    public static void updateSystemValueForUpdate(Object record) {
        LocalDateTime now = LocalDateTime.now();
        try {
            BeanUtils.setProperty(record, "updatedBy", DaoUtilContext.getInstance().getUser());
            BeanUtils.setProperty(record, "updatedTimestamp", now);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // ignore
        }
    }
}
