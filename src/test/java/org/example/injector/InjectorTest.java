package org.example.injector;

import org.example.model.SomeBean;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class InjectorTest {

    @Test
    void testInject_SuccessfullyInjectsDependencies() throws Exception {
        SomeBean bean = new SomeBean();
        Injector injector = new Injector();

        injector.inject(bean);

        Field field1 = SomeBean.class.getDeclaredField("field1");
        field1.setAccessible(true);
        Object injectedField1 = field1.get(bean);

        Field field2 = SomeBean.class.getDeclaredField("field2");
        field2.setAccessible(true);
        Object injectedField2 = field2.get(bean);

        assertNotNull(injectedField1, "Поле field1 должно быть проинициализировано");
        assertNotNull(injectedField2, "Поле field2 должно быть проинициализировано");
        assertEquals("org.example.model.SomeImpl", injectedField1.getClass().getName());
        assertEquals("org.example.model.SODoer", injectedField2.getClass().getName());
    }
}