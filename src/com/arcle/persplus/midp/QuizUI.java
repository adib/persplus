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

import com.arcle.persplus.quiz.*;
import com.arcle.persplus.Words;

import java.util.Enumeration;
import java.util.Random;
import javax.microedition.lcdui.*;

/**
 A screen that displays quiz questions and their corresponding answers
 one at a time and waits for the user to select one answer for each question.
 @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
 @version $Id: QuizUI.java,v 1.1 2003/01/29 16:52:31 cutecoder Exp $
 @since 26-01-03 13:01
*/
public class QuizUI extends UserInterface {
    public QuizUI(UIManager m,Quiz q) {
        super(m);
        quiz = q;
        _quizQuestions = null;
        curAnswers = new QuizAnswer[quiz.getMaxAnswers()];

        // jump to the first question
        doNextQuestion();
    }

    protected boolean hasMoreQuestions() {
        return getQuestions().hasMoreElements();
    }

    //-----------------------------------------------------------------------
    //

    /**
    Advances to the next question and updates the answer list accordingly.
    */
    protected void doNextQuestion() {
        curQuestion = (QuizQuestion) getQuestions().nextElement();
        getAnswerList().setTitle(curQuestion.getText());
        Enumeration answers = curQuestion.answers();
        int i=0;

        // populate curAnswers
        while(answers.hasMoreElements() && i < curAnswers.length) {
            curAnswers[i] =  (QuizAnswer) answers.nextElement();
            i++;
        }

        // shuffle entries
        Random rnd = new Random();
        for(i=0; i < (curAnswers.length+1) * 3 / 2; i++) {
            int i1 = Math.abs(rnd.nextInt() % curAnswers.length);
            int i2 = Math.abs(rnd.nextInt() % curAnswers.length);
            if(i1 != i2) {
                QuizAnswer tmp = curAnswers[i1];
                curAnswers[i1] = curAnswers[i2];
                curAnswers[i2] = tmp;
            }
        }

        // update answer list
        for(i=0;i<curAnswers.length;i++) {
            getAnswerList().set(i,curAnswers[i].getText(),null);
        }
    }


    /**
    Selects the current answer.
    @param selectedIndex
    */
    protected void doSelectAnswer(int selectedIndex) {
        curQuestion.selectAnswer(curAnswers[selectedIndex]);
    }

    protected void doQuizComplete() {
        getOwner().requestClose(this);
    }


    //-----------------------------------------------------------------------
    // UserInterface methods

    protected void setupCommands() {
        cmdSelectAnswer = new Command("Select",Command.OK,1);
        getAnswerList().addCommand(cmdSelectAnswer);
        super.setupCommands();
    }

    public Displayable getDisplayable() {
        return getAnswerList();
    }


    //-----------------------------------------------------------------------
    // accessor methods

    /**
    Returns the managed List instance.
    */
    protected List getAnswerList() {
        if(_lstQuizAnswers == null) {
            _lstQuizAnswers = new List("",List.IMPLICIT);
            // initially, create dummy entries
            for(int i=0; i<quiz.getMaxAnswers(); i++) {
                _lstQuizAnswers.append("-",null);
            }
        }
        return _lstQuizAnswers;
    }

    /**
    Returns the enumeration of QuizQuestion objects for the current quiz.
    */
    protected Enumeration getQuestions() {
        if(_quizQuestions == null) {
            _quizQuestions = quiz.questions();
        }
        return _quizQuestions;
    }


    //-----------------------------------------------------------------------
    // CommandListener methods

    public void commandAction(Command c, Displayable s) {
        if(c == cmdSelectAnswer || c == List.SELECT_COMMAND) {
            // an item has been selected
            if(hasMoreQuestions()) {
                doSelectAnswer(getAnswerList().getSelectedIndex());
                doNextQuestion();
            }
            else {
                doQuizComplete();
            }

        }
        else {
            super.commandAction(c,s);
        }
    }

    //-----------------------------------------------------------------------
    // Member variables

    Quiz quiz;

    /**
    The currently displayed question.
    */
    QuizQuestion curQuestion;

    /**
    Returns the current answers
    */
    QuizAnswer[] curAnswers;

    /**
    Displays the list of possible answers in the current question.
    */
    List _lstQuizAnswers;

    /**
    An enumeration of QuizQuestion objects that makes up the questions in
    the current quiz. This member variable must only be accessed through
    the getQuestions() method.
    @see #getQuestions()
    */
    Enumeration _quizQuestions;



    Command cmdSelectAnswer;

}
