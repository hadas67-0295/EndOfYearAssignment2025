package com.example.endofyearassignment2025;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    TextView tvUserName;
    TextView tvNumberOfLeft;
    TextView tvNumberOfRight;

    Button btnGreaterThan;
    Button btnSmallerThan;
    Button btnEqual;
    Button btnShowScore;

    String userName = "";

    ArrayList<Question> questionList;
    int currentQuestionIndex = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvUserName = findViewById(R.id.tvUserName);
        tvNumberOfLeft = findViewById(R.id.tvNumberOfLeft);
        tvNumberOfRight = findViewById(R.id.tvNumberOfRight);

        btnGreaterThan = findViewById(R.id.btnGreaterThan);
        btnSmallerThan = findViewById(R.id.btnSmallerThan);
        btnEqual = findViewById(R.id.btnEqual);
        btnShowScore = findViewById(R.id.btnShowScore);

        userName = getIntent().getStringExtra("userName");
        tvUserName.setText("Hello " + userName);

        questionList = populateQuestions(7);
        Question currentQuestion = questionList.get(currentQuestionIndex);
        tvNumberOfLeft.setText((String.valueOf(currentQuestion.getLeftNumber())));
        tvNumberOfRight.setText(String.valueOf(currentQuestion.getRightNumber()));

        btnGreaterThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question currentQuestion = questionList.get(currentQuestionIndex);
                int leftNumber = currentQuestion.getLeftNumber();
                int rightNumber = currentQuestion.getRightNumber();

                if (leftNumber > rightNumber) {
                    score++;
                }

                currentQuestionIndex++;

                if (currentQuestionIndex < questionList.size()) {
                    Question nextQuestion = questionList.get(currentQuestionIndex);
                    tvNumberOfLeft.setText(String.valueOf(nextQuestion.getLeftNumber()));
                    tvNumberOfRight.setText(String.valueOf(nextQuestion.getRightNumber()));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setTitle("Game Over");
                    builder.setMessage("You answered all the questions. Click 'Show Score' to continue.");
                    builder.setPositiveButton("OK", null);
                    builder.show();

                    btnGreaterThan.setEnabled(false);
                    btnSmallerThan.setEnabled(false);
                    btnEqual.setEnabled(false);
                }
            }
        });

        btnSmallerThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question currentQuestion = questionList.get(currentQuestionIndex);
                int leftNumber = currentQuestion.getLeftNumber();
                int rightNumber = currentQuestion.getRightNumber();

                if (leftNumber < rightNumber) {
                    score++;
                }

                currentQuestionIndex++;

                if (currentQuestionIndex < questionList.size()) {
                    Question nextQuestion = questionList.get(currentQuestionIndex);
                    tvNumberOfLeft.setText(String.valueOf(nextQuestion.getLeftNumber()));
                    tvNumberOfRight.setText(String.valueOf(nextQuestion.getRightNumber()));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setTitle("Game Over");
                    builder.setMessage("You answered all the questions. Click 'Show Score' to continue.");
                    builder.setPositiveButton("OK", null);
                    builder.show();

                    btnGreaterThan.setEnabled(false);
                    btnSmallerThan.setEnabled(false);
                    btnEqual.setEnabled(false);
                }
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question currentQuestion = questionList.get(currentQuestionIndex);
                int leftNumber = currentQuestion.getLeftNumber();
                int rightNumber = currentQuestion.getRightNumber();
                if (leftNumber == rightNumber) {
                    score++;
                }
                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    Question nextQuestion = questionList.get(currentQuestionIndex);
                    tvNumberOfLeft.setText(String.valueOf(nextQuestion.getLeftNumber()));
                    tvNumberOfRight.setText(String.valueOf(nextQuestion.getRightNumber()));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setTitle("Game Over");
                    builder.setMessage("You answered all the questions. Click 'Show Score' to continue.");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                    btnGreaterThan.setEnabled(false);
                    btnSmallerThan.setEnabled(false);
                    btnEqual.setEnabled(false);
                }
            }
        });
        btnShowScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("are you sure?");
                builder.setMessage("this will save your score and reset the game");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] motivationSentence={
                                "good job", "you did well", "keep it up"
                        };
                        String randomMotivation = motivationSentence[new Random().nextInt(motivationSentence.length)];
                        int precentScore = (score*100)/questionList.size();

                        GameResultDatabase gameResultDatabase = GameResultDatabase.getInstance(MainActivity2.this);
                        GameResult gameResult = new GameResult(userName,precentScore,randomMotivation);
                        gameResultDatabase.gameResultDao().insert(gameResult);

                        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                        intent.putExtra("username", userName);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("cancel", null);
                builder.show();

            }
        });
    }
    public ArrayList<Question> populateQuestions(int numberOfQuestions){
        ArrayList<Question> arrList = new ArrayList<>();
        Random random = new Random();
        int leftNumber=0;
        int rightNumber=0;
        for(int i=0; i<numberOfQuestions;i++) {
            leftNumber = random.nextInt(100);
            rightNumber = random.nextInt(100);

            arrList.add(new Question(leftNumber, rightNumber));
        }
        return arrList;

        }
    }
