package nl.npipf.tgh.binarytroubles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button but10x10 = (Button) findViewById(R.id.but10x10);
        Button but14x14 = (Button) findViewById(R.id.but14x14);
        Button but20x20 = (Button) findViewById(R.id.but20x20);

        but10x10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDifficultyScreen("10x10");
            }
        });

        but14x14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDifficultyScreen("14x14");
            }
        });

        but20x20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDifficultyScreen("20x20");
            }
        });
    }

    public void toDifficultyScreen(String levelSize){
        Intent intent = new Intent(getApplicationContext(), DifficultySelect.class);
        intent.putExtra("level_size", levelSize);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
