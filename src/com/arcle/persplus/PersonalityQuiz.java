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
package com.arcle.persplus;

import com.arcle.persplus.quiz.*;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Random;

/**
 @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
 @version $Id: PersonalityQuiz.java,v 1.1 2003/01/29 16:52:30 cutecoder Exp $
*/
public class PersonalityQuiz implements Quiz {

    protected PersonalityQuiz() {}


    /**
    Returns an enumeration of QuizQuestions.
    */
    public Enumeration questions() {
        return personalityQuestions.elements();
    }


    public int getMaxAnswers() {
        return Words.strengths[0].length;
    }

    //-----------------------------------------------------------------------
    // Accessor methods

    public int getStrengthSanguinic() {
        return strengthSanguinic;
    }


    public int getStrengthMelancholic() {
        return strengthMelancholic;
    }

    public int getStrengthCholeric() {
        return strengthCholeric;
    }

    public int getStrengthPhlegmatic() {
        return strengthPhlegmatic;
    }


    public int getWeaknessSanguinic() {
        return weaknessSanguinic;
    }


    public int getWeaknessMelancholic() {
        return weaknessMelancholic;
    }

    public int getWeaknessCholeric() {
        return weaknessCholeric;
    }

    public int getWeaknessPhlegmatic() {
        return weaknessPhlegmatic;
    }


    //-----------------------------------------------------------------------
    // answer-tracking methods

    protected void incrementStrengthSanguinic() {
        strengthSanguinic++;
    }

    protected void incrementStrengthCholeric() {
        strengthCholeric++;
    }

    protected void incrementStrengthMelancholic() {
        strengthMelancholic++;
    }

    protected void incrementStrengthPhlegmatic() {
        strengthPhlegmatic++;
    }


    protected void incrementWeaknessSanguinic() {
        weaknessSanguinic++;
    }

    protected void incrementWeaknessCholeric() {
        weaknessCholeric++;
    }

    protected void incrementWeaknessMelancholic() {
        weaknessMelancholic++;
    }

    protected void incrementWeaknessPhlegmatic() {
        weaknessPhlegmatic++;
    }

    //-----------------------------------------------------------------------
    // Member variables

    /**
    The questions involved
    */
    private Vector personalityQuestions;

    private int weaknessSanguinic;
    private int weaknessCholeric;
    private int weaknessMelancholic;
    private int weaknessPhlegmatic;

    private int strengthSanguinic;
    private int strengthCholeric;
    private int strengthMelancholic;
    private int strengthPhlegmatic;


    //-----------------------------------------------------------------------
    // Inner classes

    /**
    Creates PersonalityQuiz objects.
    */
    public static class Factory implements QuizFactory {

        public Factory(ProgressListener l) {
            listener = l;
        }


        public Quiz createQuiz() {
            final int maxWord = 4;
            final int maxQuestions = Words.strengths.length + Words.weaknesses.length;
            int usedQuestions = 0;
            Random rnd = new Random();
            String[] entry = new String[maxWord];
            PersonalityQuiz quiz = new PersonalityQuiz();
            quiz.personalityQuestions = new Vector(maxQuestions);

            // used to mark off entries that are already in use.
            boolean[][] usedStrengths = new boolean[Words.strengths.length][maxWord];
            boolean[][] usedWeaknesses = new boolean[Words.weaknesses.length][maxWord];

            if(listener != null) {
                listener.begin(maxQuestions-1);
            }

            boolean isStrength = false;
            boolean noSwitch = false;
            while(usedQuestions < maxQuestions) {
                int index;
                int indexWord;
                int len;
                int type = 0;
                boolean[][] used;
                String[][] words;

                if(!noSwitch) {
                    /* switch only if there are still unused words in one of
                       the word lists */
                    isStrength = rnd.nextInt() % 2 == 0;
                }

                if(isStrength) {
                    len = usedStrengths.length;
                    used = usedStrengths;
                    words = Words.strengths;
                    type = Question.STRENGTH;
                }
                else {
                    len = usedWeaknesses.length;
                    used = usedWeaknesses;
                    words = Words.weaknesses;
                    type = Question.WEAKNESS;
                }

                // for all personality types
                boolean forceBreak = false;
                for(indexWord=0; indexWord<maxWord; indexWord++) {
                    entry[indexWord] = null;
                    // repeat generating indexes until we find an unused entry
                    int tries = 0;
                    do {
                        index = Math.abs(rnd.nextInt() % len);
                        tries++;
                        if(tries > len) { // too many tries?
                            /*
                            LOOP INVARIANT:
                            triedAll is true IFF the boolean sub-array
                            used[0..i-1][indexWord] contains all true.
                            */
                            boolean triedAll = true;
                            for(int i=0;i<len;i++) {
                                triedAll &= used[i][indexWord];
                            }

                            if(triedAll) {
                                forceBreak = true;  // all indexes has been tried.

                                /*
                                All words in the current word list (strengths/weakness)
                                has already been used. So, force the use of the other
                                word list. */
                                isStrength = !isStrength;   // switch word list
                                noSwitch = true;            // prevent word list switching
                            }
                            else {
                                tries = 0;          // keep on trying
                            }
                        }
                    }
                    while(used[index][indexWord] == true && !forceBreak);
                    if(!forceBreak) {
                        // found a vacant entry
                        used[index][indexWord] = true; // mark the entry as used.
                        entry[indexWord] = words[index][indexWord]; // made one entry

                    }
                }

                /*
                last traversal found a full word list, so restart processing
                without adding the words found.
                */
                if(forceBreak) {
                    continue;
                }

                quiz.personalityQuestions.addElement(new Question(quiz,type,entry[0],entry[1],entry[2],entry[3]));
                usedQuestions++; // added one more question

                if(listener != null) {
                    listener.update(usedQuestions);
                }
            }

            if(listener != null) {
                listener.end();
            }
            return quiz;
        }

        /**
        The listener object is called during object creation.
        */
        private ProgressListener listener;

    }

    /**
     One question in the quiz.
     @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
    */
    protected static class Question implements QuizQuestion {
        /*
        @param s Sanguinic
        @param c Choleric
        @param m Melancholic
        @param p Phlegmatic
        */
        public Question(PersonalityQuiz q,int type, String s,String c, String m,String p) {
            Answer as = null;  // sanguinic
            Answer ac = null;  // choleric
            Answer am = null;  // melancholic
            Answer ap = null;  // phlegmatic
            switch(type) {
            case STRENGTH:
                as = new Answer(q,Answer.STRENGTH_SANGUINIC,s);
                ac = new Answer(q,Answer.STRENGTH_CHOLERIC,c);
                am = new Answer(q,Answer.STRENGTH_MELANCHOLIC,m);
                ap = new Answer(q,Answer.STRENGTH_PHLEGMATIC,p);
                break;
            case WEAKNESS:
                as = new Answer(q,Answer.WEAKNESS_SANGUINIC,s);
                ac = new Answer(q,Answer.WEAKNESS_CHOLERIC,c);
                am = new Answer(q,Answer.WEAKNESS_MELANCHOLIC,m);
                ap = new Answer(q,Answer.WEAKNESS_PHLEGMATIC,p);
                break;
            }
            personalityWords = new Vector(4);
            personalityWords.addElement(as);
            personalityWords.addElement(ac);
            personalityWords.addElement(am);
            personalityWords.addElement(ap);
        }

        public Enumeration answers() {
            return personalityWords.elements();
        }

        /**
        Returns the question text for this question. For the PersonalityQuiz,
        all question texts are the same.
        */
        public String getText() {
            return "Personality trait?";
        }

        /**
        The personality words involved.
        */
        Vector personalityWords;

        /**
        The owner quiz object.
        */
        PersonalityQuiz quiz;

        public void selectAnswer(QuizAnswer a) {
            Answer aa = (Answer) a;
            aa.select();
        }

        public static final int STRENGTH = 1;
        public static final int WEAKNESS = 2;

    }

    /**
     One answer in a quiz question.
     @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
    */
    protected static class Answer implements QuizAnswer {
        /**
        Constructs the object.
        @param q    the owning PersonalityQuiz object.
        @param tp   the type of the answer, one of STRENGTH_xxx or WEAKNESS_xxx.
        @param txt  the display text of the answer.
        */
        public Answer(PersonalityQuiz q, int tp, String txt) {
            quiz = q;
            text = txt;
            type = tp;
        }

        /**
        Returns the display text of this answer.
        */
        public String getText() {
            return text;
        }

        /**
        Selects this instance as the answer for its corresponding question.
        This implementation reports to the owner PersonalityQuiz instance
        notifying it of the selected answer.
        */
        public void select() {
            switch(type) {
            case STRENGTH_SANGUINIC:
                quiz.incrementStrengthSanguinic();
                break;
            case STRENGTH_CHOLERIC:
                quiz.incrementStrengthCholeric();
                break;
            case STRENGTH_MELANCHOLIC:
                quiz.incrementStrengthMelancholic();
                break;
            case STRENGTH_PHLEGMATIC:
                quiz.incrementStrengthPhlegmatic();
                break;
            case WEAKNESS_SANGUINIC:
                quiz.incrementWeaknessSanguinic();
                break;
            case WEAKNESS_CHOLERIC:
                quiz.incrementWeaknessCholeric();
                break;
            case WEAKNESS_MELANCHOLIC:
                quiz.incrementWeaknessMelancholic();
                break;
            case WEAKNESS_PHLEGMATIC:
                quiz.incrementWeaknessPhlegmatic();
                break;
            }
        }

        /**
        The personality characteristic word.
        */
        private String text;

        /**
        The characteristic word type. One of STRENGTH_xxx or WEAKNESS_xxx.
        */
        private int type;

        /**
        The owning quiz object.
        */
        private PersonalityQuiz quiz;

        public static final int STRENGTH_SANGUINIC      = 0x01;
        public static final int STRENGTH_CHOLERIC       = 0x02;
        public static final int STRENGTH_MELANCHOLIC    = 0x03;
        public static final int STRENGTH_PHLEGMATIC     = 0x04;

        public static final int WEAKNESS_SANGUINIC      = 0x11;
        public static final int WEAKNESS_CHOLERIC       = 0x12;
        public static final int WEAKNESS_MELANCHOLIC    = 0x13;
        public static final int WEAKNESS_PHLEGMATIC     = 0x14;

    }


}

