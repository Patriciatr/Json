package conCadenadeMando.salud.isa.gsonMedDB;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try {
			DatabaseJSonReader dbjp = new DatabaseJSonReader(new medicines(new activeIngredients(new physiotherapist(
					new inhalers(new posologies(new medicinePresentantions(new rescueMedicinePresentations(
							new userManualPhysioSteps(new userManualSteps((null)))))))))));

			try {
				System.out.println(dbjp.parse("./datos.json", dbjp));
			} finally {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
