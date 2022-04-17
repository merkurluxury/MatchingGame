package com.example.matchinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


import com.squareup.picasso.Picasso;

public class Game4x4Activity extends AppCompatActivity implements TaskCompleted {
    String URL_STRING = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    ArrayList<String> img_urls;
    final int NUM_IMGS = 8;

    AlertDialog.Builder builder;
    ArrayList<ImageButton> buttons;
    Map<ImageButton, Card> hm;
    Set<ImageButton> matchedCards;

    TextView puan;
    Chronometer sure;

    boolean isBoardLocked;
    ImageButton firstCard;
    ImageButton secondCard;

    TextView flips;
    TextView matches;
    int flip_count = 0;
    int match_count = 0;
    int toplam_puan = 0;
    Button shuffle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4x4);

        builder = new AlertDialog.Builder(this);

        flips = findViewById(R.id.textView1);
        matches = findViewById(R.id.textView2);

        sure = (Chronometer) findViewById(R.id.sure);
        sure.setFormat("Süre %s");
        puan = findViewById(R.id.puan);
        puan.setText("Puan : " + Integer.toString(toplam_puan));



        flips.setText("Çevirme : " + Integer.toString(flip_count));
        matches.setText("Eşleşme : " + Integer.toString(match_count));

        img_urls = new ArrayList<>();

        shuffle = (Button) findViewById(R.id.button_shuffle);

        new AsyncComplex(Game4x4Activity.this).execute(URL_STRING);
    }

    @Override
    public void onTaskComplete(String result){
        extractImageUrls(result);

        buttons = new ArrayList<>();
        initializeBoard(buttons);
        sure.start();
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(ImageButton b : buttons){
                    Random random = new Random();
                    int idx = random.nextInt(buttons.size());
                    if(!matchedCards.contains(b) && !matchedCards.contains(buttons.get(idx))){
                        Card temp = hm.get(b);
                        hm.put(b, hm.get(buttons.get(idx)));
                        hm.put(buttons.get(idx), temp);
                    }
                }
            }
        });
    }
    private void extractImageUrls(String result){
        img_urls.add("https://images.pexels.com/photos/2246476/pexels-photo-2246476.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        img_urls.add("https://images.pexels.com/photos/691668/pexels-photo-691668.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        img_urls.add("https://images.pexels.com/photos/3052361/pexels-photo-3052361.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        img_urls.add("https://images.pexels.com/photos/853199/pexels-photo-853199.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        img_urls.add("https://images.pexels.com/photos/2049422/pexels-photo-2049422.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        img_urls.add("https://images.pexels.com/photos/2090645/pexels-photo-2090645.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        img_urls.add("https://images.pexels.com/photos/1402787/pexels-photo-1402787.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        img_urls.add("https://images.pexels.com/photos/807598/pexels-photo-807598.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
    }
    private void initializeBoard(ArrayList<ImageButton> buttons){
        buttons.add((ImageButton)findViewById(R.id.imageButton1));
        buttons.add((ImageButton)findViewById(R.id.imageButton2));
        buttons.add((ImageButton)findViewById(R.id.imageButton3));
        buttons.add((ImageButton)findViewById(R.id.imageButton4));
        buttons.add((ImageButton)findViewById(R.id.imageButton5));
        buttons.add((ImageButton)findViewById(R.id.imageButton6));
        buttons.add((ImageButton)findViewById(R.id.imageButton7));
        buttons.add((ImageButton)findViewById(R.id.imageButton8));
        buttons.add((ImageButton)findViewById(R.id.imageButton9));
        buttons.add((ImageButton)findViewById(R.id.imageButton10));
        buttons.add((ImageButton)findViewById(R.id.imageButton11));
        buttons.add((ImageButton)findViewById(R.id.imageButton12));
        buttons.add((ImageButton)findViewById(R.id.imageButton13));
        buttons.add((ImageButton)findViewById(R.id.imageButton14));
        buttons.add((ImageButton)findViewById(R.id.imageButton15));
        buttons.add((ImageButton)findViewById(R.id.imageButton16));
        Collections.shuffle(buttons, new Random());
        hm = new HashMap<>();
        matchedCards = new HashSet<>();
        isBoardLocked = false;
        firstCard = null;
        for(int i = 0; i < buttons.size(); i++) {

            final int img_id = i % img_urls.size();
            final ImageButton card = buttons.get(i);
            hm.put(card, new Card(img_id, true));

            card.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    if(!canFlipCard(card))
                        return;
                    flips.setText("Çevirme : " + Integer.toString(++flip_count));
                    if(firstCard == null){
                        firstCard = card;
                        Picasso.get().load(img_urls.get(hm.get(firstCard).img_id)).into(firstCard);
                        return;
                    }
                    if(hm.get(firstCard).img_id == hm.get(card).img_id){
                        Picasso.get().load(img_urls.get(hm.get(card).img_id)).into(card);
                        processCardMatch(firstCard, card);
                    }
                    else{
                        processCardNoMatch(firstCard, card);
                    }
                }

                private boolean canFlipCard(ImageButton card){
                    return !isBoardLocked && (card != firstCard) && !matchedCards.contains(card);
                }

                private void processCardMatch(ImageButton card1, ImageButton card2){
                    matchedCards.add(card1);
                    matchedCards.add(card2);
                    card1.setEnabled(false);
                    card2.setEnabled(false);
                    matches.setText("Eşleşme : " + matchedCards.size()/2);
                    toplam_puan = toplam_puan+10;
                    puan.setText("Puan : " + Integer.toString(toplam_puan));
                    firstCard = null;
                    if(matchedCards.size() == 2*NUM_IMGS){
                        sure.stop();
                        SharedPreferences settings = getSharedPreferences("appName",0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("sured", sure.getText()+"");
                        editor.putString("puand", puan.getText().toString());
                        editor.commit();
                        builder.setMessage("Tekrar Oynamak İstiyor Musunuz ?")
                                .setCancelable(false)
                                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Kazandınız!");
                        alert.show();
                    }
                }

                private void processCardNoMatch(ImageButton card1, ImageButton card2){
                    isBoardLocked = true;
                    firstCard = card1;
                    secondCard = card2;
                    if (toplam_puan>=2){
                        toplam_puan = toplam_puan - 2;
                        puan.setText("Puan : " + Integer.toString(toplam_puan));
                    }
                    Picasso.get().load(img_urls.get(hm.get(secondCard).img_id)).into(secondCard);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable (){
                        @Override
                        public void run(){
                            firstCard.setImageResource(0);
                            secondCard.setImageResource(0);
                            firstCard = null;
                            secondCard = null;
                            isBoardLocked = false;
                        }
                    }, 1000);
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }
}
