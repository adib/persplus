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

import com.arcle.persplus.quiz.ProgressListener;

import javax.microedition.lcdui.*;


/**
 A simple screen that displays a progress gauge. Instances of this class are
 to be used by algorithms that allows monitoring its progress through a
 ProgessListener instance.
 @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
 @version $Id: ProgressUI.java,v 1.1 2003/01/29 16:52:31 cutecoder Exp $
 @since 26-01-03 13:01
*/
public class ProgressUI extends UserInterface implements ProgressListener {

    /**
    Constructs the screen.
    @param owner    the owning manager.
    @param _title   The title of the form.
    @param _text    The text for the progress gauge.
    */
    public ProgressUI(UIManager owner,String _title,String _text) {
        super(owner);
        title = _title;
        text = _text;
    }

    //-----------------------------------------------------------------------
    // ProgressListener methods

    public void begin(int maxValue) {
        progressGauge.setMaxValue(maxValue);
    }

    public void end() {
        getOwner().requestClose(this);
    }


    public void update(int value) {
        progressGauge.setValue(value);
    }

    //-----------------------------------------------------------------------
    // UserInterface methods

    /**
    Returns the form displayed.
    */
    public Displayable getDisplayable() {
        if(theForm == null) {
            theForm = new Form(title);
            progressGauge = new Gauge(text, false,10,5);
            theForm.append(progressGauge);
        }
        return theForm;
    }


    //-----------------------------------------------------------------------
    // Member variables

    /**
    The progress gauge that is displayed
    */
    private Gauge progressGauge;

    private String title;

    private String text;

    private Form theForm;
}
