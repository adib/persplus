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


/**
 Displays a simple welcome screen.
 @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
 @version $Id: WelcomeUI.java,v 1.1 2003/01/29 16:52:31 cutecoder Exp $
 @since 26-01-03 13:02
*/
public class WelcomeUI extends UserInterface {
    public WelcomeUI(UIManager m) {
        super(m);
    }


    public void setupCommands() {
        cmdBegin = new Command("Begin",Command.OK,1);
        getDisplayable().addCommand(cmdBegin);
        super.setupCommands();
    }

    public Displayable getDisplayable() {
        if(theForm == null) {
            theForm = new Form("Personality Plus!");
            theForm.append(new StringItem(null,INFO_TEXT));
            theForm.append(new StringItem(null,INSTRUCTION_TEXT));
            theForm.append(new StringItem(null,COPYRIGHT_TEXT));
            theForm.append(new StringItem(null,COPYRIGHT2_TEXT));
        }
        return theForm;
    }

    public void commandAction(Command c, Displayable s) {
        switch(c.getCommandType()) {
        case Command.OK:
            // close the form
            getOwner().requestClose(this);
            break;
        default:
            super.commandAction(c,s);
        }
    }

    /**
    The welcome form.
    */
    private Form theForm;


    /**
    Command to begin the quiz and dismiss the form.
    */
    private Command cmdBegin;

    private static final String COPYRIGHT_TEXT =
        "Copyright(C) 2002 Sasmito Adibowo\n" +
        "Arcle Technologies\n" +
        "http://www.arcle.com\n";

    private static final String COPYRIGHT2_TEXT =
        "Adapted from the book Personality Plus by Florence Littauer.\n";

    private static final String NOTICE_TEXT =
    "PersonalityPlus is a freeware program distributed under the terms of the " +
    "GNU General Public License. It is provided in the hope that it may be " +
    "useful and comes with ABSOLUTELY NO WARRANTY, expressed or implied.";


    private static final String INFO_TEXT =
        "The human personality is composed of four elements: sanguinic, " +
        "melancholic, choleric, and phlegmatic. Each of these elements has " +
        "its own strengths and weaknesses.\n";

    private static final String INSTRUCTION_TEXT =
        "You will be provided with several questions, where each question " +
        "lists words that describes a personality trait. For each question, " +
        "choose the word that best describes you. Don't take too much time " +
        "in choosing the word; the more you think of it, the less accurate " +
        "the answer will be. At the end of the quiz, " +
        "your personality type will be shown in terms of the four personality " +
        "elements previously cited. There are 40 questions to answer, so " +
        "please be patient.\n";
}

