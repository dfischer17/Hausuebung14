package com.example.dodelschach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {
        Button pressedButton = (Button) v;

        // Wenn Button leer -> x setzen
        if (pressedButton.getText().toString().equals("")) {
            pressedButton.setText("X");
        }

        // Feld bereits belegt -> Toast anzeigen
        else {
            Toast.makeText(this.getApplicationContext(), "Dieses Feld wurde bereits belegt!", Toast.LENGTH_LONG).show();
        }

        Button button00 = findViewById(R.id.button00);
        Button button01 = findViewById(R.id.button01);
        Button button02 = findViewById(R.id.button02);
        Button button10 = findViewById(R.id.button10);
        Button button11 = findViewById(R.id.button11);
        Button button12 = findViewById(R.id.button12);
        Button button20 = findViewById(R.id.button20);
        Button button21 = findViewById(R.id.button21);
        Button button22 = findViewById(R.id.button22);

        char[][] board = new char[3][3];
        board[0][0] = button00.getText().toString().charAt(0);
        board[1][0] = button01.getText().toString().charAt(0);
        board[1][1] = button02.getText().toString().charAt(0);
        board[2][0] = button10.getText().toString().charAt(0);
        board[2][1] = button11.getText().toString().charAt(0);
        board[2][2] = button12.getText().toString().charAt(0);
        board[0][0] = button20.getText().toString().charAt(0);
        board[0][0] = button21.getText().toString().charAt(0);
        board[0][0] = button22.getText().toString().charAt(0);

    }

    private BestMove findBestMove(char[][] board) {
        int x = 0;
        int y = 0;
        int bestVal = Integer.MIN_VALUE;
        BestMove bestMove = new BestMove();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                // Wenn Feld ist leer
                if (board[i][j] == ' ') {

                    // Feld makieren
                    board[i][j] = 'O';

                    // Zug evaluieren
                    int moveVal = minimax(board, 0, false);

                    // Zug zurücksetzen
                    board[i][j] = ' ';

                    // TODO
                    if (moveVal > bestVal) {
                        // TODO
                        bestMove.setX(j);
                        bestMove.setY(i);
                        bestVal = moveVal;
                        return new BestMove(i, j);
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(char[][] board, int depth, Boolean isMax) {
        // TODO
        int score = evaluate(board);

        // Spieler gewinnt
        if (score == 1) return score;

        // Computer gewinnt
        if (score == 1) return score;

        // Unentschieden
        // TODO
        if (!isMovesLeft(board)) return 0;

        // Player Zug
        if (!isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    // Wenn Feld ist leer
                    if (board[i][j] == ' ') {

                        // Feld als Spieler makieren
                        board[i][j] = 'X';

                        // Board evaluieren
                        best = Math.max(best, minimax(board, depth + 1, !isMax));

                        // Zug zuruecksetzen
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }

        // Computer
        else {
            int worst = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    // Wenn Feld ist leer
                    if (board[i][j] == ' ') {

                        // Feld als Computer markieren
                        board[i][j] = 'O';

                        // Board evaluieren
                        worst = Math.max(worst, minimax(board, depth + 1, !isMax));

                        // Zug zuruecksetzen
                        board[i][j] = ' ';
                    }
                }
            }
            return worst;
        }
    }

    private int evaluate(char[][] board) {
        // Zeilen und Reihen durchlaufen
        for (int i = 0; i < 3; i++) {
            int counterX = 0;
            int counterO = 0;

            // Zeilen durchlaufen
            for (int j = 0; j < 3; j++) {

                if (board[j][i] == 'X') counterX++;
                else if (board[j][i] == 'O') counterO++;
            }

            // wenn 3 X in Reihe -> Spieler gewinnt -> return -1
            if (counterX == 3) return -1;

            // wenn 3 O in Reihe -> Computer gewinnt -> return 1
            else if (counterO == 3) return 1;

            // wenn keines der beiden -> unentschieden -> return 0
            else {
                // TODO
            }


            // Spalten durchlaufen
            for (int j = 0; j < 3; j++) {
                if (board[i][i] == 'X') counterX++;
                else if (board[i][j] == 'O') counterO++;
            }

            // wenn 3 X in Reihe -> Spieler gewinnt -> return -1
            if (counterX == 3) return -1;

            // wenn 3 O in Reihe -> Computer gewinnt -> return 1
            else if (counterO == 3) return 1;

            // wenn keines der beiden -> unentschieden -> return 0
            return 0;
        }
        return -99;
    }

    private boolean isMovesLeft(char[][] board) {
        int counter = 0;

        // Zeilen und Reihen durchlaufen
        for (int i = 0; i < 3; i++) {

            // Zeilen durchlaufen
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == ' ') counter++;
            }

            // Spalten durchlaufen
            for (int j = 0; j < 3; j++) {
                if (board[i][i] == ' ') counter++;
            }
        }
        return counter > 0;
    }
}
