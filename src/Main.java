import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static class ComparatorByName implements Comparator<Method> {

        @Override
        public int compare(Method o1, Method o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static class ComparatorByField implements Comparator<Field> {

        @Override
        public int compare(Field o1, Field o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<Reflection> reflection = Reflection.class;
        /*System.out.println(reflection);

        Class<?> superclass = reflection.getSuperclass();

        System.out.println(superclass);

        Class<?>[] interfaces = reflection.getInterfaces();

        for (Class<?> aInterfaces : interfaces) {
            System.out.println(aInterfaces);
        }

        Reflection reflectionObject = reflection.getDeclaredConstructor().newInstance();

        System.out.println(reflectionObject);*/

        Set<Field> fields = new TreeSet<>(new ComparatorByField());

        Set<Method> getters = new TreeSet<>(new ComparatorByName());

        Set<Method> setters = new TreeSet<>(new ComparatorByName());


        Arrays.stream(reflection.getDeclaredFields())
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .forEach(fields::add);

        Arrays.stream(reflection.getDeclaredMethods())
                .filter(g -> !Modifier.isPublic(g.getModifiers()) && g.getName().startsWith("get") && g.getParameterCount() == 0)
                .forEach(getters::add);

        Arrays.stream(reflection.getDeclaredMethods()).
                filter(s -> !Modifier.isPrivate(s.getModifiers()) && s.getName().startsWith("set") && s.getParameterCount() == 1)
                .forEach(setters::add);

        for (Field field : fields) {
            System.out.println(String.format("%s must be private!"
                    , field.getName()));
        }

        for (Method method : getters) {
            System.out.println(String.format("%s have to be public!"
                    , method.getName()));
        }

        for (Method method : setters) {
            System.out.println(String.format("%s have to be private!"
                    , method.getName()));
        }

        Class<?> cl = Tracker.class;

        Map<String , List<String>> methodsByAuthor = new HashMap<>();

        Method[] methods = cl.getDeclaredMethods();

        for (Method method : methods) {
           Author annotation = method.getAnnotation(Author.class);

           if (annotation != null) {
               methodsByAuthor.putIfAbsent(annotation.name(), new ArrayList<>());
               methodsByAuthor.get(annotation.name()).add(method.getName());
           }
        }

        methodsByAuthor.forEach((key, value) -> {
            System.out.printf("%s: ", key);
            for (String name : value) {
                System.out.print(name + "()");
            }
            System.out.println();
        });

    }
}
