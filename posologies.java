package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class posologies extends ElementoCadenadeMando {
	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String DESCRIPTION_FIELD_TAGNAME = "description";

	public posologies(ElementoCadenadeMando s) {
		super(s);

	}

	public StringBuffer readType(JsonReader reader, String type) throws IOException {
		if (type.equals(POSOLOGIES_TAGNAME)) {
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
			if (name.equals(DESCRIPTION_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return medName;
	}
}
