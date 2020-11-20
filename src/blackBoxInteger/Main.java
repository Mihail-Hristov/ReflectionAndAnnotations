package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        Class<?> clazz = BlackBoxInt.class;

        Constructor<?> constructor = clazz.getDeclaredConstructor(int.class);

        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = (BlackBoxInt) constructor.newInstance(0);

        String input = scanner.nextLine();

        while (!"END".equals(input)) {

            String[] tokens = input.split("_");

            String command = tokens[0];
            int value = Integer.parseInt(tokens[1]);


            Method method = clazz.getDeclaredMethod(command, int.class);
            method.setAccessible(true);
            method.invoke(blackBoxInt, value);

            Field field = clazz.getDeclaredField("innerValue");
            field.setAccessible(true);
            System.out.println(field.get(blackBoxInt));

            input = scanner.nextLine();
        }

    }
}
