package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class medicinePresentantions extends ElementoCadenadeMando {
	private static final String MEDICINES_TAGNAME = "medicinePresentations";

	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String POSOLOGYREF_FIELD_TAGNAME = "posologyRef";
	private static final String FIELD_SEPARATOR = " ; ";

	public medicinePresentantions(ElementoCadenadeMando s) {
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
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		String posoRef = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(MEDREF_FIELD_TAGNAME)) {
				medRef = reader.nextString();
			} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
				aiRef = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				if (!(reader.peek() == JsonToken.BEGIN_ARRAY)) {
					inhRef = reader.nextString();
				} else if (reader.peek() == JsonToken.BEGIN_ARRAY) {
					reader.beginArray();
					while (reader.hasNext()) {
						// se hace para quitar el null del principio
						if (inhRef == null) {
							inhRef = reader.nextString() + ", ";
						} else {
							inhRef = inhRef + reader.nextString() + ", ";

						}
					}
					reader.endArray();
				} else {
					inhRef = "ERROR";
				}
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				if (!(reader.peek() == JsonToken.BEGIN_ARRAY)) {
					dose = reader.nextString();
				} else if (reader.peek() == JsonToken.BEGIN_ARRAY) {
					reader.beginArray();
					while (reader.hasNext()) {
						if (dose == null) {
							dose = reader.nextString() + ", ";
						} else {
							dose = dose + reader.nextString() + ", ";

						}
					}
					reader.endArray();
				} else {
					dose = "ERROR";
				}
			} else if (name.equals(POSOLOGYREF_FIELD_TAGNAME)) {
				if (!(reader.peek() == JsonToken.BEGIN_ARRAY)) {
					posoRef = reader.nextString();
				} else if (reader.peek() == JsonToken.BEGIN_ARRAY) {
					reader.beginArray();
					while (reader.hasNext()) {
						if (posoRef == null) {
							posoRef = reader.nextString() + ", ";
						} else {
							posoRef = posoRef + reader.nextString() + ", ";

						}
					}
					reader.endArray();
				} else {
					posoRef = "ERROR";
				}
			} else {
				reader.skipValue();
			}

		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose + FIELD_SEPARATOR
				+ posoRef;
	}

}
