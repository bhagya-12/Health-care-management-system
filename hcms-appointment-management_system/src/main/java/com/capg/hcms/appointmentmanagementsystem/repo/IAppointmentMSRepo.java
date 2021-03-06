package com.capg.hcms.appointmentmanagementsystem.repo;




	import java.math.BigInteger;
	import java.time.LocalDateTime;

	import org.springframework.data.jpa.repository.JpaRepository;

	import com.capg.hcms.appointmentmanagementsystem.model.Appointment;


	public interface IAppointmentMSRepo extends JpaRepository<Appointment, BigInteger> {
	  
	  public Appointment getAppointmentByDateTimeAndTestId(LocalDateTime dateTime,String testId);

	 
	  
	}




