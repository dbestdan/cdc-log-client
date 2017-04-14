import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.util.Date;

public class LogWriter implements Config{

	private long sessionEndTime =0L;
	private OutputStream out = null;
	private Socket logSocketClient;
	private int portNumber = 0;
	private DataInputStream input;
	private DataOutputStream output;
	
	public LogWriter(long sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
		
		
		String fileName = "Changed_Data_Log_Based_" + dateFormat.format(new Date());
		try {
			out = new FileOutputStream(fileName, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void writeLogFile() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String clientName = System.getProperty("clientName");
			portNumber = ports.get(clientName).intValue();			
			logSocketClient = new Socket("pcnode2",portNumber);
			output = new DataOutputStream(logSocketClient.getOutputStream());
			input = new DataInputStream(logSocketClient.getInputStream());
			
			if(logSocketClient!=null && output!=null && input!=null) {
				System.out.println("==================================================================");
				System.out.println(clientName +": Socket connection Successful ------------------------");
				System.out.println("==================================================================");

				while (System.currentTimeMillis()<sessionEndTime) {
					byte[] buffer = new byte[8*1024];
					int bytesRead;
					while((bytesRead =input.read(buffer))!=-1) {
						out.write(buffer, 0, bytesRead);
					}				
					
					out.flush();
					}
				
				
			}
			
			
			
		} catch ( IOException e) {
			e.printStackTrace();
		}

	}
}
