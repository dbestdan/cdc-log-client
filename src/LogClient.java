import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LogClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long runDuration = Long.parseLong(System.getProperty("runDuration"))*60000L;
		long sessionEndTime = System.currentTimeMillis()+runDuration;
		LogWriter logWriter = new LogWriter(sessionEndTime);
		logWriter.writeLogFile();

	}
	
	public static Connection getConnection(){
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", "benchmarksql");
		connectionProps.put("password", "benchmarksql");
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://pcnode2:5432/benchmarksql", connectionProps);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
