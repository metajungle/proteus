/*
 * Copyright (C) 2013 Intelligent Automation Inc. 
 * 
 * All Rights Reserved.
 */
package com.iai.proteus.model.map;

import java.awt.Color;

import com.iai.proteus.model.MapId;
import com.iai.proteus.model.Model;
import com.iai.proteus.model.event.WorkspaceEventType;
import com.iai.proteus.model.services.Service;
import com.iai.proteus.model.workspace.QueryLayer;

/**
 * A workspace model object that corresponds to a map layer 
 * 
 * @author Jakob Henriksson
 *
 */
public abstract class MapLayer extends Model implements IMapLayer {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * The map ID
	 */
	private MapId mapId;

	/*
	 * Determines if the layer is active (visible) on the Map
	 * 
	 * NOTE: Currently we do not save the active state of a map layer. 
	 *       It should be noted that XMLEncoder does not respect the
	 *       transient keyword, instead we have to set the transient 
	 *       status before serializing the object to disk. 
	 */
	private transient boolean active;
	
	private Color color; 
	
	/**
	 * Default constructor 
	 */
	public MapLayer() {
		/*
		 * Generate a default map Id for this map layer 
		 */
		mapId = MapId.generateNewMapId(); 
		active = false;
	}
	
	/**
	 * Returns the map ID for this map layer 
	 * 
	 * @return the map ID
	 */
	public MapId getMapId() {
		return mapId;
	}

	/**
	 * Sets the map ID for this map layer 
	 * 
	 * @param mapId the map IDs to set
	 */
	public void setMapId(MapId mapId) {
		this.mapId = mapId;
	}
	
	/**
	 * Generates a new map ID 
	 * 
	 * @return
	 */
	public static MapId generateNewMapId() {
		return MapId.generateNewMapId();
	}

	/**
	 * Sets the activity state of this layer 
	 * 
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Returns true if the layer is active, false otherwise 
	 * 
	 * @return
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Marks the layer as active (visible)  
	 * 
	 */
	public void activate() {
		setActive(true);
	}
	
	/**
	 * Marks the layer as inactive (not visible) 
	 */
	public void deactivate() {
		setActive(false);
	}
	
	/**
	 * Sets the color for this map layer 
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color; 
	}
	
	/**
	 * Returns the color for this map layer 
	 * @return
	 */
	public Color getColor() {
		// default color if there is no one specified
		if (color == null)
			return Color.WHITE; 			
		return color;
	}
		

	/**
	 * Fire event stating that a layer was toggled 
	 * 
	 * (sending the relevant model object as an argument to the listener)
	 * 
	 */
	public void fireToggleLayer() {
		if (this instanceof QueryLayer) {
			/*
			 * Fire update to projects root
			 */
			QueryLayer queryLayer = (QueryLayer) this;
			queryLayer.fireEvent(WorkspaceEventType.WORKSPACE_LAYER_TOGGLE);
			
		} else if (this instanceof Service) {
			Service service = (Service) this;
			/*
			 * Fire update event
			 */
			service.fireEvent(WorkspaceEventType.WORKSPACE_LAYER_TOGGLE);
			
		} else if (this instanceof WmsMapLayer) {
			WmsMapLayer mapLayer = (WmsMapLayer) this;
			
			mapLayer.fireEvent(WorkspaceEventType.WORKSPACE_WMS_TOGGLE);
		}
	}
	
	/**
	 * Fire event stating that the layer should be deleted 
	 * 
	 * (sending the relevant model object as an argument to the listener)
	 * 
	 */
	public void fireDeleteLayer() {
		if (this instanceof Service) {
			Service service = (Service)this;
			/*
			 * Fire update to delete layer 
			 */
			service.fireEvent(WorkspaceEventType.WORKSPACE_LAYER_DELETE);
			
		} else if (this instanceof WmsMapLayer) {
			WmsMapLayer mapLayer = (WmsMapLayer) this;
			/*
			 * Request map layer to be deleted 
			 */
			mapLayer.fireEvent(WorkspaceEventType.WORKSPACE_LAYER_DELETE);
		}
	}
	
	public void fireChangeColor() {
		fireEvent(WorkspaceEventType.WORKSPACE_LAYER_COLOR_CHANGE);
	}
	
	@Override
	public String toString() {
		return "Map layer, ID: " + getMapId();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapId == null) ? 0 : mapId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapLayer other = (MapLayer) obj;
		if (mapId == null) {
			if (other.mapId != null)
				return false;
		} else if (!mapId.equals(other.mapId))
			return false;
		return true;
	}
}
