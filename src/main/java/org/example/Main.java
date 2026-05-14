package org.example;

import org.example.injector.Injector;
import org.example.model.SomeBean;

public class Main {
    public static void main(String[] args) {
        SomeBean sb = (new Injector()).inject(new SomeBean());

        System.out.println("Результат работы (ожидается AC):");
        sb.foo();
    }
}