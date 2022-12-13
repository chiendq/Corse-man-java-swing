package a2_1901040037.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Utils {
    public static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    public static void shutDown() {
        System.exit(0);
    }

    public static Object[] toArrayFieldValues(Object o, String[] accessFields){
        Field[] fields= o.getClass().getDeclaredFields();

        List<Field> matchingFields = Arrays.stream(fields)
                .filter(field -> List.of(accessFields).contains(field.getName())).toList();

        return matchingFields.stream().map(field -> {
           field.setAccessible(true);
            try {
                return field.get(o).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toArray();
    }
}
