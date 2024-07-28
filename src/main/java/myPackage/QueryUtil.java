package myPackage;

public class QueryUtil {
//	private static Logger demologger = LogManager.getLogger(DBconnection.class.getName());

	/**
	 * Constructs the SQL query to insert a new medicine record into the database.
	 *
	 * @return the SQL query string for inserting a new medicine
	 */
	public static String insertMedicineQuery() {
//		demologger.debug("Constructed SQL query for inserting a new medicine.");
		return "INSERT INTO medicine (med_type, med_name, company, quantity, purchase_date, purchase_rate, expiry_date, supplier_name) VALUES (?,?,?,?,?,?,?,?)";

	}

	/**
	 * Constructs the SQL query to check the total quantity of a specific medicine
	 * from a specific supplier.
	 *
	 * @return the SQL query string for checking the quantity of a specific medicine
	 *         from a specific supplier
	 */
	public static String checkMedicineQuery() {
//		demologger.debug(
//				"Constructed SQL query for checking the quantity of a specific medicine from a specific supplier.");
		return "SELECT SUM(quantity) FROM medicine WHERE med_name = ? AND supplier_name = ?";
	}

	/**
	 * Constructs the SQL query to retrieve all medicine records from the database.
	 *
	 * @return the SQL query string for viewing all medicines
	 */
	public static String viewMedicineQuery() {
//		demologger.debug("Constructed SQL query for viewing all medicines.");
		return "SELECT * FROM medicine";
	}

	/**
	 * Constructs the SQL query to search for medicines by name or type.
	 *
	 * @return the SQL query string for searching medicines by name or type
	 */
	public static String searchMedicineQuery() {
//		demologger.debug("Constructed SQL query for searching medicines by name or type.");
		return "SELECT * FROM medicine WHERE med_name LIKE ? OR med_type LIKE ?";
	}

	/**
	 * Constructs the SQL query to retrieve a medicine record by its ID.
	 *
	 * @param medid the ID of the medicine to retrieve
	 * @return the SQL query string for retrieving a medicine by its ID
	 */
	public static String MedbyIdQuery() {
//		demologger.debug("Constructed SQL query for retrieving a medicine by its ID.");
		return "SELECT * FROM medicine WHERE med_id = ?";
	}

	/**
	 * Constructs the SQL query to update a medicine record by its ID.
	 *
	 * @param medid the ID of the medicine to update
	 * @return the SQL query string for updating a medicine by its ID
	 */
	public static String updateMedicineQuery(int medid) {
//		demologger.debug("Constructed SQL query for updating a medicine by its ID.");
		return "UPDATE medicine SET med_type = ?, med_name = ?, company = ?, quantity = ?, purchase_date = ?, purchase_rate = ?, expiry_date = ?, supplier_name = ? WHERE med_id = "
				+ medid;
	}
	
	public static String deleteMedicineQuery() {
		return "DELETE FROM medicine WHERE med_id = ?";
	}
	
}
