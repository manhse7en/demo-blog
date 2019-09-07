package demo.blog.graphql.custom;

import static graphql.ErrorType.DataFetchingException;

import java.util.List;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorHelper;
import graphql.language.SourceLocation;

@SuppressWarnings("serial")
public class CustomInvalidCursorException extends RuntimeException implements GraphQLError {

	CustomInvalidCursorException(String message) {
		this(message, null);
	}

	CustomInvalidCursorException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		return DataFetchingException;
	}

	@Override
	public boolean equals(Object o) {
		return GraphqlErrorHelper.equals(this, o);
	}

	@Override
	public int hashCode() {
		return GraphqlErrorHelper.hashCode(this);
	}

}

