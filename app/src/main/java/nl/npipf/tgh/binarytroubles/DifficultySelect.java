package nl.npipf.tgh.binarytroubles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class DifficultySelect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_select);

        Button butEasy = (Button) findViewById(R.id.butDifficultyEasy);
        Button butNormal = (Button) findViewById(R.id.butDifficultyNormal);
        Button butHard = (Button) findViewById(R.id.butDifficultyHard);

        Intent intent = getIntent();
        final String levelSize = intent.getStringExtra("level_size");

        butEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBinaryGame(levelSize, "easy");
            }
        });

        butNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBinaryGame(levelSize, "normal");
            }
        });

        butHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBinaryGame(levelSize, "hard");
            }
        });
    }

    public void toBinaryGame(String levelSize, String difficulty){
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        intent.putExtra("level_size", levelSize);
        intent.putExtra("difficulty", difficulty);
        Toast.makeText(getApplicationContext(), "This may take a bit, please stand by", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.difficulty_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
