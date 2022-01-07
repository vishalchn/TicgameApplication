package com.example.ticgame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SinglePlayerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView txtPlayer1 = (TextView) findViewById(R.id.player1);
        txtPlayer1.setText("YOU");
        TextView txtPlayer2 = (TextView) findViewById(R.id.player2);
        txtPlayer2.setText("COMPUTER");

        Button btn_restart = findViewById(R.id.btn_restart);
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });
        fpScoreTextView = (TextView) findViewById(R.id.mainScreenScore_of_firstPlayer);
        spScoreTextView = (TextView) findViewById(R.id.mainScreenScore_of_secondPlayer);
    }

    //score of player
    int firstPlayerScore = 0;
    int secondPlayerScore = 0;
    int drawCheck = 0;  //checking match is draw or Not
    int noOfMatches = 1;

    TextView fpScoreTextView;
    TextView spScoreTextView;

    Dialog winner_dialog;
    Dialog nextMatch_dialog;

    //gamePlay
    boolean gameActive = true;
    // Player representation,gameState
    // Player_One(x):= 1, Computer:= 2 ,null:= 3
    int activePlayer = 1;
    int[] gameState = {3, 3, 3, 3, 3, 3, 3, 3, 3};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void computerTurn() {
        TextView status = findViewById(R.id.status);
        status.setText("Your's Turn");
        drawCheck++;
        Random rnd = new Random();
        boolean played = true;   // used for computer turn completed or not.
        while (played) {
            int rndNumber = rnd.nextInt(9);
            if (gameState[rndNumber] == 3) {
                played = false;
                switch (rndNumber) {
                    case 0:
                        gameState[0] = activePlayer;
                        ImageView img1 = (ImageView) findViewById(R.id.imageView0);
                        img1.setTranslationY(-1000f);
                        img1.setImageResource(R.drawable.p);
                        img1.animate().translationYBy(1000f).setDuration(300);
                        break;
                    case 1:
                        gameState[1] = activePlayer;
                        ImageView img2 = (ImageView) findViewById(R.id.imageView1);
                        img2.setTranslationY(-1000f);
                        img2.setImageResource(R.drawable.p);
                        img2.animate().translationYBy(1000f).setDuration(300);
                        break;
                    case 2:
                        gameState[2] = activePlayer;
                        ImageView img3 = (ImageView) findViewById(R.id.imageView2);
                        img3.setTranslationY(-1000f);
                        img3.setImageResource(R.drawable.p);
                        img3.animate().translationYBy(1000f).setDuration(300);
                        break;
                    case 3:
                        gameState[3] = activePlayer;
                        ImageView img4 = (ImageView) findViewById(R.id.imageView3);
                        img4.setTranslationY(-1000f);
                        img4.setImageResource(R.drawable.p);
                        img4.animate().translationYBy(1000f).setDuration(300);
                        break;
                    case 4:
                        gameState[4] = activePlayer;
                        ImageView img5 = (ImageView) findViewById(R.id.imageView4);
                        img5.setTranslationY(-1000f);
                        img5.setImageResource(R.drawable.p);
                        img5.animate().translationYBy(1000f).setDuration(300);
                        break;
                    case 5:
                        gameState[5] = activePlayer;
                        ImageView img6 = (ImageView) findViewById(R.id.imageView5);
                        img6.setTranslationY(-1000f);
                        img6.setImageResource(R.drawable.p);
                        img6.animate().translationYBy(1000f).setDuration(300);
                        break;
                    case 6:
                        gameState[6] = activePlayer;
                        ImageView img7 = (ImageView) findViewById(R.id.imageView6);
                        img7.setTranslationY(-1000f);
                        img7.setImageResource(R.drawable.p);
                        img7.animate().translationYBy(1000f).setDuration(300);
                        break;
                    case 7:
                        gameState[7] = activePlayer;
                        ImageView img8 = (ImageView) findViewById(R.id.imageView7);
                        img8.setTranslationY(-1000f);
                        img8.setImageResource(R.drawable.p);
                        img8.animate().translationYBy(1000f).setDuration(300);
                        break;
                    case 8:
                        gameState[8] = activePlayer;
                        ImageView img9 = (ImageView) findViewById(R.id.imageView8);
                        img9.setTranslationY(-1000f);
                        img9.setImageResource(R.drawable.p);
                        img9.animate().translationYBy(1000f).setDuration(300);
                        break;
                }
            }
        }
        activePlayer = 1;
    }

    public void playerTap(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.tapsound);
        mediaPlayer.start();

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (gameState[tappedImage] == 3) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 1) {
                img.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.status);
                status.setText(R.string.secondPlayerTurn);
                drawCheck++;
                activePlayer = 2;
            }
            img.animate().translationYBy(1000f).setDuration(300);
            if (activePlayer == 2) {
                if (gameState[0] == 3 || gameState[1] == 3 || gameState[2] == 3 || gameState[3] == 3 || gameState[4] == 3 || gameState[5] == 3 || gameState[6] == 3 || gameState[7] == 3 || gameState[8] == 3) {
                    computerTurn();
                }
            }
        }

        // Check if any player has won/draw
        for (int[] winPosition : winPositions) {
            // Check if any player has won
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 3) {

                if (noOfMatches <= 2) {
                    nextMatch_dialog = new Dialog(this);
                    nextMatch_dialog.setContentView(R.layout.next_match_dialog);
                    nextMatch_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    nextMatch_dialog.show();

                    TextView currentWinner = nextMatch_dialog.findViewById(R.id.current_Winner);
                    if (gameState[winPosition[0]] == 1) {
                        if (noOfMatches == 1) {
                            currentWinner.setText(R.string.fFirstMatch);
                        } else {
                            currentWinner.setText(R.string.fSecondMatch);
                        }
                        firstPlayerScore++;
                        activePlayer = 1;
                    } else {
                        if (noOfMatches == 1) {
                            currentWinner.setText(R.string.sFirstMatch);
                        } else {
                            currentWinner.setText(R.string.sSecondMatch);
                        }
                        secondPlayerScore++;
                        activePlayer = 2;
                    }

                    TextView currentScoreOfFirstPlayer = nextMatch_dialog.findViewById(R.id.currentScore_of_firstPlayer);
                    TextView currentScoreOfSecondPlayer = nextMatch_dialog.findViewById(R.id.currentScore_of_secondPlayer);
                    String firstPlayerScoreInString = Integer.toString(firstPlayerScore);
                    String secondPlayerScoreInString = Integer.toString(secondPlayerScore);
                    currentScoreOfFirstPlayer.setText(firstPlayerScoreInString);
                    currentScoreOfSecondPlayer.setText(secondPlayerScoreInString);
                    fpScoreTextView.setText(firstPlayerScoreInString);
                    spScoreTextView.setText(secondPlayerScoreInString);
                    Button nextMatchButton = nextMatch_dialog.findViewById(R.id.nextMatchButton);
                    nextMatchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gameReset(v);
                            nextMatch_dialog.dismiss();
                            drawCheck = 0;
                            noOfMatches++;
                        }
                    });
                } else {

                    if (gameState[winPosition[0]] == 1) {
                        firstPlayerScore++;
                    } else {
                        secondPlayerScore++;
                    }

                    winner(view);
                }
            }

            //check if match is draw
            if (drawCheck == 9 && gameState[winPosition[0]] != gameState[winPosition[1]] &&
                    gameState[winPosition[1]] != gameState[winPosition[2]]) {
                if (noOfMatches <= 2) {

                    nextMatch_dialog = new Dialog(this);
                    nextMatch_dialog.setContentView(R.layout.next_match_dialog);
                    nextMatch_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    nextMatch_dialog.show();
                    TextView currentWinner = nextMatch_dialog.findViewById(R.id.current_Winner);
                    currentWinner.setText(R.string.draw);

                    TextView currentScoreOfFirstPlayer = nextMatch_dialog.findViewById(R.id.currentScore_of_firstPlayer);
                    TextView currentScoreOfSecondPlayer = nextMatch_dialog.findViewById(R.id.currentScore_of_secondPlayer);
                    String firstPlayerScoreInString = Integer.toString(firstPlayerScore);
                    String secondPlayerScoreInString = Integer.toString(secondPlayerScore);
                    currentScoreOfFirstPlayer.setText(firstPlayerScoreInString);
                    currentScoreOfSecondPlayer.setText(secondPlayerScoreInString);

                    fpScoreTextView.setText(firstPlayerScoreInString);
                    spScoreTextView.setText(secondPlayerScoreInString);

                    drawCheck = 0;
                    noOfMatches++;
                    activePlayer = 1;

                    Button nextMatchButton = nextMatch_dialog.findViewById(R.id.nextMatchButton);
                    nextMatchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gameReset(v);
                            nextMatch_dialog.dismiss();
                        }
                    });
                } else {
                    winner(view);
                }
            }
        }
    }

    public void winner(View view) {
        winner_dialog = new Dialog(this);
        winner_dialog.setContentView(R.layout.win_layout_dialog);
        winner_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        winner_dialog.show();

        MediaPlayer media = MediaPlayer.create(this, R.raw.winsound);
        media.start();

        //Somebody has won! - Find out who!
        TextView winner = winner_dialog.findViewById(R.id.winner);
        if (firstPlayerScore == secondPlayerScore) {
            winner.setText(R.string.draw);
        } else if (firstPlayerScore > secondPlayerScore) {
            winner.setText(R.string.firstPlayerWon);
        } else {
            winner.setText(R.string.secondPlayerWon);
        }

        TextView totalScoreOfFirstPlayer = winner_dialog.findViewById(R.id.totalScore_of_firstPlayer);
        String totalScoreOfFirstPlayerInString = Integer.toString(firstPlayerScore);
        totalScoreOfFirstPlayer.setText(totalScoreOfFirstPlayerInString);

        TextView totalScoreOfSecondPlayer = winner_dialog.findViewById(R.id.totalScore_of_secondPlayer);
        String totalScoreOfSecondPlayerInString = Integer.toString(secondPlayerScore);
        totalScoreOfSecondPlayer.setText(totalScoreOfSecondPlayerInString);

        Button replayButton = winner_dialog.findViewById(R.id.replayButton);
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        Button homeButton = winner_dialog.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SinglePlayerActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void gameReset(View view) {
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 3;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Player2's Turn - Tap to play");
    }
}

