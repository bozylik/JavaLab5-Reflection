package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для автоматического внедрения зависимостей.
 * Применяется к полям класса.
 */
@Retention(RetentionPolicy.RUNTIME) // Доступна во время выполнения (для рефлексии)
@Target(ElementType.FIELD) // Применяется только к полям
public @interface AutoInjectable {
}