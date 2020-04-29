package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

/**
 * Created by Patriciatr
 */
public class DatabaseJSonReader {

	ElementoCadenadeMando cadenademando;

	public DatabaseJSonReader(ElementoCadenadeMando cm) {
		cadenademando = cm;
	}

	public StringBuffer readType(JsonReader reader, String jsonFileName) throws IOException {
		return cadenademando.readType(reader, jsonFileName);
	}

	public String parse(String jsonFileName, DatabaseJSonReader ccm) throws IOException {

		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

		reader.beginObject();
		StringBuffer readData = new StringBuffer();
		/*
		 * esto era lo que no cumplía con el método de abierto-cerrado.
		 */
		while (reader.hasNext()) {

			String name = reader.nextName();
			readData.append(ccm.readType(reader, name).append("\n"));

		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}

}
