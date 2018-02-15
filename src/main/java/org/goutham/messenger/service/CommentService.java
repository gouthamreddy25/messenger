package org.goutham.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.goutham.messenger.database.DatabaseClass;
import org.goutham.messenger.model.Comment;
import org.goutham.messenger.model.ErrorMessage;
import org.goutham.messenger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Comment> getAllComment(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 500, "https://www.youtube.com/");
		Response response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException();
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		return comment;
	}

	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> Comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		Comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment removeComment(long messageId, long CommentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(CommentId);
	}

}
