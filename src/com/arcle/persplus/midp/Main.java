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
import com.arcle.persplus.quiz.*;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;


/**
 The "Personality Plus" midlet.
 @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
 @version $Id: Main.java,v 1.1 2003/01/29 16:52:30 cutecoder Exp $
 @since 25-01-03 23:21
*/
public class Main extends MIDlet implements UIManager {
    public Main() {
        quiz = null;
    }

    /**
    Called to show the welcome screen
    */
    protected void doShowWelcome() {
        welcomeUI = new WelcomeUI(this);
        welcomeUI.init();
        Display.getDisplay(this).setCurrent(welcomeUI.getDisplayable());
    }


    /**
    Caled to create the quiz object and show the progressUI while the
    word list is being loaded.
    */
    protected void doCreateQuiz() {
        Display.getDisplay(this).setCurrent(getProgressUI().getDisplayable());
        final Main _this = this;

        Runnable r = new Runnable() {
            public void run() {
                try {
                    quiz = getQuizFactory().createQuiz();
                    _this.doBeginQuiz();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    /**
    Called after the quiz variable has been initialized, this method
    replaces the "progress" form on the display with the quiz form
    to start the quiz.
    */
    protected void doBeginQuiz() {
        mainUI = new QuizUI(this,quiz);
        mainUI.init();
        Display.getDisplay(this).setCurrent(mainUI.getDisplayable());
    }

    /**
    Called when the quiz has been completed to show the results screen.
    */
    protected void doShowResults() {
        resultUI = new ResultUI(this,(PersonalityQuiz)quiz);
        resultUI.init();
        Display.getDisplay(this).setCurrent(resultUI.getDisplayable());
    }

    //-----------------------------------------------------------------------
    // accessor methods

    protected ProgressUI getProgressUI() {
        if(_progressUI == null) {
            _progressUI = new ProgressUI(this,"Personality Plus","Loading");
            _progressUI.init();
        }
        return _progressUI;
    }

    protected QuizFactory getQuizFactory() {
        if(_quizFactory == null) {
            _quizFactory = new PersonalityQuiz.Factory(getProgressUI());
        }
        return _quizFactory;
    }

    //-----------------------------------------------------------------------
    // MIDLet methods

    public void startApp() {
        doShowWelcome();
    }

    public void pauseApp() {}

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

    //-----------------------------------------------------------------------
    // UIManager methods

    /**
    Called when a UserInterface object has completed and requests to close.
    @param source The calling object.
    */
    public void requestClose(UserInterface source) {
        if(source == getProgressUI()) {
            /* do nothing, the thread created by doBeginQuiz will create
               the mainUI */
        }
        else if(source == mainUI) {
            doShowResults();
        }
        else if(source == resultUI) {
            requestExit(source);
        }
        else if(source == welcomeUI) {
            doCreateQuiz();
        }

    }

    /**
    Called when a UserInterface object wants to close the application.
    @param source The calling object.
    */
    public void requestExit(UserInterface source) {
        destroyApp(true);
    }


    //-----------------------------------------------------------------------
    // Member variables


    /**
    The screen that displays instructions and copyright information.
    */
    protected UserInterface welcomeUI;

    /**
    The screen that displays the quiz questions and wait for the user to answer
    each question.
    */
    protected UserInterface mainUI;

    /**
    The screen that displays the result of the quiz.
    */
    protected UserInterface resultUI;

    /**
    The screen that displays a progress bar while the quiz is being created.
    */
    private ProgressUI _progressUI;

    /**
    The current quiz.
    */
    protected Quiz   quiz;

    /**
    The Abstract Factory that creates Quiz objects.
    */
    private QuizFactory _quizFactory;
}

