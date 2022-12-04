package com.example.unscrambleword;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PlayGame extends AppCompatActivity {

    TextView score;
    static TextView showword;
    MaterialButton submit;
    ImageView dec;
    static TextView getText;
    static TextView showRound;
    static TextView showChances;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        score = findViewById(R.id.showscore);
        showword = findViewById(R.id.showWord);
        submit = findViewById(R.id.btn);
        dec = findViewById(R.id.corw);
        getText = findViewById(R.id.player1);
        showRound = findViewById(R.id.showRound);
        showChances = findViewById(R.id.showChances);

        ArrayList<String>list = new ArrayList<>();
        list.add("WORDSCAPES");
        list.add("WORDLE");
        list.add("SCRABBLE");
        list.add("CROSSWORDS");
        list.add("COMPLICATED");
        list.add("QUIZZES");
        list.add("CODING");
        list.add("WANDER");
        list.add("DISTINCT");
        list.add("EXPRESS");
        list.add("COMMAND");
        list.add("SIGNAL");
        list.add("PARTICULAR");
        list.add("IRONIC");
        list.add("SYNONYMS");
        list.add("OBJECTIVE");
        list.add("DIVISIBLE");
        list.add("EDITOR");
        list.add("MASTERY");
        list.add("SOME");
        list.add("SCHEDULE");
        list.add("CULTURE");
        list.add("COUNTRY");
        list.add("INDIA");
        list.add("LIBRARY");
        list.add("EXPECT");
        list.add("MEET");
        list.add("STORY");
        list.add("IMPROVE");
        list.add("TOPICS");
        list.add("FRIENDS");
        list.add("TRAVEL");
        list.add("HOBBY");
        list.add("GREAT");
        list.add("ADVANCE");
        list.add("PEOPLE");
        list.add("BECAUSE");
        list.add("MOMENT");
        list.add("ALTHOUGH");
        list.add("POLICY");
        list.add("PROBABLY");
        list.add("BEHIND");
        list.add("REMAIN");
        list.add("SUGGEST");
        list.add("RAISE");
        list.add("FORMER");
        list.add("ECONOMY");
        list.add("MARKET");
        list.add("INCIDENT");
        list.add("ITSELF");
        list.add("EITHER");
        list.add("QUITE");
        list.add("PICTURE");
        list.add("DESCRIBE");
        list.add("PERSONAL");
        list.add("CENTURY");
        list.add("ENERGY");
        list.add("REALIZE");
        list.add("MATERIAL");
        list.add("FUTURE");

        final int[] count = {0};
        final int[] chances = {3};
        ArrayList<String> ScrambleUNscramble = new ArrayList<>();
        shuffling(list,ScrambleUNscramble);
        final int[] sc = {0};
        showword.setText(ScrambleUNscramble.get(1));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(count[0]==10)
                {   count[0]=0;
                    chances[0]=3;
                    checkRounds(sc[0],count[0],chances[0]);
                }
                else
                {
                    String inp = getText.getText().toString().trim();
                    if (inp.isEmpty()) {
                        Toast.makeText(PlayGame.this, "Please enter word!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        setBlank();
                        inp = inp.toUpperCase();
                        String checks = ScrambleUNscramble.get(0).toUpperCase();
                        if (inp.equals(checks))
                        {
                            setBlank();
                            String arr[] = (score.getText().toString().trim()).split(" ");
                            int scores = Integer.parseInt(arr[2]);
                            scores += 10;
                            score.setText(" Score - " + scores);
                            sc[0] = scores;
                            dec.setImageResource(R.drawable.correctlogo);
                            removeFromList(ScrambleUNscramble);
                            shuffling(list,ScrambleUNscramble);
                            setWord(ScrambleUNscramble);
                            setChances(chances[0]);
                            count[0] += 1;
                            if(count[0]==10)
                            {
                                count[0]=0;
                                chances[0]=3;
                                checkRounds(sc[0],count[0],chances[0]);
                            }
                            else {
                                setRound(count[0]);
                            }
                        }
                        else
                        {
                            setBlank();
                            dec.setImageResource(R.drawable.crosssym);
                            chances[0] = countChance(chances[0]);
                            boolean b=chanceOver(chances[0]);
                            if(b==true)
                            {
                                chances[0]=3;
                                count[0]+=1;
                                setRound(count[0]);
                                setChances(chances[0]);
                                setBlank();
                                removeFromList(ScrambleUNscramble);
                                shuffling(list,ScrambleUNscramble);
                                setWord(ScrambleUNscramble);
                            }
                            else
                            {
                                setChances(chances[0]);
                            }
                        }
                    }
                }
            }
        });
    }
    private void checkRounds(int sc,int count,int chances)
    {
        DialogBox dialogbox = new DialogBox(PlayGame.this,"Your have Scored "+sc+" Points in 10 Rounds",PlayGame.this);
        dialogbox.setCancelable(false);
        dialogbox.show();
        score.setText(" Score - 0");
        setRound(count);
        setChances(chances);
        setBlank();
    }
    private static void shuffling(ArrayList<String>list,ArrayList<String>ScrambleUNscramble)
    {
        Random random = new Random();
        int num = random.nextInt(60);
        String str = list.get(num);
        ScrambleUNscramble.add(list.get(num));
        List<String> character = Arrays.asList(str.split(""));
        Collections.shuffle(character);
        String scrumbble="";
        for(String s:character)
            scrumbble+=s;
        ScrambleUNscramble.add(scrumbble);
    }
    private static void setWord(ArrayList<String>Scramble)
    {
        showword.setText(Scramble.get(1));
    }
    private static void removeFromList(ArrayList<String>Scamble)
    {
        Scamble.remove(1);
        Scamble.remove(0);
    }
    private static int countChance(int count)
    {
        count-=1;
        return count;
    }
    private static boolean chanceOver(int count)
    {
        if(count==0)
            return true;
        else
            return false;
    }
    private static void setRound(int count) { showRound.setText(" Round - "+count); }
    private static void setChances(int chances)
    {
        showChances.setText(" Chances - "+chances);
    }
    private static void setBlank()
    {
        getText.setText("");
    }
}