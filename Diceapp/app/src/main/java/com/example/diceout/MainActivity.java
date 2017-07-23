package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    //variable for text view
    TextView rollview;




    //variable for random number
    Random rand;

    //variables to hold the dice values

    int dice1;
    int dice2;
    int dice3;

    //list of dice values
    ArrayList<Integer> list;

    //Image views
    ImageView diceview1;
    ImageView diceview2;
    ImageView diceview3;


    //list of Imageview
    ArrayList<ImageView> Imagelistview;

    //text view for the final score
    TextView finalscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               rollDice(view);
            }
        });



        //creating a welcome message
        Toast.makeText(getApplicationContext(),"Welcome to Dice out",Toast.LENGTH_SHORT).show();

        rollview=(TextView) findViewById(R.id.Rollview);


        finalscore=(TextView) findViewById(R.id.Score);

        //logic for the random number genarator
        rand=new Random();

        //to initialize arraylist
        list=new ArrayList<Integer>();

        //to initialize ListImageView
        Imagelistview=new ArrayList<ImageView>();

        //initialise imageviews
        diceview1=(ImageView) findViewById(R.id.dice1);

        diceview2=(ImageView) findViewById(R.id.dice2);

        diceview3=(ImageView) findViewById(R.id.dice3);


        Imagelistview.add(diceview1);

        Imagelistview.add(diceview2);

        Imagelistview.add(diceview3);


    }


    public void rollDice(View view)
    {
        dice1=rand.nextInt(6)+1;

        dice2=rand.nextInt(6)+1;

        dice3=rand.nextInt(6)+1;


        list.clear();
        list.add(dice1);
        list.add(dice2);
        list.add(dice3);

        for(int diceofset=0;diceofset<3;diceofset++)
        {

            String imagename="die_"+list.get(diceofset)+".png";
            try
            {
                InputStream stream=getAssets().open(imagename);
                Drawable d= Drawable.createFromStream(stream,null);
                Imagelistview.get(diceofset).setImageDrawable(d);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


        }

        String finscore;

        if(dice1==dice2&&dice1==dice3)
        {
            finscore=" YOU WON 100";
        }

        else if(dice1==dice2||dice1==dice3)
        {
            finscore="YOU WON 50";
        }

        else
        {

            finscore=" LOST!!!!!";
        }


        finalscore.setText(finscore);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
