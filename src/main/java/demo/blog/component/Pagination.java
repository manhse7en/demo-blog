package demo.blog.component;

public class Pagination {
	String sorted = "_id";
	String direction = "desc";
	int skip = 0;
	int limit = 10;

	public String getSorted() {
		return sorted;
	}

	public void setSorted(String sorted) {
		this.sorted = sorted;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
