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

/**
 Contains the word strings which represents each personality characteristic.
 The personality words are taken from <cite>Personality Plus</cite>,
 Florence Littauer.
 @author Sasmito Adibowo [cutecoder@users.sourceforge.net]
 @version $Id: Words.java,v 1.1 2003/01/29 16:52:30 cutecoder Exp $
*/
public interface Words {
    /**
    Strength words.
    [i][j], where:
    * <pre>
    *  j = 0, Sanguinic
    *  j = 1, Choleric
    *  j = 2, Melancholic
    *  j = 3, Phlegmatic
    * </pre>
    */
    public static final String[][] strengths= {
        // 1
        {"Animated", "Adventurous", "Analytical", "Adaptable"},
        // 2
        {"Playful", "Persuasive", "Persistent", "Peaceful"},
        // 3
        {"Sociable", "Strong-willed", "Self-Sacrificing", "Submissive"},
        // 4
        {"Convincing", "Competitive", "Considerate", "Controlled"},
        // 5
        {"Refreshing", "Resourceful", "Respectful", "Reserved"},
        // 6
        {"Spirited", "Self-reliant", "Sensitive", "Satisfied"},
        // 7
        {"Promoter", "Positive", "Planner", "Patient"},
        // 8
        {"Spontaneous", "Sure", "Scheduled", "Shy"},
        // 9
        {"Optimistic", "Outspoken", "Orderly", "Obliging"},
        // 10
        {"Funny", "Forceful", "Faithful", "Friendly"},
        // 11
        {"Delightful", "Daring", "Detailed", "Diplomatic"},
        // 12
        {"Cheerful", "Confident", "Cultured", "Consistent"},
        // 13
        {"Inspiring", "Independent", "Idealistic", "Inoffensive"},
        // 14
        {"Demonstrative", "Decisive", "Deep", "Dry humor"},
        // 15
        {"Mixes easily", "Mover", "Musical", "Mediator"},
        // 16
        {"Talker", "Tenacious", "Thoughtful", "Tolerant"},
        // 17
        {"Lively", "Leader", "Loyal", "Listener"},
        // 18
        {"Cute", "Chief", "Chartmaker", "Contended"},
        // 19
        {"Popular", "Productive", "Perfectionist", "Pleasant"},
        // 20
        {"Bouncy", "Bold", "Behaved", "Balanced"}

    };

    /**
    Weakneses words.
    [i][j], where:
    * <pre>
    *  j = 0, Sanguinic
    *  j = 1, Choleric
    *  j = 2, Melancholic
    *  j = 3, Phlegmatic
    * </pre>
    */
    public static final String[][] weaknesses = {
        // 21
        {"Brassy", "Bossy", "Bashful", "Blank"},
        // 22
        {"Undisciplined", "Unsympathetic", "Unforgiving", "Unenthusiastic" },
        // 23
        {"Repetitious", "Resistant", "Resentful", "Reticent" } ,
        // 24
        {"Forgetful", "Frank", "Fussy", "Fearful" },
        // 25
        {"Interrupts", "Impatient", "Insecure", "Indecisive"},
        // 26
        {"Unpredictable", "Unaffectionate", "Unpopular", "Uninvolved"},
        // 27
        {"Haphazard", "Headstrong", "Hard to please", "Hesitant"},
        // 28
        {"Permissive", "Proud", "Pessimistic", "Plain"},
        // 29
        {"Angered-easily", "Argumentative", "Alienated", "Aimless"},
        // 30
        {"Naive", "Nervy", "Negative-attitude", "Nonchalant"},
        // 31
        {"Wants-credit", "Workaholic", "Withdrawn", "Worrier"},
        // 32
        {"Talkative", "Tactless", "Too-sensitive", "Timid"},
        // 33
        {"Disorganized", "Domineering", "Depressed", "Doubtful"},
        // 34
        {"Inconsistent", "Intolerant", "Introvert", "Indifferent"},
        // 35
        {"Messy", "Manipulative", "Moody", "Mumbles"},
        // 36
        {"Show-off", "Stubborn", "Skeptical", "Slow"},
        // 37
        {"Loud", "Lord over others", "Loner", "Lazy"},
        // 38
        {"Scatter-brained", "Short-tempered", "Suspicious", "Sluggish"},
        // 39
        {"Restless", "Rash", "Revengeful", "Reluctant"},
        // 40
        {"Changeable", "Crafty", "Critical", "Compromising"}
    };
}
 