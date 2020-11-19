package harvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String searchingField = reader.readLine();
		Class<?> richSoiIland = RichSoilLand.class;

		Field[] richFields = richSoiIland.getDeclaredFields();

		Map<String, List<Field>> fieldMaps = new HashMap<>();

		fieldMaps.put("public",new ArrayList<>());
		fieldMaps.put("protected",new ArrayList<>());
		fieldMaps.put("private",new ArrayList<>());
		fieldMaps.put("all",new ArrayList<>());

		for (Field field : richFields) {
			fieldMaps.get(Modifier.toString(field.getModifiers())).add(field);
			fieldMaps.get("all").add(field);
		}

		Map<String, String> output = new HashMap<>();


		output.put("public", collectToString(fieldMaps.get("public")));
		output.put("protected",collectToString(fieldMaps.get("protected")));
		output.put("private",collectToString(fieldMaps.get("private")));
		output.put("all",collectToString(fieldMaps.get("all")));

		while (!"HARVEST".equals(searchingField)){
			System.out.println(output.get(searchingField));

			searchingField = reader.readLine();
		}

	}

	public static String collectToString(List<Field> fields) {
		StringBuilder result = new StringBuilder();
		for (Field field : fields) {
			result.append(Modifier.toString(field.getModifiers())).append(" ").append(field.getType().getSimpleName()).append(" ").append(field.getName());
			result.append(System.lineSeparator());
		}

		return result.toString().trim();
	}
}
