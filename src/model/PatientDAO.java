package model;

import DTO.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO
{

	private Connection connect()  
	{
		
		Connection con = null; 
	 
	  try   
	  {     
		  Class.forName("com.mysql.jdbc.Driver");              
		  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/patient_data", "root", "");
		  
	  }   
	  catch (Exception e)   
	  {e.printStackTrace();} 
	 
	  return con;  
	  } 
	
	public boolean insertPatientDetails(String first_name,String last_name,String address,String email)//insert patient function
	{   
		String output = "";
	 try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)   
	   {
		   return false;
	   }
	 
	      String query = " insert into patient_det (address,first_name, last_name,email ) "
	    		  + " values ( ?, ?, ?,? )";

	      PreparedStatement preparedStmt = con.prepareStatement(query);


	      
	      preparedStmt.setString(1, first_name);
	      preparedStmt.setString(2, last_name);
	      preparedStmt.setString(3, address);
	      preparedStmt.setString(4, email);


	      preparedStmt.execute();
	      con.close();

	      output = "DAta Inserted";
		  return true;

	  }
	 catch (Exception e)

	 	{
		   output = "Error inserting";
		   System.err.println(e.getMessage());
		   return false;
		}

	}
	
	
public List<Patient> readPatientDetails()//list all patient details function
	{   
		String output = ""; 

		try   
		{   
			Connection con = connect(); 
		
			if (con == null)    
			{
				return null;
			} 
			
	 
	     output = "<table border=\"1\"><tr><th>Hospital Name</th><th>Address</th><th>Contact No</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			List<Patient> patients = new ArrayList<>();
	     	String query = "select * from patient_Data";
	     	Statement stmt = con.createStatement();
	     	ResultSet rs = stmt.executeQuery(query);


	 
	     while (rs.next())    
	     {     
	    	 String id = Integer.toString(rs.getInt("id"));
	    	 String firstName = rs.getString("first_name");
	    	 String lastName = rs.getString("last_name");
	    	 String address = rs.getString("address");
	    	 String Email = rs.getString("email");

			 Patient patient = new Patient(id,firstName, lastName, address ,Email);
			 patients.add(patient);


	         } 
	
	     con.close(); 
	 	return  patients;

	     }

		catch (Exception e)  
		{    
			return null;
		}



	  } 
	
	public boolean updatePatientDetails(String ID, Patient patient) // update the patient detail function
	{   
		String value = ""; 
	 
		try   
		{    
				Connection con = connect(); 
	 
				if (con == null)   
				{
					return false;
				} 
	 
	   String query = "UPDATE patient_det SET address=?,first_name=?,last_name=?,email=? WHERE id=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query);

	   preparedStmt.setString(1, patient.getFirst_name());
	   preparedStmt.setString(2, patient.getLast_name());
	   preparedStmt.setString(3, patient.getAddress());
	   preparedStmt.setString(4, patient.getEmail());
	   preparedStmt.setInt(5, Integer.parseInt(ID));

	 
	   preparedStmt.execute();   
	   con.close(); 
	 
	   value = "Update successfully";
	   return true;
	}   
		catch (Exception e)   
	{    
			value = "Error updating the Details.";    
			System.err.println(e.getMessage());   
			return false;
	}
	  
	}
	
	public boolean deletePatientDetails(String id) //delete patient details function
	{   
		String value = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return false;
	   }
	 
	   String query = "delete from patient_det where id=?";
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setInt(1, Integer.parseInt(id));
	 
	   preparedStmt.execute();    
	   con.close(); 
	 
	   value = "Deleted successfully"; 
	   return true;
	  }   
	  catch (Exception e)   
	  {    
		  value = "Error while deleting the Details.";    
		  System.err.println(e.getMessage());
		  return false;
	  }
	}


	public Patient getPatient(String id) //get details by id function
	{
		String value = "";

		try
		{
			Connection con = connect();

			if (con == null)
			{
				return null;
			}


			value = "<table border=\"1\"><tr><th>Hospital Name</th><th>Address</th><th>Contact No</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			Patient patient = null;
			String query = "select * from patient_det where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next())
			{
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String address = rs.getString("address");
				String Email = rs.getString("email");

				patient = new Patient(id, address, firstName, lastName,Email);
			}
			con.close();
			return  patient;
		}

		catch (Exception e)
		{
			return null;
		}

	}

}
