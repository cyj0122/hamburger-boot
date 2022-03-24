package org.hamburger.boot.core.exception;

import org.hamburger.boot.core.dto.ICodeEnum;

import java.util.Collection;
import java.util.Map;

public abstract class Assert {

    public static void isTrue(boolean expression, ICodeEnum codeEnum) {
        if (!expression) {
            throw new HamException(codeEnum);
        }
    }

    public static void isFalse(boolean expression, ICodeEnum codeEnum) {
        if (expression) {
            throw new HamException(codeEnum);
        }
    }

    public static void notNull(Object object, ICodeEnum codeEnum) {
        if (object == null) {
            throw new HamException(codeEnum);
        }
    }

    public static void notBlank(String string, ICodeEnum codeEnum) {
        if (string == null || string.isEmpty()) {
            throw new HamException(codeEnum);
        }
    }

    public static void notEmpty(Collection<?> collection, ICodeEnum codeEnum) {
        if (collection == null || collection.isEmpty()) {
            throw new HamException(codeEnum);
        }
    }

    public static void notEmpty(Map<?, ?> map, ICodeEnum codeEnum) {
        if (map == null || map.isEmpty()) {
            throw new HamException(codeEnum);
        }
    }
}
