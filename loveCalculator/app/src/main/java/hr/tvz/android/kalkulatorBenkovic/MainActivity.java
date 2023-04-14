package hr.tvz.android.kalkulatorBenkovic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText editText1, editText2;
    Button buttonCalculate;
    TextView textViewResult;

    private Spinner spinnerColorMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        spinnerColorMode = findViewById(R.id.spinner_color_mode);

        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = editText1.getText().toString().trim().toLowerCase();
                String name2 = editText2.getText().toString().trim().toLowerCase();

                int lovePercentage = calculateLovePercentage(name1, name2);

                String result =  lovePercentage + "%";
                textViewResult.setText(result);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_modes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColorMode.setAdapter(adapter);

        // Set spinner listener
        spinnerColorMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                switch (selectedOption) {
                    case "Pink Mode":
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FFD1DC"));

                        // Set button color
                        buttonCalculate.setBackgroundColor(Color.parseColor("#800080"));
                        break;
                    case "Blue Mode":
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#BFEFFF"));

                        // Set button color
                        buttonCalculate.setBackgroundColor(Color.parseColor("#1E90FF"));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

    }

    private int calculateLovePercentage(String name1, String name2) {
        int lovePercentage = 0;

        String combinedNames = name1 + name2;
        int count = 0;
        for (int i = 0; i < combinedNames.length(); i++) {
            if (combinedNames.charAt(i) == 'l' ||
                    combinedNames.charAt(i) == 'o' ||
                    combinedNames.charAt(i) == 'v' ||
                    combinedNames.charAt(i) == 'e') {
                count++;
            }
        }

        if (count > 0) {
            lovePercentage = (count * 100) / 4;
        }

        if(lovePercentage>100){
            lovePercentage=100;
        }

        return lovePercentage;
    }


}