package model;


import java.util.LinkedList;
import java.util.List;

import util.AnimatorBuilder;

public class SimpleGrid implements GridBuilder{
	private List<RoadList> _rowsList;
	private List<RoadList> _colsList;
	private TimeServer _server;
	private int _rows;
	private int _columns;
	AnimatorBuilder _builder;
	
	SimpleGrid(TimeServer server, int rows, int columns, AnimatorBuilder builder) {
		_server = server;
		_rows = rows;
		_columns = columns;
		_rowsList = new LinkedList<RoadList>();
		_colsList = new LinkedList<RoadList>();
		_builder = builder;
	}
	
	@Override
	public List<RoadList> RowBuilder() {
		boolean eastToWest = true;
		//boolean northToSouth = false;
		for (int i = 0; i < _rows; i ++) {
			
			RoadList rrl = new RoadList();
			for (int j = 0; j < _columns; j ++) {
				Light rowLight = new Light(_server, _builder, i, j);
				Road rowRoad = new Road(_server, _builder, rrl, rrl.size(), eastToWest, i, j, rowLight);
				rrl.addRoad(rowRoad);
				_rowsList.add(rrl);
				_builder.addHorizontalRoad(rowRoad, i, j, false);
				
			}	
		}
		
		for(RoadList rowrl : _rowsList) {
			Source s1 = new Source(_server, rowrl.getRoad(0));
			_server.enqueue(_server.currentTime() + MP.getCarEntryRate(), s1);
		}
		
		return _rowsList;
	}

	@Override
	public List<RoadList> ColumnBuilder() {
		
		boolean eastToWest = false;
		
		for (int j = _rows - 1; j >= 0; j --) {
			RoadList rl = new RoadList();
			for (int i = _columns - 1; i >= 0; i --) {
				Light l = new Light(_server, _builder, i, j);
				Road r = new Road(_server, _builder, rl, rl.size(), eastToWest, i, j, l);
				rl.addRoad(r);
				_colsList.add(rl);
				_builder.addVerticalRoad(r, i, j, false);
				
			}
		}
		
		for(RoadList rl : _colsList) {
			
			Road road = (Road)rl.getIterable().iterator().next();
			if (road.getPosition() == 0) {
				Source s2 = new Source(_server, road);
				_server.enqueue(_server.currentTime() + MP.getCarEntryRate(), s2);
			}
		}
		
		return _colsList;
	}
	
	public GridBuilder getGrid() {
		this.RowBuilder();
		this.ColumnBuilder();
		return this;
	}

}
