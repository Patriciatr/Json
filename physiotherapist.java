package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class physiotherapist extends ElementoCadenadeMando {

	private static final String PHYSIOTHE_TAGNAME = "physiotherapies";

	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGEN_FIELD_TAGNAME = "image";
	private static final String FIELD_SEPARATOR = " ; ";

	public physiotherapist(ElementoCadenadeMando s) {
		super(s);
	}

	public StringBuffer readType(JsonReader reader, String type) throws IOException {
		if (type.equals(PHYSIOTHE_TAGNAME)) {
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
		String medImage = null;

		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else if (name.equals(IMAGEN_FIELD_TAGNAME)) {
				medImage = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return medName + FIELD_SEPARATOR + medImage;
	}

}
