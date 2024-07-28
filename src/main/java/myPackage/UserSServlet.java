package myPackage;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class UserSServlet
 */
public class UserSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Med userdao;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSServlet() {
//        super();
        // TODO Auto-generated constructor stub
    	this.userdao=new Med();    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
    	this.doGet(request, response);
    	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action= request.getServletPath();
	         
		switch(action) {
		
		case "/insert":
			try {
				insertUser(request,response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  break;
		case "/delete": try {
				deleteUser(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		       break;
		case "/searchByDate": try {
				getMedicinebyDate(request,response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   break;
		case "/update":try {
				updateUser(request,response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    break;
		case "/search": try {
				searchMedicine(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        break;
		case"/sendEmail":sendEmailWithAttachment(request,response);
		                   break;
			    default: try {
				viewMedicines(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    	//handle list
			    	break;
		}
	}
	
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, NumberFormatException, ClassNotFoundException{
	try {
		int id= Integer.parseInt(request.getParameter("med_id"));
		Medicine existingMedicine = userdao.getMedicineByID(id);
        if (existingMedicine == null) {
            request.setAttribute("message", "Medicine with ID " + id + " not found.");
           
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
            return;
        }
		 String type= request.getParameter("med_type");
		 String name = request.getParameter("med_name");
	      String company= request.getParameter("company");
		 int quantity= Integer.parseInt(request.getParameter("quantity"));
		 Date purchaseDate= parseDate(request.getParameter("purchaseDate"));
		 double purchaseRate = Double.parseDouble(request.getParameter("purchaseRate"));
		 Date expiryDate = parseDate(request.getParameter("expiryDate"));
		 String supplierName = request.getParameter("supplierName");
		
		 Medicine m = new Medicine( id,type,name,company,quantity,purchaseDate,purchaseRate,expiryDate,supplierName);
		 userdao.updateMedicine(m);
		
		 request.setAttribute("medicine", m);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
         dispatcher.forward(request, response);

		 }catch(ParseException e) {
				e.printStackTrace();
		        throw new ServletException("Error parsing date", e);
			}
	
	
	
}
	private void viewMedicines(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, ClassNotFoundException {
	    List<Medicine> listMedicine;
		try {
			listMedicine = userdao.viewMedicines();
			request.setAttribute("listMedicine", listMedicine);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
	}
	 private void searchMedicine(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException, ClassNotFoundException {
//		
		 String pattern=request.getParameter("query");
		 List<Medicine> searchlist = userdao.searchAllMedicines(pattern);
		 
		 request.setAttribute("listMedicine", searchlist);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	        dispatcher.forward(request, response);
		 
		 
	        }
	 private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
		    int id = Integer.parseInt(request.getParameter("med_id"));
		    userdao.deleteMedicineById(id);
		    request.setAttribute("medicine", id);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
            dispatcher.forward(request, response);
		}
	    
	private void insertUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, NumberFormatException, ClassNotFoundException,SQLException, ParseException{
	
				 String type= request.getParameter("med_type");
				 String name = request.getParameter("med_name");
			      String company= request.getParameter("company");
				 int quantity= Integer.parseInt(request.getParameter("quantity"));
				 Date purchaseDate= parseDate(request.getParameter("purchaseDate"));
				 double purchaseRate = Double.parseDouble(request.getParameter("purchaseRate"));
				 Date expiryDate = parseDate(request.getParameter("expiryDate"));
				 String supplierName = request.getParameter("supplierName");
				 List<Medicine> existingMedicines = userdao.getMedicinesByNameAndSupplier(name, supplierName);
				 int totalQuantity = quantity;
				    for (Medicine medicine : existingMedicines) {
				        totalQuantity += medicine.getQuantity();
				    }
				    if (totalQuantity > 100) {
				       
				        request.setAttribute("errorMessage", "Cannot add medicine. Total quantity exceeds 100.");
				        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				        dispatcher.forward(request, response);
				    } else {
				    	
				    
				 Medicine m = new Medicine( type,name,company,quantity,purchaseDate,purchaseRate,expiryDate,supplierName);
				 userdao.addMedicine(m);
				 request.setAttribute("medicine", m);
				 RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
		         dispatcher.forward(request, response);}
	}
	private void getMedicinebyDate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, NumberFormatException, ClassNotFoundException, ParseException{
		Date fromDate = parseDate(request.getParameter("fromDate"));
        Date toDate = parseDate(request.getParameter("toDate"));
        try {
        	 List<Medicine> medicinesByDate = userdao.getMedicinesByDateRange(fromDate, toDate);
        	 request.setAttribute("medicinesByDate", medicinesByDate);
             RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
             dispatcher.forward(request, response);
        
    } catch (Exception e) {
        e.printStackTrace(); 
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
	}
	
	 private Date parseDate(String dateStr) throws ParseException {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date utilDate = sdf.parse(dateStr);
	        return new Date(utilDate.getTime());
	    }
	 private void sendEmailWithAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 String to = request.getParameter("to");
		    String from = "amoghp44@gmail.com"; 		    
		    String downloadsDir = "C:\\Users\\admin\\Downloads";

	      
	        File latestFile = getLatestFileFromDir(downloadsDir);

	        
	            boolean b = userdao.sendEmailWithAttachment(to, from, latestFile);
	            if (b) {
	                System.out.println("Email is sent successfully");
	            } else {
	                System.out.println("Error in sending email !!");
	            }
	        

	            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		        dispatcher.forward(request, response);
	       
	      
	       
	 }
	 private File getLatestFileFromDir(String dirPath) {
	        File dir = new File(dirPath);
	        File[] files = dir.listFiles((d, name) -> name.startsWith("medicine-details") && name.endsWith(".pdf"));
	        if (files != null && files.length > 0) {
	            Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));
	            return files[0];
	        }
	        return null;
	    }
	/**
	 * @throws SQLException 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
}