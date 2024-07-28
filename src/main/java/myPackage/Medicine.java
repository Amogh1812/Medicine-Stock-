package myPackage;

import java.sql.Date;

/**
 * Represents a Medicine with its attributes such as type, name, company,
 * quantity, purchase date, purchase rate, expiry date, and supplier name.
 */
public class Medicine {
//	private static final Logger logger = LogManager.getLogger(Medicine.class.getName());

	private int id;
	private String type;
	private String name;
	private String company;
	private int quantity;
	private Date purchaseDate;
	private double purchaseRate;
	private Date expiryDate;
	private String supplierName;

	/**
	 * Constructs a new Medicine with all attributes including id.
	 *
	 * @param id           the id of the medicine
	 * @param type         the type of the medicine (e.g., Tablet, Syrup, Spray)
	 * @param name         the name of the medicine
	 * @param company      the company that manufactures the medicine
	 * @param quantity     the quantity of the medicine
	 * @param purchaseDate the purchase date of the medicine
	 * @param purchaseRate the purchase rate of the medicine
	 * @param expiryDate   the expiry date of the medicine
	 * @param supplierName the name of the supplier
	 */
	public Medicine(int id, String type, String name, String company, int quantity, Date purchaseDate,
			double purchaseRate, Date expiryDate, String supplierName) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.company = company;
		this.quantity = quantity;
		this.purchaseDate = purchaseDate;
		this.purchaseRate = purchaseRate;
		this.expiryDate = expiryDate;
		this.supplierName = supplierName;
//        logger.info("Medicine created with id: {}, type: {}, name: {}", id, type, name);
	}

	/**
	 * Constructs a new Medicine without id.
	 *
	 * @param type         the type of the medicine (e.g., Tablet, Syrup, Spray)
	 * @param name         the name of the medicine
	 * @param company      the company that manufactures the medicine
	 * @param quantity     the quantity of the medicine
	 * @param purchaseDate the purchase date of the medicine
	 * @param purchaseRate the purchase rate of the medicine
	 * @param expiryDate   the expiry date of the medicine
	 * @param supplierName the name of the supplier
	 */
	public Medicine(String type, String name, String company, int quantity, Date purchaseDate, double purchaseRate,
			Date expiryDate, String supplierName) {
		this.type = type;
		this.name = name;
		this.company = company;
		this.quantity = quantity;
		this.purchaseDate = purchaseDate;
		this.purchaseRate = purchaseRate;
		this.expiryDate = expiryDate;
		this.supplierName = supplierName;
//		logger.info("Medicine created with type: {}, name: {}", type, name);
	}

	/**
	 * Gets the id of the medicine.
	 *
	 * @return the id of the medicine
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the medicine.
	 *
	 * @param id the new id of the medicine
	 */
	public void setId(int id) {
		this.id = id;
//		logger.info("Set id for medicine: {} to {}", name, id);
	}

	/**
	 * Gets the type of the medicine.
	 *
	 * @return the type of the medicine
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the medicine.
	 *
	 * @param type the new type of the medicine
	 */
	public void setType(String type) {
		this.type = type;
//		logger.info("Set type for medicine: {} to {}", name, type);
	}

	/**
	 * Gets the name of the medicine.
	 *
	 * @return the name of the medicine
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the medicine.
	 *
	 * @param name the new name of the medicine
	 */
	public void setName(String name) {
		this.name = name;
//		logger.info("Set name for medicine with id: {} to {}", id, name);
	}

	/**
	 * Gets the company that manufactures the medicine.
	 *
	 * @return the company that manufactures the medicine
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Sets the company that manufactures the medicine.
	 *
	 * @param company the new company that manufactures the medicine
	 */
	public void setCompany(String company) {
		this.company = company;
//		logger.info("Set company for medicine: {} to {}", name, company);
	}

	/**
	 * Gets the quantity of the medicine.
	 *
	 * @return the quantity of the medicine
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the medicine.
	 *
	 * @param quantity the new quantity of the medicine
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
//		logger.info("Set quantity for medicine: {} to {}", name, quantity);
	}

	/**
	 * Gets the purchase date of the medicine.
	 *
	 * @return the purchase date of the medicine
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * Sets the purchase date of the medicine.
	 *
	 * @param purchaseDate the new purchase date of the medicine
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
//		logger.info("Set purchase date for medicine: {} to {}", name, purchaseDate);
	}

	/**
	 * Gets the purchase rate of the medicine.
	 *
	 * @return the purchase rate of the medicine
	 */
	public double getPurchaseRate() {
		return purchaseRate;
	}

	/**
	 * Sets the purchase rate of the medicine.
	 *
	 * @param purchaseRate the new purchase rate of the medicine
	 */
	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
//		logger.info("Set purchase rate for medicine: {} to {}", name, purchaseRate);
	}

	/**
	 * Gets the expiry date of the medicine.
	 *
	 * @return the expiry date of the medicine
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiry date of the medicine.
	 *
	 * @param expiryDate the new expiry date of the medicine
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
//		logger.info("Set expiry date for medicine: {} to {}", name, expiryDate);
	}

	/**
	 * Gets the name of the supplier.
	 *
	 * @return the name of the supplier
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * Sets the name of the supplier.
	 *
	 * @param supplierName the new name of the supplier
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
//		logger.info("Set supplier name for medicine: {} to {}", name, supplierName);
	}
}

