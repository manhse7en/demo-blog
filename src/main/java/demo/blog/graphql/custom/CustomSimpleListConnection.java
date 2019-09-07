package demo.blog.graphql.custom;

import static graphql.Assert.assertNotNull;
import static java.util.Base64.getEncoder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import graphql.PublicApi;
import graphql.relay.Connection;
import graphql.relay.DefaultConnection;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.DefaultEdge;
import graphql.relay.DefaultPageInfo;
import graphql.relay.Edge;
import graphql.relay.PageInfo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@PublicApi
public class CustomSimpleListConnection<T> implements DataFetcher<Connection<T>> {
	private final List<T> data;
	private final String sorted;

	public CustomSimpleListConnection(List<T> data, String sorted) {
		this.data = assertNotNull(data, " data cannot be null");
		this.sorted = sorted;
	}

	private List<Edge<T>> buildEdges() {
		ObjectMapper mapper = new ObjectMapper();
		List<Edge<T>> edges = new ArrayList<>();
		for (T object : data) {
			JsonNode node = mapper.convertValue(object, JsonNode.class);
			JsonNode tmp = JsonNodeFactory.instance.objectNode();
			((ObjectNode) tmp).put("_id", node.get("_id"));
			((ObjectNode) tmp).put("sorted", node.get(this.sorted));
			String cursor = tmp.toString();
			edges.add(new DefaultEdge<>(object, new DefaultConnectionCursor(createCursor(cursor))));
		}
		return edges;
	}

	@Override
	public Connection<T> get(DataFetchingEnvironment environment) {

		List<Edge<T>> edges = buildEdges();

		if (edges.size() == 0) {
			return emptyConnection();
		}

		Number _first = environment.getArgument("first");
		Number _last = environment.getArgument("last");

		Integer first = _first.intValue();
		Integer last = _last.intValue();
		boolean hasNextPage = false;
		boolean hasPreviousPage = false;

		if (first > 0 && edges.size() > first) {
			hasNextPage = true;
			edges.remove(edges.size() - 1);

		} else if (last > 0 && edges.size() > last) {
			hasPreviousPage = true;
			edges.remove(edges.size() - 1);
		}

		if (edges.isEmpty()) {
			return emptyConnection();
		}

		Edge<T> firstEdge = edges.get(0);
		Edge<T> lastEdge = edges.get(edges.size() - 1);

		PageInfo pageInfo = new DefaultPageInfo(firstEdge.getCursor(), lastEdge.getCursor(), hasPreviousPage,
				hasNextPage);

		return new DefaultConnection<>(edges, pageInfo);
	}

	private Connection<T> emptyConnection() {
		PageInfo pageInfo = new DefaultPageInfo(null, null, false, false);
		return new DefaultConnection<>(Collections.emptyList(), pageInfo);
	}

	private String createCursor(String offset) {
		byte[] bytes = (offset).getBytes(StandardCharsets.UTF_8);
		return getEncoder().encodeToString(bytes);
	}
}

