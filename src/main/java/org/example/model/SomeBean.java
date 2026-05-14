package org.example.model;

import org.example.annotation.AutoInjectable;

/**
 * Класс, демонстрирующий внедрение зависимостей.
 */
public class SomeBean {

    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}