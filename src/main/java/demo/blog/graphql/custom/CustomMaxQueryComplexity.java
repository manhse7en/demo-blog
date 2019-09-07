package demo.blog.graphql.custom;

import graphql.analysis.MaxQueryComplexityInstrumentation;

public class CustomMaxQueryComplexity extends MaxQueryComplexityInstrumentation {

	public CustomMaxQueryComplexity(int maxComplexity) {
		super(maxComplexity);
	}

}
