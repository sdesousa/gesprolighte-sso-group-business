package af.cmr.indyli.gespro.light.business.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FilePropertiesLoader {

	 
	public static Properties loadProp() {
		try (InputStream input = FilePropertiesLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            //load a properties file from class path, inside static method  
            prop.load(input); 
            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return null;
	}
}
