package InputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InputDataProvider extends DataReader {
	
	@DataProvider
	public static List<String> getLoginData(int position){
		
		List<String> credentials = new ArrayList<>(); 
		try {
			String jsonContent = DataReader.getJsonLoginDataToMap();
			JsonArray jsonArray = (JsonArray) JsonParser.parseString(jsonContent);
			//JsonArray positiveJsonArray = (JsonArray) jsonArray;

			if (position <= jsonArray.size()) {
				JsonObject jsonObject = (JsonObject) jsonArray.get(position);

				String email = jsonObject.get("user_name").getAsString();
				String password = jsonObject.get("password").getAsString();

				credentials.add(email);
				credentials.add(password);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return credentials;
		
	}

}
