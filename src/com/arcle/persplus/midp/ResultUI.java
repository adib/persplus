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

import com.arcle.persplus.*;

import javax.microedition.lcdui.*;


/**
 Displays the result of a PersonalityQuiz.
 @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
 @version $Id: ResultUI.java,v 1.1 2003/01/29 16:52:31 cutecoder Exp $
 @since 26-01-03 13:01
*/
public class ResultUI extends UserInterface {
    /**
    Construct the result.
    */
    public ResultUI(UIManager owner,PersonalityQuiz q) {
        super(owner);
        quiz = q;
    }

    //-----------------------------------------------------------------------
    // UserInterface methods

    public Displayable getDisplayable() {
        if(theForm == null) {
            int[] strengths = new int[4];
            int[] weaknesses = new int[4];
            int maxStrength;
            int maxWeakness;

            strengths[0] = quiz.getStrengthSanguinic();
            strengths[1] = quiz.getStrengthCholeric();
            strengths[2] = quiz.getStrengthMelancholic();
            strengths[3] = quiz.getStrengthPhlegmatic();
            maxStrength = findMax(strengths);

            weaknesses[0] = quiz.getWeaknessSanguinic();
            weaknesses[1] = quiz.getWeaknessCholeric();
            weaknesses[2] = quiz.getWeaknessMelancholic();
            weaknesses[3] = quiz.getWeaknessPhlegmatic();
            maxWeakness = findMax(weaknesses);

            gsSanguinic = new Gauge("Sanguinic("+strengths[0]+")",false,maxStrength,strengths[0]);
            gsCholeric = new Gauge("Choleric("+strengths[1]+")",false,maxStrength,strengths[1]);
            gsMelancholic = new Gauge("Melancholic("+strengths[2]+")",false,maxStrength,strengths[2]);
            gsPhlegmatic = new Gauge("Phlegmatic("+strengths[3]+")",false,maxStrength,strengths[3]);

            gwSanguinic = new Gauge("Sanguinic("+strengths[0]+")",false,maxWeakness,weaknesses[0]);
            gwCholeric = new Gauge("Choleric("+strengths[1]+")",false,maxWeakness,weaknesses[1]);
            gwMelancholic = new Gauge("Melancholic("+strengths[2]+")",false,maxWeakness,weaknesses[2]);
            gwPhlegmatic = new Gauge("Phlegmatic("+strengths[3]+")",false,maxWeakness,weaknesses[3]);

            theForm = new Form("Test Results");

            theForm.append(new StringItem("Strengths",null));
            theForm.append(gsSanguinic);
            theForm.append(gsMelancholic);
            theForm.append(gsCholeric);
            theForm.append(gsPhlegmatic);

            theForm.append(new StringItem("Weaknesses",null));
            theForm.append(gwSanguinic);
            theForm.append(gwMelancholic);
            theForm.append(gwCholeric);
            theForm.append(gwPhlegmatic);
        }

        return theForm;
    }

    //-----------------------------------------------------------------------
    // utility methods

    /**
    Finds the maximum element value in an integer array.
    @return max(a[0..i-1]) where i=a.length
    */
    private static int findMax(int[] a) {
        int localMax = Integer.MIN_VALUE;
        /* LOOP INVARIANT:
        localMax contains the maximum value for the sub-array a[0..i-1] */
        for(int i=0; i<a.length; i++) {
            if(a[i] > localMax) {
                localMax=a[i];
            }
        }
        return localMax;
    }


    //-----------------------------------------------------------------------
    // member variables

    /**
    The form that this class manages.
    */
    private Form theForm;

    // strengths

    private Gauge gsSanguinic;
    private Gauge gsMelancholic;
    private Gauge gsCholeric;
    private Gauge gsPhlegmatic;

    // weaknesses

    private Gauge gwSanguinic;
    private Gauge gwMelancholic;
    private Gauge gwCholeric;
    private Gauge gwPhlegmatic;

    private PersonalityQuiz quiz;

    private static final int MAX_LEVEL_STRENGTH = Words.strengths.length-1;
    private static final int MAX_LEVEL_WEAKNESS = Words.weaknesses.length-1;
}
