package aviadapps.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView etText;
    EditText etD, etX;
    Intent solution;
    double x1, promoter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etText = (TextView)findViewById(R.id.promoteTV);
        etD = (EditText)findViewById(R.id.etD);
        etX = (EditText)findViewById(R.id.etX);
        solution = new Intent(this, SolutionActivity.class);
    }

    public void radioClicked(View view) {
        if(view.getId() == R.id.btnRArith) {
            etText.setText("d");
            etD.setHint("Enter d here");
            solution.setFlags(1);
        }
        else {
            etText.setText("q");
            etD.setHint("Enter q here");
            solution.setFlags(0);
        }
    }

    public void calculateClicked(View view) {
        promoter = Double.parseDouble(etD.getText().toString());
        x1 = Double.parseDouble(etX.getText().toString());
        solution.putExtra("promoter", promoter);
        solution.putExtra("x1", x1);
        startActivity(solution);
    }
}
