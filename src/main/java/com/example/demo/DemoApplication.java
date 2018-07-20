package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.utils.EdgeUtil;
import com.telenav.modules.mapping.graph.Edge;
import com.telenav.modules.mapping.graph.Graph;
import com.telenav.tdk.framework.modules.logging.Logger;
import com.telenav.tdk.framework.modules.logging.LoggerFactory;
import com.telenav.tdk.framework.utilities.filesystem.File;

@SpringBootApplication
public class DemoApplication
{

	public static void main(final String[] args) throws Exception
	{
		final Logger logger = LoggerFactory.newLogger();
		final File file = new File("D:\\files and word\\NT-ANZ.graph");
		final Graph graph = Graph.forGraphResource(file, logger);
		final long i = 0;
		for (final Edge edge : graph.edges().asList())
		{
			EdgeUtil.initEdgeToMap(edge.getRoadFunctionalClass().getIdentifier(), edge);
		}
		SpringApplication.run(DemoApplication.class, args);
	}
}
