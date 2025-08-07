package maheshacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public List<HashMap<String, String>> getJsonToMap () throws IOException {
		// Read JSON to String
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "src\\test\\java\\maheshacademy\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		
		// String to HashMap using jacksonBind
		
		ObjectMapper map = new ObjectMapper();
		List<HashMap<String,String>> data =  map.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){} );
		return data;
	}

}
