package Game.level;

import Game.entity.util.Vector2i;

public class Node {

	public Vector2i tile;
	public Node parent;
	public double fCost, gCost, hCost;
	//hCost - estimate of Distance to finish
	//gCost - Distance from node to node from start
	//fCost - Combination of hCost and gCost1
	
	public Node(Vector2i tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost;
	}
}