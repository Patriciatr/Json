package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class medicines extends ElementoCadenadeMando {

	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	public medicines(ElementoCadenadeMando s) {
		super(s);

	}

	public StringBuffer readType(JsonReader reader, String type) throws IOException {
		if (type.equals(MEDICINES_TAGNAME)) {
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
