package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class<?> blackBox = BlackBoxInt.class;

        Method[] methods = blackBox.getDeclaredMethods();

        Constructor<?> blackBoxInt = blackBox.getDeclaredConstructor(int.class);

        blackBoxInt.setAccessible(true);
        blackBoxInt.newInstance(15);
        System.out.println(blackBoxInt);

        blackBox.getDeclaredField("innerValue").setAccessible(true);

        BlackBoxInt.class.getDeclaredConstructor().setAccessible(true);
        Constructor constructor = BlackBoxInt.class.getDeclaredConstructor(int.class);

        
        BlackBoxInt blackBoxInt1 = (BlackBoxInt) constructor.newInstance(15);

        System.out.println();

    }
}
