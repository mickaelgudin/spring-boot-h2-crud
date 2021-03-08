

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.spring.crud.demo.utils.HelperUtil;

public class TestHelperUtil {
	public TestHelperUtil() {
	}
	
	
	@Test
	public void testGetObjectFromJson() {
		String json = "{\"name\":\"test\"}";
		InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
		//JSONObject objet = HelperUtil.getObjectFromJson(stream);
		//assertEquals("test", objet.get("name"));
	}
	
	@Test
	public void testGetResponseFromUrl() {
		//JSONObject objet = HelperUtil.getResponseFromUrl("https://jsonplaceholder.typicode.com/posts/1", false);
		//assertEquals("1", String.valueOf(objet.get("id")) );
	}

}
