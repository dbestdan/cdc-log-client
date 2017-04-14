import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Config {

	public final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	public final static Map<String,Integer> ports = new HashMap<String,Integer>(){{
		put("client1",9999);
		put("client2",9998);
	}};
	
}
