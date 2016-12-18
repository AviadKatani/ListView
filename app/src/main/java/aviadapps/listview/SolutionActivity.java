package aviadapps.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SolutionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView x1TV, promoteTV, nTV, SnTV;
    ListView listView;
    double x1Num, dNum, nNum, SnNum, qNum;
    String[] ShowNumbers;
    TextView promoterTV;
    Intent getIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        x1TV = (TextView) findViewById(R.id.x1TV);
        promoteTV = (TextView) findViewById(R.id.promoteTV);
        nTV = (TextView) findViewById(R.id.nTV);
        SnTV = (TextView) findViewById(R.id.SnTV);
        promoterTV = (TextView) findViewById(R.id.promoterTV);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        ShowNumbers = new String[20];
        getIntent = getIntent();
        x1Num = getIntent.getDoubleExtra("x1", 0);
        nTV.setText(0.0 + "");
        SnTV.setText(0.0 + "");
        if(getIntent.getFlags() == 1) {
            dNum = getIntent.getDoubleExtra("d", 0);
            promoterTV.setText("d = ");
            promoteTV.setText(String.valueOf(dNum));
        }
        else {
            qNum = getIntent.getDoubleExtra("d", 0);
            promoterTV.setText("q = ");
            promoteTV.setText(String.valueOf(qNum));
        }
        x1TV.setText(String.valueOf(x1Num));
        if(getIntent.getFlags() == 1) {
            for(int i = 0; i < 20; i++) {
                ShowNumbers[i] = String.valueOf((Math.ceil(x1Num + (i * dNum)) * 1000) / 1000);
            }
            ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ShowNumbers);
            listView.setAdapter(adapter);
        }
        else {
            ShowNumbers[0] = String.valueOf(x1Num);
            for(int i = 1; i < 20; i ++) {
                ShowNumbers[i] = String.valueOf(Math.ceil((Double.parseDouble(ShowNumbers[i - 1]) * qNum) * 1000) / 1000);
            }
            ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ShowNumbers);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        nNum = position + 1;
        nTV.setText(String.valueOf(nNum));
        if(getIntent.getFlags() == 1) {
            SnNum = nNum * (x1Num + Double.parseDouble(ShowNumbers[position])) / 2;
            SnNum = Math.ceil(SnNum * 1000) / 1000;
            SnTV.setText(String.valueOf(SnNum));
        }
        else {
            SnNum = (x1Num * (Math.pow(qNum, nNum) - 1)) / (qNum - 1);
            SnNum = Math.ceil(SnNum * 1000) / 1000;
            SnTV.setText(SnNum + "");
        }
    }
}
