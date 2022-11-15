package InputData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public class DataReader {
	
	public static String getJsonLoginDataToMap() throws IOException {

		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\InputData\\DataFiles\\LoginData.json"), StandardCharsets.UTF_8);
		return jsonContent;

	}
}
