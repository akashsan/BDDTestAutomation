package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Load_Properties {

	static Properties prop = null;

	public static Properties getProp() throws IOException {
		if (prop == null) {
			prop = new Properties();
			InputStream input = new FileInputStream("src/test/resources/properties/credentials.properties");
			prop.load(input);

		}
		return prop;
	}

}
