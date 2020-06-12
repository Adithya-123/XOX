package com.example.xox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button[] []  buttons=new Button[3][3];
    private  boolean player1Turn=true;
    private int roundCounts=0;
    private int Player1points=0;
    private int Player2points=0;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private Button reset_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1=(TextView)findViewById(R.id.text_view_player1);
        textViewPlayer2=(TextView)findViewById(R.id.text_view_player2);

        reset_button=(Button)findViewById(R.id.Button_reset);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGames();
            }
        });

        buttons[0][0]=(Button)findViewById(R.id.Button_00);
        buttons[0][1]=(Button)findViewById(R.id.Button_01);
        buttons[0][2]=(Button)findViewById(R.id.Button_02);
        buttons[1][0]=(Button)findViewById(R.id.Button_10);
        buttons[1][1]=(Button)findViewById(R.id.Button_11);
        buttons[1][2]=(Button)findViewById(R.id.Button_12);
        buttons[2][0]=(Button)findViewById(R.id.Button_20);
        buttons[2][1]=(Button)findViewById(R.id.Button_21);
        buttons[2][2]=(Button)findViewById(R.id.Button_22);

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                buttons[i][j].setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Button b = (Button) v;

        if (!b.getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            b.setText("X");
        } else {
            b.setText("O");
        }

        roundCounts++;

        if (checkForWin())
        {
            if (player1Turn)
            {

                player1Wins();
            } else
                {
                player2Wins();
                }
        }
        else

            if (roundCounts==9)
            {
                draw();
            }

        else
            {
                player1Turn=!player1Turn;
            }
    }

    private void player1Wins() {

        Player1points++;
        Toast.makeText(this,"player 1 Wins",Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins() {

        Player2points++;
        Toast.makeText(this,"player 2 Wins",Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void draw()
    {
        Toast.makeText(this,"no player Won",Toast.LENGTH_LONG).show();
        resetBoard();
    }



    private void updatePointsText()
    {
        textViewPlayer1.setText("Player 1 :"+Player1points);
        textViewPlayer2.setText("Player 2 :"+Player2points);
    }

    private void resetBoard()
    {
      for (int i=0;i<3;i++)
          for(int j=0;j<3;j++)
              buttons[i][j].setText("");

          roundCounts=0;
          player1Turn=true;
    }
    private void resetGames() {
        Player1points=0;
        Player2points=0;
        updatePointsText();
        resetBoard();


    }


    private boolean checkForWin()
    {
        String[] [] field=new String[3][3];

        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                field[i][j]=buttons[i][j].getText().toString();


         for(int i=0;i<3;i++)
         {
             if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("") )
             {
                 return  true;
             }
         }
        for(int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("") )
            {
                return  true;
            }
        }

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
        {
            return true;
        }
        return false;
    }

}
