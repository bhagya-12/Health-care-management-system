package com.capg.hcms.appointmentmanagementsystem.controller;


	import java.time.LocalDateTime;

	import javax.servlet.http.HttpServletRequest;

	import org.springframework.http.HttpStatus;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.ResponseStatus;
	import org.springframework.web.bind.annotation.RestControllerAdvice;

	import com.capg.hcms.appointmentmanagementsystem.exception.AppointmentAlreadyApprovedException;
	import com.capg.hcms.appointmentmanagementsystem.exception.AppointmentNotFoundException;
	import com.capg.hcms.appointmentmanagementsystem.exception.ErrorInfo;
	import com.capg.hcms.appointmentmanagementsystem.exception.SlotNotAvailableException;

	
	@RestControllerAdvice
	public class ErrorController {
		@ResponseStatus(code=HttpStatus.NOT_ACCEPTABLE)
		@ExceptionHandler(value= {AppointmentAlreadyApprovedException.class})
		public ErrorInfo handleAppointmentAlreadyApproved(AppointmentAlreadyApprovedException ex , HttpServletRequest req)
		{
			return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
		}

		@ResponseStatus(code=HttpStatus.NOT_FOUND)
		@ExceptionHandler(value= {AppointmentNotFoundException.class})
		public ErrorInfo AppointmentNotFound(AppointmentNotFoundException ex , HttpServletRequest req)
		{
			return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
		}
		@ResponseStatus(code=HttpStatus.NOT_FOUND)
		@ExceptionHandler(value= {SlotNotAvailableException.class})
		public ErrorInfo SlotNotAvailable(SlotNotAvailableException ex , HttpServletRequest req)
		{
			return new ErrorInfo(LocalDateTime.now(), ex.getMessage(),req.getRequestURI().toString());
		}
	}
		


