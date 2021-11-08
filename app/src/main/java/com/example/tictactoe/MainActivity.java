package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean activePlayer = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer ? 0 : 1;
            counter.setTranslationY(-1000);

            if (activePlayer) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = false;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = true;
            }

            counter.animate().translationYBy(1000).setDuration(300);

            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                {
                    gameActive = false;

                    String winner = "";
                    if(!activePlayer)
                        winner = "Cross";
                    else
                        winner = "Circle";

                    Button playAgain = (Button)findViewById(R.id.button2);
                    TextView winnerText = (TextView) findViewById(R.id.textView);

                    winnerText.setText(winner + " has won");
                    playAgain.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                }

            }
        }

    }

    public void PlayAgain(View view){

        Button playAgain = (Button) findViewById(R.id.button2);
        TextView winnerText = (TextView) findViewById(R.id.textView);

        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);

        GridLayout grid = (GridLayout) findViewById(R.id.gridlayout);
        for(int i = 0; i < 9; i++) {
            ImageView image = (ImageView) grid.getChildAt(i);
            image.setImageDrawable(null);
        }

        Arrays.fill(gameState, 2);
        activePlayer = true;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}