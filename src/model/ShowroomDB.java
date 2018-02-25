package model;

public class ShowroomDB {

	public Showroom getShowroomById(int id) {
       	DBAccess db = new DBAccess();
       	db.createConnection();
       	Showroom sr = db.getShowroomById(id);
       	db.closeConnection();
		return sr;
	}

}
