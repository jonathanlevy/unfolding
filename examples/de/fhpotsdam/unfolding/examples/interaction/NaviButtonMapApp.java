package de.fhpotsdam.unfolding.examples.interaction;

import org.apache.log4j.Logger;

import processing.core.PApplet;
import codeanticode.glgraphics.GLConstants;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.events.EventDispatcher;
import de.fhpotsdam.unfolding.events.PanMapEvent;
import de.fhpotsdam.unfolding.events.ZoomMapEvent;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.DebugDisplay;
import de.fhpotsdam.unfolding.utils.MapUtils;

/**
 * Very simple manual navigation example. The user can press two buttons to jump to specific
 * locations.
 * 
 */
public class NaviButtonMapApp extends PApplet {

	public static Logger log = Logger.getLogger(NaviButtonMapApp.class);

	DebugDisplay debugDisplay;

	EventDispatcher eventDispatcher;

	UnfoldingMap map;

	public void setup() {
		size(800, 600, GLConstants.GLGRAPHICS);
		smooth();

		textFont(loadFont("Miso-Light-12.vlw"));

		// Creates default mapDisplay
		map = new UnfoldingMap(this, "map", 0, 0, 600, 600);
		map.setTweening(false);

		debugDisplay = new DebugDisplay(this, map, 600, 200);

		map.zoomToLevel(3);

		// default dispatcher
		eventDispatcher = MapUtils.createDefaultEventDispatcher(this, map);
	}

	public void draw() {
		background(0);
		map.draw();

		debugDisplay.draw();

		// Simple "Go to Berlin" - Button
		fill(255, 0, 0);
		rect(610, 10, 180, 80);
		fill(0);
		text("Berlin", 650, 50);

		// Simple "Go to Berlin" - Button
		fill(255, 0, 0);
		rect(610, 110, 180, 80);
		fill(0);
		text("FH Potsdam", 650, 150);
	}

	@Override
	public void mouseClicked() {
		if (mouseX > 610 && mouseX < 790 && mouseY > 10 && mouseY < 90) {

			// zoom and pan to berlin
			log.debug("Go To Berlin");
			ZoomMapEvent zoomMapEvent = new ZoomMapEvent(this, map.getId());
			zoomMapEvent.setSubType("zoomTo");
			zoomMapEvent.setZoomLevel(10);
			eventDispatcher.fireMapEvent(zoomMapEvent);

			PanMapEvent panMapEvent = new PanMapEvent(this, map.getId());
			Location berlin = new Location(52.439046f, 13.447266f);
			panMapEvent.setToLocation(berlin);
			eventDispatcher.fireMapEvent(panMapEvent);
		} else if (mouseX > 610 && mouseX < 790 && mouseY > 110 && mouseY < 190) {

			// zoom and pan to fh potsdam
			log.debug("Go To FH Potsdam");
			ZoomMapEvent zoomMapEvent = new ZoomMapEvent(this, map.getId());
			zoomMapEvent.setSubType("zoomTo");
			zoomMapEvent.setZoomLevel(16);
			eventDispatcher.fireMapEvent(zoomMapEvent);

			PanMapEvent panMapEvent = new PanMapEvent(this, map.getId());
			Location fhpotsdam = new Location(52.411613f, 13.051779f);
			panMapEvent.setToLocation(fhpotsdam);
			eventDispatcher.fireMapEvent(panMapEvent);
		}
	}

}
