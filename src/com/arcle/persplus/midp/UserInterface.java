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

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 The UserInterface class is to be used as a base class for classes
 exhibits a Displayable object.
 @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
 @version $Id: UserInterface.java,v 1.1 2003/01/29 16:52:31 cutecoder Exp $
*/
public abstract class UserInterface implements CommandListener {
    public UserInterface(UIManager o) {
        _owner = o;

    }

    /**
    Call this method after construction of the object.
    */
    public void init() {
        setupCommands();
    }


    /**
    Configures the commands for this screen. The default implementation adds
    an EXIT command to the Displayable object.
    */
    protected void setupCommands() {
        Displayable d = getDisplayable();
        Command cmd = new Command("Exit",Command.EXIT,0);
        d.addCommand(cmd);
        d.setCommandListener(this);
    }

    //-----------------------------------------------------------------------
    //

    /**
    Called when the object receives an EXIT command. This implementation
    calls the owner MIDLet's notifyDestroyed() method to exit the MIDLet.
    */
    protected void doExit() {
        getOwner().requestExit(this);
    }

    //-----------------------------------------------------------------------
    // accessor methods

    /**
    Returns the Displayable object managed by this instance.
    */
    public abstract Displayable getDisplayable();

    /**
    Returns the owner of this instance.
    */
    public UIManager getOwner() {
        return _owner;
    }

    //-----------------------------------------------------------------------
    // CommandListener methods

    public void commandAction(Command c, Displayable s) {
        switch(c.getCommandType()) {
        case Command.EXIT:
            doExit();
            break;
        }
    }

    //-----------------------------------------------------------------------
    // Member variables

    /**
    The owner of this UserInterface instance.
    */
    private UIManager _owner;
}
