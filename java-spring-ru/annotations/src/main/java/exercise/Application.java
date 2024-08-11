package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.lang.annotation.Annotation;


public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);
        handlerAnnotationInspect(Inspect.class);
    }

    private static <T extends Annotation> void handlerAnnotationInspect(Class<T> theClass) {
        for (var method : Address.class.getDeclaredMethods()) {
            if (method.getAnnotation(theClass) != null) {
                System.out.format(
                        "Method %s returns a value of type %s%n",
                        method.getName(),
                        method.getReturnType().getSimpleName()
                );
            }
        }
    }

}
