package DTO;


public class Patient
{
	protected String id;
	protected String first_name;
	protected String last_name;
	protected String address;
	protected String email;
	
	public Patient(String id, String first_name, String last_name, String address, String email) 
	{
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.email = email;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getFirst_name() 
	{
		return first_name;
	}

	public void setFirst_name(String first_name) 
	{
		this.first_name = first_name;
	}

	public String getLast_name() 
	{
		return last_name;
	}

	public void setLast_name(String last_name)
	{
		this.last_name = last_name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	

	
}
