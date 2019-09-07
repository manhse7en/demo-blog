package demo.blog.repository.impl;

import java.io.IOException;
import java.util.Base64;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.blog.component.Pagination;
import demo.blog.config.MongoConfig;
import graphql.GraphQLException;

public class BaseRepositoryImpl {

	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	@SuppressWarnings("static-access")
	public Query paginate(Criteria criteria, Pagination pagination, int first, String after, int last, String before) {
		if (!after.equals("")) {
			byte[] decodedBytes = Base64.getDecoder().decode(after);
			after = new String(decodedBytes);
		} else if (!before.equals("")) {
			byte[] decodedBytes = Base64.getDecoder().decode(before);
			before = new String(decodedBytes);
		}
		Object val = null;
		Object _id = null;
		ObjectMapper mapper = new ObjectMapper();
		String nodeId = "";
		String nodeSort = "";
		if (!after.equals("")) {
			JsonNode node;
			try {
				node = mapper.readTree(after);
			} catch (JsonProcessingException e) {
				throw new GraphQLException("Lỗi cursor");
			} catch (IOException e) {
				throw new GraphQLException("Lỗi cursor");
			}
			nodeId = node.get("_id").asText();
			nodeSort = node.get("sorted").asText();
			_id = nodeId;
			if (node.get("_id").isNumber())
				_id = Integer.valueOf(nodeId);
			if (pagination.getSorted().equals("_id")) {
				if (pagination.getDirection().equals("desc"))
					criteria.and("_id").lt(_id);
				else
					criteria.and("_id").gt(_id);
			} else {
				if (node.get("sorted").isNumber()) {
					val = Float.valueOf(nodeSort);
				} else if (node.get("sorted").isBoolean())
					val = Boolean.valueOf(nodeSort);
				else
					val = String.valueOf(nodeSort);
				Criteria dir = new Criteria();
				if (pagination.getDirection().equals("desc"))
					dir = dir.where(pagination.getSorted()).lt(val);
				else
					dir = dir.where(pagination.getSorted()).gt(val);
				Criteria condition = new Criteria().orOperator(dir,
						Criteria.where(pagination.getSorted()).is(val).and("_id").gt(_id));

				criteria.andOperator(condition);
			}
		} else if (!before.equals("")) {
			JsonNode node;
			try {
				node = mapper.readTree(before);
			} catch (JsonProcessingException e) {
				throw new GraphQLException("Lỗi cursor");
			} catch (IOException e) {
				throw new GraphQLException("Lỗi cursor");
			}
			nodeId = node.get("_id").asText();
			nodeSort = node.get("sorted").asText();
			_id = nodeId;
			if (node.get("_id").isNumber())
				_id = Integer.valueOf(nodeId);
			if (pagination.getSorted().equals("_id")) {
				if (pagination.getDirection().equals("desc"))
					criteria.and("_id").gt(_id);
				else
					criteria.and("_id").lt(_id);
			} else {
				if (node.get("sorted").isNumber()) {
					val = Float.valueOf(nodeSort);
				} else if (node.get("sorted").isBoolean())
					val = Boolean.valueOf(nodeSort);
				else
					val = String.valueOf(nodeSort);
				Criteria dir = new Criteria();
				if (pagination.getDirection().equals("desc"))
					dir = dir.where(pagination.getSorted()).gt(val);
				else
					dir = dir.where(pagination.getSorted()).lt(val);
				Criteria condition = new Criteria().orOperator(dir,
						Criteria.where(pagination.getSorted()).is(val).and("_id").gt(_id));
				criteria.andOperator(condition);
			}
		}
		Query query = new Query(criteria);
		Sort sort = new Sort(Sort.Direction.ASC, pagination.getSorted());
		if (!pagination.getSorted().equals("_id"))
			sort.and(new Sort(Sort.Direction.ASC, "_id"));
		if (pagination.getDirection().equals("desc")) {
			sort = new Sort(Sort.Direction.DESC, pagination.getSorted());
			if (!pagination.getSorted().equals("_id"))
				sort.and(new Sort(Sort.Direction.ASC, "_id"));
		}
		query.with(sort);
		if (first > 0)
			query.limit(first + 1);
		else if (last > 0)
			query.limit(last + 1);
		else
			query.limit(1);
		return query;
	}

}
