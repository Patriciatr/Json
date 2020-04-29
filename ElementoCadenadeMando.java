package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

import conCadenadeMando.salud.isa.gsonMedDB.ElementoCadenadeMando;

public abstract class ElementoCadenadeMando {

	protected ElementoCadenadeMando sig;

	public ElementoCadenadeMando(ElementoCadenadeMando s) {
		sig = s;
	}

	public StringBuffer readType(JsonReader reader, String jsonFileName) throws IOException {
		return sig.readType(reader, jsonFileName);
	}

	/*
	 * esta es la parte común de todas las clases concretas
	 */
	public StringBuffer parseGeneral(JsonReader reader, String type) throws IOException {
		StringBuffer medicineData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			medicineData.append(readEntry(reader)).append("\n");
			reader.endObject();
		}
		medicineData.append("\n");
		reader.endArray();
		return medicineData;
	}

	public abstract String readEntry(JsonReader reader) throws IOException;
}
