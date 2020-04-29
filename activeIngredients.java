package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class activeIngredients extends ElementoCadenadeMando {
	private static final String ACTIVEING_TAGNAME = "activeIngredients";
	private static final String NAME_FIELD_TAGNAME = "name";

	public activeIngredients(ElementoCadenadeMando s) {
		super(s);

	}

	/*
	 * vale esta parte de código es la que vas se repite en todo el proyecto, por lo
	 * que lo más apropiado es que hagas una función solo esta parte y la metas en
	 * elementos. Sin embargo la vas a tener que modificar algo cuando lo intestes
	 * implementar aquí porque tiene que reconocer que es la clase indicada que
	 * tiene que leer.
	 * 
	 * A ver, a ver este método esta implementado en elementos, lo tienes que hacer
	 * aquí también porque tienes que saber que tipo de categoría es. Por lo que vas
	 * a pasarle el nombre y le vas a comprobar si esta bien y si no que pase a la
	 * siguiente.
	 */
	public StringBuffer readType(JsonReader reader, String type) throws IOException {
		if (type.equals(ACTIVEING_TAGNAME)) {
			return super.parseGeneral(reader, type);
		} else {
			if (sig != null) {
				return super.readType(reader, type);
			} else {
				reader.skipValue();
				System.err.println("Category " + type + " not processed.");
				return new StringBuffer();
			}
		}
	}

	// Parses the contents of a medicine.
	public String readEntry(JsonReader reader) throws IOException {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
		String medName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return medName;
	}
}
