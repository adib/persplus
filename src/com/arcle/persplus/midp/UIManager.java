/*
  Personality Plus for J2ME
  Copyright (C) 2003 Arcle Technologies
  http://www.arcle.com/

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package com.arcle.persplus.midp;

/**
 The UIManager interface should be implemented by classes that owns
 one or more instances of UserInterface subclasses. Typically, these owners
 are MIDlets.

 @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
 @version $Id: UIManager.java,v 1.1 2003/01/29 16:52:31 cutecoder Exp $
*/
public interface UIManager {

    /**
    Called when a UserInterface object has completed and requests to close.
    @param source The calling object.
    */
    public void requestClose(UserInterface source);

    /**
    Called when a UserInterface object wants to close the application.
    @param source The calling object.
    */
    public void requestExit(UserInterface source);
}
