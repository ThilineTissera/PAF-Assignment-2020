package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import DTO.Patient;
import DTO.Response;
import Util.JsonConverter;
import model.PatientDAO;



import java.util.List;

@Path("/Patient")
public class PatientService 
{

	PatientDAO patientRepo = new PatientDAO();
	
	@POST //add patient to the System
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertPatientDetails(@FormParam("address")String address,
										 @FormParam("first_name")String first_name,
										 @FormParam("last_name")String last_name,
										 @FormParam("email")String email) 
	{
		return new Response(patientRepo.insertPatientDetails(address,first_name,last_name,email));
	}


	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response readPatientDetails(@PathParam("id") String id)
	{

		Patient patient = patientRepo.getPatient(id);
		if(patient !=null)
		{
			return javax.ws.rs.core.Response.status(200).type(MediaType.APPLICATION_JSON)
					.entity(patient).build();
		}else
		{
			return javax.ws.rs.core.Response.status(404).build();
		}

	}
	 
	
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	public String readAllPatientDetails()
	 {

		 List<Patient> patients = patientRepo.readPatientDetails();
		 JsonConverter converter = new JsonConverter();
		 String output = converter.convertToJson(patients);

		 return  output;

	 }
	 
	 
	
	 @Path("/{id}")
	 @Consumes(MediaType.APPLICATION_JSON) 
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response updatePatientDetails(@PathParam("id") String id, Patient patient)
	 {

		 return new Response(patientRepo.updatePatientDetails(id, patient));
		 
	 } 
	 
	 
	 @Path("/{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response deletePatientDetails(@PathParam("id") String id) 
	 {

		 return new Response(patientRepo.deletePatientDetails(id));
	 }
}
