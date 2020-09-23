package com.capg.hcms.appointmentmanagementsystem.service;

    import java.math.BigInteger;
	import java.time.LocalDateTime;
	import java.time.LocalTime;
	import java.util.List;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
    import com.capg.hcms.appointmentmanagementsystem.exception.AppointmentAlreadyApprovedException;
	import com.capg.hcms.appointmentmanagementsystem.exception.AppointmentNotFoundException;
	import com.capg.hcms.appointmentmanagementsystem.exception.SlotNotAvailableException;
	import com.capg.hcms.appointmentmanagementsystem.model.Appointment;
	import com.capg.hcms.appointmentmanagementsystem.repo.IAppointmentMSRepo;

	

	@Service
	public class AppointmentServiceImpl implements IAppointmentMSService {

		@Autowired
		IAppointmentMSRepo appointmentRepo;
		
		
		@Override
		public Appointment makeAppointment(Appointment appointment) {
		
			LocalTime time=appointment.getDateTime().toLocalTime();

			
		    if ((appointmentRepo.getAppointmentByDateTimeAndTestId(appointment.getDateTime(),
		    		appointment.getTestId())!=null))
		    	
		    {
				throw new SlotNotAvailableException("This slot is not available");
			}
		   
		   // System.out.println(appointment);
			Appointment app=appointmentRepo.save(appointment);
		return app;
		}
		
		
		@Override
		public Appointment getAppointment(BigInteger appointmentId) {

			if (!appointmentRepo.existsById(appointmentId)) {
				throw new AppointmentNotFoundException("Appointment with id " + appointmentId + " not found");
			}
			return appointmentRepo.getOne(appointmentId);
		}
		
		
	    
		@Override
		public List<Appointment> getAllAppointments() {

			if (appointmentRepo.findAll().isEmpty()) {
				throw new AppointmentNotFoundException("Appointment list is empty");
			}
			List<Appointment> allAppointments = appointmentRepo.findAll();
			return allAppointments;
		}
		
		

		@Override
		public Appointment approveAppointment(Appointment appointment, boolean status) {

			if (appointment.isApproved()) {
				throw new AppointmentAlreadyApprovedException(
						"Appointment with Id :" + appointment.getAppointmentId() + " is Already Approved");
			}

			appointment.setApproved(status);
			return appointmentRepo.save(appointment);
		}
		
		
	    
	    @Override
		public boolean removeAppointmentById(BigInteger appointmentId) {
	    	
	    	if(!appointmentRepo.existsById(appointmentId)) {
	    		throw new AppointmentNotFoundException("Appointment with id: "+appointmentId+" not found");
	    	}
			appointmentRepo.deleteById(appointmentId);
			return true;
		}
	    
	}



