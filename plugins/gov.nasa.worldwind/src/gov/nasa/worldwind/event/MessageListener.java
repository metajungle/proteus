/*
 * Copyright (C) 2011 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */

package gov.nasa.worldwind.event;

/**
 * Listener for general purpose message events.
 *
 * @author pabercrombie
 * @version $Id: MessageListener.java 1 2011-07-16 23:22:47Z dcollins $
 */
public interface MessageListener
{
    /**
     * Invoked when a message is received.
     *
     * @param msg The message that was received.
     */
    void onMessage(Message msg);
}
