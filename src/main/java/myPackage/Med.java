package myPackage;

import java.sql.Connection;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.File;
import java.io.InputStream;
import javax.servlet.annotation.MultipartConfig;
import java.util.Properties;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

/**
 * This class manages operations related to medicines in the stock, including adding, viewing, searching, and updating medicine records.
 */
public class Med {
//	private static Logger demologger= LogManager.getLogger(Med.class.getName());
    /**
     * Adds a new medicine to the stock.
     * 
     * @param m the Medicine object containing details of the medicine to be added.
     * @throws ClassNotFoundException 
     */
    public void addMedicine(Medicine m) throws ClassNotFoundException {
        try (Connection conn = DBconnection.connect();
             PreparedStatement checkStmt = conn.prepareStatement(QueryUtil.checkMedicineQuery());
             PreparedStatement insertStmt = conn.prepareStatement(QueryUtil.insertMedicineQuery())) {

            checkStmt.setString(1, m.getName());
            checkStmt.setString(2, m.getSupplierName());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && (rs.getInt(1) + m.getQuantity()) > 100) {
                System.out.println("Duplicate entry detected for medicine: " + m.getName() + " from supplier: " + m.getSupplierName() + " with total quantity greater than 100.");
//                demologger.warn("Duplicate entry for medicine: " + m.getName() + " from supplier: " + m.getSupplierName());
                return;
            }

            insertStmt.setString(1, m.getType());
            insertStmt.setString(2, m.getName());
            insertStmt.setString(3, m.getCompany());
            insertStmt.setInt(4, m.getQuantity());
            insertStmt.setDate(5, m.getPurchaseDate());
            insertStmt.setDouble(6, m.getPurchaseRate());
            insertStmt.setDate(7, m.getExpiryDate());
            insertStmt.setString(8, m.getSupplierName());

            insertStmt.executeUpdate();
            insertStmt.close();
            System.out.println("Medicine added successfully.");
//            demologger.info("Medicine added successfully: " + m.getName() + " by " + m.getSupplierName());
        } catch (SQLException e) {
            System.out.print(e);
//            demologger.error("Error adding medicine: " + e.getMessage());
        }finally {
            DBconnection.disconnect();
        } 
    }

    /**
     * Views all medicines in the stock.
     * @throws ClassNotFoundException 
     */
    public List<Medicine> viewMedicines() throws ClassNotFoundException {
    	List<Medicine> med= new ArrayList<>();
        try (Connection conn = DBconnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QueryUtil.viewMedicineQuery())) {

//            System.out.println("Medicine Details:");
//            demologger.info("Viewing all medicines in stock.");
            while (rs.next()) {
            	int id = rs.getInt("med_id");
            	String type = rs.getString("med_type");
           	 String name =  rs.getString("med_name");
           	 String company = rs.getString("company");
           	 int quantity= rs.getInt("quantity");
           	 Date purchaseDate= rs.getDate("purchase_date");
           	 double purchaseRate= rs.getDouble("purchase_rate");
           	 Date expiryDate= rs.getDate("expiry_date");
           	 String supplierName= rs.getString("supplier_name");
           	 med.add(new Medicine(id,type,name,company,quantity,purchaseDate,purchaseRate,expiryDate,supplierName));
            }
        } catch (SQLException e) {
        	System.out.print(e);
//            demologger.error("Failed to view medicines.", e);
        }
        return med;
    }

    /**
     * Views the names of all suppliers in a tabular format.
     * @throws ClassNotFoundException 
     */
    public void viewSupplierNames() throws ClassNotFoundException {
        try (Connection conn = DBconnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QueryUtil.viewMedicineQuery())) {
       
            System.out.println("Supplier Names:");
//            demologger.info("Viewing supplier names:");
            System.out.println("-----------------------");
            System.out.printf("| %-20s |%n", "Supplier Name");
            System.out.println("+---------------------+");
            while (rs.next()) {
                System.out.printf("| %-20s |%n", rs.getString("supplier_name"));
//                demologger.info("| {} |", rs.getString("supplier_name"));
            }
            System.out.println("+---------------------+");
        } catch (SQLException e) {
        	System.out.print(e);
//            demologger.error("Failed to view supplier names.", e);
        }
    }

    /**
     * Searches for medicines by name or type and displays the results.
     * 
     * @param searchterm the search term to use for finding medicines.
     * @throws ClassNotFoundException 
     */
    public List<Medicine> searchAllMedicines(String searchterm) throws ClassNotFoundException {
        List<Medicine> medicines = new ArrayList<>();
        String query= QueryUtil.searchMedicineQuery();
        try (Connection conn = DBconnection.connect();
        		
             PreparedStatement pstmt = conn.prepareStatement(query) ){

            String searchPattern = "%" + searchterm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("med_id");
                String type = rs.getString("med_type");
                String name = rs.getString("med_name");
                String company = rs.getString("company");
                int quantity = rs.getInt("quantity");
                Date purchaseDate = rs.getDate("purchase_date");
                double purchaseRate = rs.getDouble("purchase_rate");
                Date expiryDate = rs.getDate("expiry_date");
                String supplierName = rs.getString("supplier_name");

                // Create Medicine object and add to list
                Medicine medicine = new Medicine( type, name, company, quantity, purchaseDate, purchaseRate, expiryDate, supplierName);
                medicine.setId(id);
                medicines.add(medicine);
            }

            if (medicines.isEmpty()) {
                System.out.println("No medicines available for: " + searchterm);
            }

        } catch (SQLException e) {
            System.out.print(e);
            // Log or handle exception appropriately
        } finally {
            DBconnection.disconnect(); // Make sure to close connection
        }

        return medicines;
    }

    /**
     * Retrieves a medicine record by its ID and displays the details.
     * 
     * @param id the ID of the medicine to be retrieved.
     * @return true if the medicine was found, false otherwise.
     * @throws ClassNotFoundException 
     */
    public Medicine getMedicineByID(int id) throws ClassNotFoundException {

    	Medicine med = null;
        try (Connection conn = DBconnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(QueryUtil.MedbyIdQuery());){
        		pstmt.setInt(1, id);
        		System.out.println(pstmt);
             ResultSet rs = pstmt.executeQuery() ;
            if (rs.next()) {
//                isFound = true;
//                demologger.info("Medicine found with ID: "+ id);
            	String type = rs.getString("med_type");
            	 String name =  rs.getString("med_name");
            	 String company = rs.getString("company");
            	 int quantity= rs.getInt("quantity");
            	 Date purchaseDate= rs.getDate("purchase_date");
            	 double purchaseRate= rs.getDouble("purchase_rate");
            	 Date expiryDate= rs.getDate("expiry_date");
            	 String supplierName= rs.getString("supplier_name");
            	 med = new Medicine(id, type, name,company,quantity,purchaseDate,purchaseRate,expiryDate,supplierName);
             
            } else {
                System.out.println("No such record found for id: " + id);
//                demologger.info("No record found for ID: "+ id);
            }
        } catch (SQLException e) {
        	System.out.print(e);
//            demologger.error("Failed to retrieve medicine by ID: "+ id, e);
        }

        return med;
    }
    public void deleteMedicineById(int id) throws ClassNotFoundException {
       
        
        try (Connection conn = DBconnection.connect();
        		PreparedStatement preparedStatement = conn.prepareStatement(QueryUtil.deleteMedicineQuery())) {
            preparedStatement.setInt(1, id);
             preparedStatement.execute();
            
            
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log exception appropriately
        }
        
        
    }

    /**
     * Updates an existing medicine record in the stock.
     * 
     * @param m the Medicine object containing updated details of the medicine.
     * @throws ClassNotFoundException 
     */
    public void updateMedicine(Medicine m) throws ClassNotFoundException {
        try (Connection conn = DBconnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(QueryUtil.updateMedicineQuery(m.getId()))) {
            pstmt.setString(1, m.getType());
            pstmt.setString(2, m.getName());
            pstmt.setString(3, m.getCompany());
            pstmt.setInt(4, m.getQuantity());
            pstmt.setDate(5, m.getPurchaseDate());
            pstmt.setDouble(6, m.getPurchaseRate());
            pstmt.setDate(7, m.getExpiryDate());
            pstmt.setString(8, m.getSupplierName());

            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Medicine record updated Successfully");
//            demologger.info("Medicine record updated successfully: ");
        } catch (SQLException e) {
        	System.out.print(e);
//            demologger.error("Failed to update medicine: "+ e);
        }
    }
    
    public List<Integer> getAllMedicineIds() throws ClassNotFoundException, SQLException {
        List<Integer> medicineIds = new ArrayList<>();
        String query = "SELECT id FROM medicine"; 
        try (Connection conn = DBconnection.connect(); 
             PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                medicineIds.add(resultSet.getInt("med_id"));
            }
        }
        return medicineIds;
    }
    
    public List<Medicine> getMedicinesByDateRange(Date fromDate, Date toDate) throws SQLException, ClassNotFoundException {
    	 Medicine medicine= null;
    	List<Medicine> medicines = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBconnection.connect(); 
            String query = "SELECT * FROM medicine WHERE purchase_date BETWEEN ? AND ?";
            stmt = conn.prepareStatement(query);
            stmt.setDate(1, fromDate);
            stmt.setDate(2, toDate);
            rs = stmt.executeQuery();

            while (rs.next()) {
                 int id = rs.getInt("med_id");
            	String type = rs.getString("med_type");
           	 String name =  rs.getString("med_name");
           	 String company = rs.getString("company");
           	 int quantity= rs.getInt("quantity");
           	 Date purchaseDate= rs.getDate("purchase_date");
           	 double purchaseRate= rs.getDouble("purchase_rate");
           	 Date expiryDate= rs.getDate("expiry_date");
           	 String supplierName= rs.getString("supplier_name");
           	 medicine = new Medicine(id, type, name,company,quantity,purchaseDate,purchaseRate,expiryDate,supplierName);
                medicines.add(medicine);
            }
        } finally {
            
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return medicines;
    }
    public List<Medicine> getMedicinesByNameAndSupplier(String name, String supplier) throws ClassNotFoundException {
        List<Medicine> medicines = new ArrayList<>();
        Medicine medicine = null;
        String sql = "SELECT * FROM medicines WHERE name = ? AND supplier = ?";
        
        try (Connection connection =DBconnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, name);
            statement.setString(2, supplier);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
            	 int id = rs.getInt("med_id");
                String type = rs.getString("med_type");
                String name1 = rs.getString("med_name");
                String company = rs.getString("company");
                int quantity = rs.getInt("quantity");
                Date purchaseDate = rs.getDate("purchase_date");
                double purchaseRate = rs.getDouble("purchase_rate");
                Date expiryDate = rs.getDate("expiry_date");
                String supplierName = rs.getString("supplier_name");
                medicine = new Medicine(id, type, name1,company,quantity,purchaseDate,purchaseRate,expiryDate,supplierName);
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }
    public boolean sendEmailWithAttachment(String to, String from,File file) {

        boolean flag = false;
        //smtp properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = "amoghp44";
        String password = "xrotugeyvuiwryhf";


        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
           

           

            MimeBodyPart part1 = new MimeBodyPart();
            part1.attachFile(file);

            MimeMultipart mimeMultipart = new MimeMultipart();
            
            mimeMultipart.addBodyPart(part1);

            message.setContent(mimeMultipart);

            Transport.send(message);
            flag = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;

    }

}


