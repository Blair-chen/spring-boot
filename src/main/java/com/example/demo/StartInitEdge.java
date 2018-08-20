package com.example.demo;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.model.EdgeVo;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.telenav.modules.mapping.geography.Rectangle;
import com.telenav.modules.mapping.graph.Edge;
import com.telenav.modules.mapping.graph.Graph;
import com.telenav.tdk.framework.modules.logging.Logger;
import com.telenav.tdk.framework.modules.logging.LoggerFactory;
import com.telenav.tdk.framework.utilities.filesystem.File;

/**
 * 启动时开启一个线程加载graph文件
 *
 * @author
 */
@Component
@Order(value = 1)
public class StartInitEdge implements CommandLineRunner
{
	public static Map<Long, EdgeVo> level = new Hashtable<Long, EdgeVo>();

	public static RTree<EdgeVo, Geometry> levelZerotree = RTree.create();
	public static RTree<EdgeVo, Geometry> levelOnetree = RTree.create();
	public static RTree<EdgeVo, Geometry> levelThreetree = RTree.create();
	public static RTree<EdgeVo, Geometry> levelFourtree = RTree.create();
	public static RTree<EdgeVo, Geometry> levelTwotree = RTree.create();

	public void initEdgeToMap(final int level, final EdgeVo edge, final Rectangle bounds)
	{
		switch (level)
		{
			case 0:
				levelZerotree = levelZerotree.add(edge,
						Geometries.rectangle(bounds.getBottomLeft().getLatitude().asDegrees(),
								bounds.getBottomLeft().getLongitude().asDegrees(),
								bounds.getTopRight().getLatitude().asDegrees(),
								bounds.getTopRight().getLongitude().asDegrees()));

				break;
			case 1:
				levelOnetree = levelOnetree.add(edge,
						Geometries.rectangle(bounds.getBottomLeft().getLatitude().asDegrees(),
								bounds.getBottomLeft().getLongitude().asDegrees(),
								bounds.getTopRight().getLatitude().asDegrees(),
								bounds.getTopRight().getLongitude().asDegrees()));

				break;
			case 2:
				levelTwotree = levelTwotree.add(edge,
						Geometries.rectangle(bounds.getBottomLeft().getLatitude().asDegrees(),
								bounds.getBottomLeft().getLongitude().asDegrees(),
								bounds.getTopRight().getLatitude().asDegrees(),
								bounds.getTopRight().getLongitude().asDegrees()));

				break;
			case 3:
				levelThreetree = levelThreetree.add(edge,
						Geometries.rectangle(bounds.getBottomLeft().getLatitude().asDegrees(),
								bounds.getBottomLeft().getLongitude().asDegrees(),
								bounds.getTopRight().getLatitude().asDegrees(),
								bounds.getTopRight().getLongitude().asDegrees()));

				break;
			case 4:
				levelFourtree = levelFourtree.add(edge,
						Geometries.rectangle(bounds.getBottomLeft().getLatitude().asDegrees(),
								bounds.getBottomLeft().getLongitude().asDegrees(),
								bounds.getTopRight().getLatitude().asDegrees(),
								bounds.getTopRight().getLongitude().asDegrees()));

				break;

		}
	}

	@Override
	public void run(final String... args) throws Exception
	{
		final Logger logger = LoggerFactory.newLogger();
		final File file = new File("D:\\files and word\\NT-ANZ.graph");
		final Graph graph = Graph.forGraphResource(file, logger);
		final List<Edge> list = graph.edges().asList();
		for (int i = 0, len = list.size(); i < len; i++)
		{
			final EdgeVo vo = new EdgeVo();
			vo.setId(list.get(i).getIdentifierAsLong());
			vo.setLocation(list.get(i).getRoadShape().getLocations());
			level.put(list.get(i).getIdentifierAsLong(), vo);
			initEdgeToMap(list.get(i).getRoadFunctionalClass().getIdentifier(), vo,
					list.get(i).bounds());
		}

	}

}
