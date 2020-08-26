package com.fon.tiks.util;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

public class ExistDBConnection {
	
	private static ExistDBConnection instance;
	public static final String DRIVER = "org.exist.xmldb.DatabaseImpl";
	public static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
	public static String COLLECTION = "/db/invoice";
	public static final String USERNAME = "admin";
	public static final String PASSWORD = "admin";
	
	public ExistDBConnection() {
		try {
			@SuppressWarnings("rawtypes")
			Class cl = Class.forName(DRIVER);
			Database database = (Database) cl.newInstance();
			database.setProperty("create-database", "true");
			DatabaseManager.registerDatabase(database);
		} catch (Exception e) {
			System.out.println("Neuspesna konekcija!!");
		}
	}
	
	public static ExistDBConnection getInstance() {
		if(instance == null)
			return new ExistDBConnection();
		return instance;
	}
	
	public Collection getOrCreateCollection() throws XMLDBException {
		return getOrCreateCollection(0);
	}

	private static Collection getOrCreateCollection(int pathSegmentOffset) throws XMLDBException {

		Collection col = DatabaseManager.getCollection(URI + COLLECTION, USERNAME, PASSWORD);
		if (col == null) {
			if (COLLECTION.startsWith("/")) {
				COLLECTION = COLLECTION.substring(1);
			}

			String pathSegments[] = COLLECTION.split("/");
			if (pathSegments.length > 0) {

				StringBuilder path = new StringBuilder();
				for (int i = 0; i <= pathSegmentOffset; i++) {
					path.append("/" + pathSegments[i]);
				}

				Collection start = DatabaseManager.getCollection(URI + path, USERNAME, PASSWORD);
				if (start == null) {
					// collection does not exist, so create
					String parentPath = path.substring(0, path.lastIndexOf("/"));
					Collection parent = DatabaseManager.getCollection(URI + parentPath, USERNAME, PASSWORD);
					CollectionManagementService mgt = (CollectionManagementService) parent
							.getService("CollectionManagementService", "1.0");
					col = mgt.createCollection(pathSegments[pathSegmentOffset]);
					col.close();
					parent.close();
				} else {
					start.close();
				}
			}
			return getOrCreateCollection(++pathSegmentOffset);
		} else {
			return col;
		}
	}

}
