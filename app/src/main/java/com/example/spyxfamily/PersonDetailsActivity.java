package com.example.spyxfamily;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonDetailsActivity extends AppCompatActivity {


    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();  // Show back button on app
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_person_details);
        person = (Person) getIntent().getSerializableExtra("PERSON_SERIALIZED");
        String name = person.getName();
        String description = person.getDescription();
        int image = person.getImagePath();

        // Views
        ImageView profileImage = this.findViewById(R.id.profileImageFull);
        TextView profileName = this.findViewById(R.id.profileNameText);
        TextView profileDescription = this.findViewById(R.id.descriptionText);

        // Set image, name and full description
        profileImage.setImageResource(image);
        profileName.setText(name);
        profileDescription.setText(description);

        Toast.makeText(this, "This is " + person.getName() + "!", Toast.LENGTH_LONG).show();
    }


    // Actionbar (back button) calls this function on click
    public boolean onOptionsItemSelected(MenuItem item){
        // send back button signal on click
        onBackPressed();
        return true;
    }
}
