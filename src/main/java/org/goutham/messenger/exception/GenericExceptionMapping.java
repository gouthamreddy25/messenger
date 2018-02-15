package org.goutham.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;


import org.goutham.messenger.model.ErrorMessage;

public class GenericExceptionMapping {

	public class DataNotFoundExceptionMapper implements ExceptionMapper<Throwable> {

		@Override
		public Response toResponse(Throwable ex) {
			ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "https://www.youtube.com/");
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(errorMessage)
					.build();

		}

	}
}
