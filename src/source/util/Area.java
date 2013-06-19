package source.util;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.powerbot.script.lang.Locatable;
import org.powerbot.script.wrappers.Tile;

public class Area {
	private AreaFactory factory;
	protected final Polygon polygon;
	protected int plane = -1;
	private Tile[] tileArrayCache = null;

	public Area(AreaFactory factory, final Tile t1, final Tile t2) {
		this(factory, new Tile(Math.min(t1.getX(), t2.getX()), Math.min(t1.getY(),
				t2.getY()), t1.getPlane()), new Tile(Math.max(t1.getX(),
						t2.getX()), Math.min(t1.getY(), t2.getY()), t1.getPlane()),
						new Tile(Math.max(t1.getX(), t2.getX()), Math.max(t1.getY(),
								t2.getY()), t2.getPlane()), new Tile(Math.min(
										t1.getX(), t2.getX()), Math.max(t1.getY(), t2.getY()),
										t2.getPlane()));
	}

	public Area(AreaFactory factory, final Tile... bounds) {
		this.factory = factory;
		polygon = new Polygon();
		for (final Tile tile : bounds) {
			if (plane != -1 && tile.getPlane() != plane) {
				throw new RuntimeException("area does not support 3d");
			}
			plane = tile.getPlane();
			addTile(tile);
		}
	}

	public void addTile(final Tile t) {
		addTile(t.getX(), t.getY());
	}


	public void addTile(final int x, final int y) {
		polygon.addPoint(x, y);
		tileArrayCache = null;
	}

	public void translate(final int x, final int y) {
		polygon.translate(x, y);
		tileArrayCache = null;
	}

	public Rectangle getBounds() {
		return polygon.getBounds();
	}


	public int getPlane() {
		return plane;
	}

	public boolean contains(final int x, final int y) {
		return polygon.contains(x, y);
	}

	public boolean contains(final Locatable... locatables) {
		for (final Locatable loc : locatables) {
			if (loc == null) {
				continue;
			}
			final Tile tile = loc.getLocation();
			if (tile != null && plane == tile.getPlane()
					&& contains(tile.getX(), tile.getY())) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(final Locatable... locatables) {
		for (final Locatable loc : locatables) {
			if (loc == null || !contains(loc)) {
				return false;
			}
		}
		return true;
	}

	public Tile getCentralTile() {
		return polygon.npoints > 0 ? new Tile(
				(int) Math.round(avg(polygon.xpoints)),
				(int) Math.round(avg(polygon.ypoints)), plane) : null;
	}

	public Tile getNearest() {
		return getNearest(factory.getCtx().players.getLocal());
	}

	public Tile getNearest(final Locatable base) {
		final Tile[] tiles = getTileArray();
		Tile tile = null;
		double dist = Double.MAX_VALUE, temp;
		for (final Tile t : tiles) {
			temp = distance(base, t);
			if (tile == null || temp < dist) {
				dist = temp;
				tile = t;
			}
		}
		return tile;
	}

	public static double distance(final Locatable locatable1,
			final Locatable locatable2) {
		final Tile tile1 = locatable1.getLocation(), tile2 = locatable2
				.getLocation();
		return Math.sqrt(Math.pow(tile1.getX() - tile2.getX(), 2)
				+ Math.pow(tile1.getY() - tile2.getY(), 2));
	}

	public Tile[] getBoundingTiles() {
		final Tile[] bounding = new Tile[polygon.npoints];
		for (int i = 0; i < polygon.npoints; i++) {
			bounding[i] = new Tile(polygon.xpoints[i], polygon.ypoints[i],
					plane);
		}
		return bounding;
	}

	public Tile[] getTileArray() {
		if (tileArrayCache == null) {
			final Rectangle bounds = getBounds();
			final ArrayList<Tile> tiles = new ArrayList<Tile>(bounds.width
					* bounds.height);
			final int xMax = bounds.x + bounds.width, yMax = bounds.y
					+ bounds.height;
			for (int x = bounds.x; x < xMax; x++) {
				for (int y = bounds.y; y < yMax; y++) {
					if (contains(x, y)) {
						tiles.add(new Tile(x, y, plane));
					}
				}
			}
			tileArrayCache = tiles.toArray(new Tile[tiles.size()]);
		}
		return tileArrayCache;
	}

	private double avg(final int... nums) {
		long total = 0;
		for (int i : nums) {
			total += (long) i;
		}
		return (double) total / (double) nums.length;
	}
}
