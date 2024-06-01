package dev.ta2khu75.java5assignment.enviroments;

public class TemplateEnviroment {
    private TemplateEnviroment() {
        throw new IllegalStateException("Enviroment class");
    }

    public static final String PROUDUCT_IMAGE = "crud/productImage";
    public static final String PRODUCT = "crud/product";
}
