package tomaszmarzec.udacity.android.udacityandroidquiz2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{
    private int points = 0;

    @BindString(R.string.single_choice_question1_correct_answer) String singleQuestion1CorrectAnswer;
    @BindString(R.string.single_choice_question2_correct_answer) String singleQuestion2CorrectAnswer;
    @BindString(R.string.open_question1_answer) String openQuestion1CorrectAnswer;
    @BindArray(R.array.multiple_choice_question1_correct_answers) String[] multipleChoiceQuestion1Answers;

    @BindView(R.id.open_question1_answer)  EditText openQuestion1Answer;
    @BindView(R.id.multiple_question1_box1) CheckBox multipleChoiceQuestion1Box1;
    @BindView(R.id.multiple_question1_box2) CheckBox multipleChoiceQuestion1Box2;
    @BindView(R.id.multiple_question1_box3) CheckBox multipleChoiceQuestion1Box3;
    @BindView(R.id.multiple_question1_box4) CheckBox multipleChoiceQuestion1Box4;
    @BindView(R.id.multiple_question1_box5) CheckBox multipleChoiceQuestion1Box5;
    @BindView(R.id.single_choice_question1_answer_block) RadioGroup singleChoiceQuestion1AnswerBox;
    @BindView(R.id.single_choice_question2_answer_block) RadioGroup singleChoiceQuestion2AnswerBox;

    private CheckBox[] multipleChoiceQuestion1BoxArray;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        multipleChoiceQuestion1BoxArray= new CheckBox[]{multipleChoiceQuestion1Box1, multipleChoiceQuestion1Box2, multipleChoiceQuestion1Box3,
                                                        multipleChoiceQuestion1Box4, multipleChoiceQuestion1Box5};
    }

    private void checkSingleChoiceQuestion(RadioGroup radioGroup, String answer)
    {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId>0)
        {
            RadioButton selectedRadioButton = findViewById(selectedId);
            if (selectedRadioButton.getText().toString().equals(answer))
            {
                points++;
            }
        }
    }

    private void checkMultipleChoiceQuestion(CheckBox[] checkBoxes, String[] answersResource)
    {
        for(int i =0; i<checkBoxes.length; i++)
        {
            String answerToCheck = checkBoxes[i].getText().toString();
            if(checkBoxes[i].isChecked()&&checkStringArray(answerToCheck, answersResource) ||!checkBoxes[i].isChecked()&&!checkStringArray(answerToCheck, answersResource))
            {
                continue;
            }
            else
                return;
        }
        points +=2;
    }

    private boolean checkStringArray(String text, String[] checkedAnswers)
    {
        for (String toCheck : checkedAnswers)
        {
            if(toCheck.equals(text))
                return true;
        }
        return false;
    }

    private void checkOpenQuestion(String correctAnswer, EditText userAnswer)
    {
       if(userAnswer.getText().toString().equals(correctAnswer))
           points++;
    }

    public void summarize(View view)
    {
        points = 0;
        checkOpenQuestion(openQuestion1CorrectAnswer,openQuestion1Answer);
        checkMultipleChoiceQuestion(multipleChoiceQuestion1BoxArray, multipleChoiceQuestion1Answers);
        checkSingleChoiceQuestion(singleChoiceQuestion1AnswerBox, singleQuestion1CorrectAnswer);
        checkSingleChoiceQuestion(singleChoiceQuestion2AnswerBox, singleQuestion2CorrectAnswer);
        Toast.makeText(getApplicationContext(), "Your score is "+points+" out of 5",Toast.LENGTH_SHORT).show();
    }
}