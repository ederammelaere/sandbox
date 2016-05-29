package be.schaubroeck.bookshelf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hsqldb.Server;
import org.junit.BeforeClass;

import liquibase.Liquibase;
import liquibase.database.jvm.HsqlConnection;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;

public class BookshelfTestBase {

	private static final String CONNECTION_STRING = "jdbc:hsqldb:hsql://localhost/";
	private static final String USER_NAME = "SA";
	private static final String PASSWORD = "";
	
	private static final String CHANGE_LOG = "src/main/resources/liquibase/master.xml";
	
	private static SessionFactory sessionFactory;
	
	private static boolean doneOnce = false;
		
	@BeforeClass
	static public void onlyOnce() throws InterruptedException {
		if (doneOnce)
			return;
		doneOnce = true;
		
		try {
			Server.main(new String[]{"-database.0", "mem:demoshelf"});

			ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();
			Class.forName("org.hsqldb.jdbcDriver");

			Connection holdingConnection = getConnection();
			HsqlConnection hsconn = new HsqlConnection(holdingConnection);
			Liquibase liquibase = new Liquibase(CHANGE_LOG, resourceAccessor, hsconn);
			liquibase.dropAll();
			liquibase.update("test");
			hsconn.close();
		} catch (Exception ex) {
			throw new RuntimeException("Error during database initialization",
					ex);
		}
	}

	protected static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONNECTION_STRING, USER_NAME,
				PASSWORD);
	}

}
