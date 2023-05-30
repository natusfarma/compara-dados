package com.natusfarma.pc.itecvstotvs.model;

public class ModeloTipo<T> {

    private T obj1;
    private T obj2;

    public ModeloTipo(T obj1, T obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public T getObj1() {
        return obj1;
    }

    public T getObj2() {
        return obj2;
    }

    @Override
    public String toString() {
        return          obj1 +
                " | " + obj2 ;
    }
}
