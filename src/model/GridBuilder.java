package model;

import java.util.List;

import util.AnimatorBuilder;

public interface GridBuilder {
	public List<RoadList> RowBuilder();
	public List<RoadList> ColumnBuilder();
	public GridBuilder getGrid();
}
