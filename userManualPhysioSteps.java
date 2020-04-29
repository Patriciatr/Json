package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class userManualPhysioSteps extends ElementoCadenadeMando {
	private static final String USERMANUALPHYSIO_TAGNAME = "userManualPhysioSteps";

	private static final String STEPTITLE_FIELD_NAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_NAME = "stepImage";
	private static final String STEPTEST_FIELD_NAME = "stepText";
	private static final String PHYSIOREF_FIELD_NAME = "physioRef";
	private static final String FIELD_SEPARATOR = " ; ";

	public userManualPhysioSteps(ElementoCadenadeMando s) {
		super(s);

	}

	public StringBuffer readType(JsonReader reader, String type) throws IOException {
		if (type.equals(USERMANUALPHYSIO_TAGNAME)) {
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
		String sTitle = null;
		String sImage = null;
		String sText = null;
		String physioRef = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPTITLE_FIELD_NAME)) {
				sTitle = reader.nextString();
			} else if (name.equals(STEPIMAGE_FIELD_NAME)) {
				sImage = reader.nextString();
			} else if (name.equals(STEPTEST_FIELD_NAME)) {
				sText = reader.nextString();
			} else if (name.equals(PHYSIOREF_FIELD_NAME)) {
				physioRef = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return sTitle + FIELD_SEPARATOR + sImage + FIELD_SEPARATOR + sText + FIELD_SEPARATOR + physioRef
				+ FIELD_SEPARATOR;
	}

}
