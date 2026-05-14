package org.example.injector;

import org.example.annotation.AutoInjectable;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для осуществления внедрения зависимостей (Dependency Injection).
 */
public class Injector {

    private final Properties properties;

    /**
     * Конструктор инициализирует объект Properties и загружает настройки из файла.
     */
    public Injector() {
        properties = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                properties.load(in);
            } else {
                throw new RuntimeException("Файл config.properties не найден в ресурсах");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке properties файла", e);
        }
    }

    /**
     * Метод осуществляет поиск полей с аннотацией @AutoInjectable и инициализирует их.
     *
     * @param object Объект любого класса для внедрения зависимостей
     * @param <T>    Тип объекта
     * @return Тот же самый объект с проинициализированными полями
     */
    public <T> T inject(T object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();

                String implementationClassName = properties.getProperty(interfaceName);

                if (implementationClassName != null) {
                    try {
                        Class<?> implClass = Class.forName(implementationClassName);
                        Object instance = implClass.getDeclaredConstructor().newInstance();

                        field.setAccessible(true);
                        field.set(object, instance);

                    } catch (Exception e) {
                        throw new RuntimeException("Ошибка при внедрении зависимости для поля " + field.getName(), e);
                    }
                }
            }
        }
        return object;
    }
}