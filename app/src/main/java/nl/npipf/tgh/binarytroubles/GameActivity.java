package nl.npipf.tgh.binarytroubles;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Size;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String levelSize = intent.getStringExtra("level_size");
        String levelDifficulty = intent.getStringExtra("difficulty");

        TextView txtGameInfo = (TextView) findViewById(R.id.textGameInfo);
        txtGameInfo.setText(levelSize + " " + levelDifficulty.substring(0,1).toUpperCase() + levelDifficulty.substring(1));

        int length = Integer.parseInt(levelSize.substring(0, 2));
        int width = Integer.parseInt(levelSize.substring(3));
        int difficulty = 20;
        if(levelDifficulty.equals("easy")){
            difficulty = 50;
        } else if(levelDifficulty.equals("normal")){
            difficulty = 60;
        } else if(levelDifficulty.equals("hard")){
            difficulty = 70;
        }
        Binary puzzle = new Binary(length, width, difficulty);
        int[][] board = puzzle.getBoard();
        int[][] solution = puzzle.getSolution();

        TableLayout table = (TableLayout) findViewById(R.id.tableLayoutGame);
        TwoDScrollView scrollView = (TwoDScrollView) findViewById(R.id.TwoDScrollGame);
        if(table.getWidth() >= scrollView.getWidth()){
            table.setGravity(Gravity.NO_GRAVITY);
        }
        for(int[] row : board){
            TableRow tr = new TableRow(this);
            tr.setBackgroundColor(Color.BLACK);
            tr.setPadding(2, 1, 2, 1);

            TableRow.LayoutParams llp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            llp.setMargins(2, 2, 2, 2);

            for(int cellint : row){
                LinearLayout cell = new LinearLayout(this);
                cell.setBackgroundColor(Color.WHITE);
                cell.setLayoutParams(llp);

                TextView tv = new TextView(this);
                String txt = Integer.toString(cellint);
                if(txt.equals("-1"))
                    txt = " ";
                tv.setText(txt);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
                tv.setPadding(3, 2, 3, 2);

                cell.addView(tv);
                tr.addView(cell);
            }

            table.addView(tr);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
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
