package de.fhpotsdam.unfolding.examples.marker;

import processing.core.PApplet;
import codeanticode.glgraphics.GLConstants;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;

/**
 * Simple example with three markers, managed by the MarkerManager. Displays two point markers, and one line marker.
 * 
 * Managing and drawing the markers is handled internally, with all markers cut-off at the border of the map.
 * Unfolding's simple marker (SimpleMarker, SimpleLinesMarker, and SimplePolygonMarker) provide some styling
 * functionality. For more customization you need to create your own Marker classes.
 */
public class SimpleMarkerManagerApp extends PApplet {

	UnfoldingMap map;

	Location berlinLocation = new Location(52.5f, 13.4f);
	Location mexicoCityLocation = new Location(19.4f, -99.1f);

	public void setup() {
		size(800, 600, GLConstants.GLGRAPHICS);

		map = new UnfoldingMap(this, "map", 50, 50, 700, 500);
		map.zoomToLevel(3);
		map.panTo(new Location(40f, -42f));
		MapUtils.createDefaultEventDispatcher(this, map);

		SimplePointMarker berlinMarker = new SimplePointMarker(berlinLocation);
		berlinMarker.setColor(color(0, 200, 0, 100));
		berlinMarker.setRadius(4);
		SimplePointMarker mexicoCityMarker = new SimplePointMarker(mexicoCityLocation);
		mexicoCityMarker.setColor(color(200, 0, 0, 100));
		SimpleLinesMarker connectionMarker = new SimpleLinesMarker(berlinLocation, mexicoCityLocation);
		map.addMarkers(berlinMarker, mexicoCityMarker, connectionMarker);
	}

	public void draw() {
		background(240);
		map.draw();
	}

}