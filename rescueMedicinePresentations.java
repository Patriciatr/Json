package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

import conCadenadeMando.salud.isa.gsonMedDB.ElementoCadenadeMando;

public class rescueMedicinePresentations extends ElementoCadenadeMando {

	private static final String RESCUEMEDICINE_TAGNAME = "rescueMedicinePresentations";

	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String FIELD_SEPARATOR = " ; ";

	public rescueMedicinePresentations(ElementoCadenadeMando s) {
		super(s);
	}

	// Parses the contents of a medicine list.
	public StringBuffer readType(JsonReader reader, String type) throws IOException {
		if (type.equals(RESCUEMEDICINE_TAGNAME)) {
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
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(MEDREF_FIELD_TAGNAME)) {
				medRef = reader.nextString();
			} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
				aiRef = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				inhRef = reader.nextString();
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				dose = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
	}

}
