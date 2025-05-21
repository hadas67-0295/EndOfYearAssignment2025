package com.example.endofyearassignment2025;

import java.util.Random;

public class Question {
    private int leftNumber;//מייצג את המספר השמאלי בשאלה
    private int rightNumber;//מייצג את המספר הימני בשאלה

    public Question(int leftNumber, int rightNumber) {
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
    }

    //פעולות מאחזרות
    public int getLeftNumber() {
        return this.leftNumber;
    }

    public int getRightNumber() {
        return this.rightNumber;
    }

    public char calculateCorrectAnswer() {
        if (leftNumber == rightNumber){ //אם הערכים בשני הצדדים זהים
            return '=';
        }
        else if(this.leftNumber<this.rightNumber) {//אם הערך בצד קטן מצד ימין
            return '<';
        }
        else {//אם הערך בצד שמאל גדול מצד ימין
            return '>';
        }
    }
}