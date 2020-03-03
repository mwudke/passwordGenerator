package de.wudke.passwordGenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity<onDestroy> extends AppCompatActivity {
    PasswordGenerator pg = new PasswordGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView t;
        t = findViewById(R.id.text);
        t.setText("*********");

//        t.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });



        // Switch lowercase
        final Switch slc;
        slc = findViewById(R.id.switch_lowercase);
        pg.lc = slc.isChecked();
        slc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pg.lc = isChecked;
                pg.updateCharset();
            }
        });

        // Switch uppercase
        final Switch suc;
        suc = findViewById(R.id.switch_uppercase);
        pg.uc = suc.isChecked();
        suc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pg.uc = isChecked;
                pg.updateCharset();
            }
        });

        // Switch special
        final Switch ss;
        ss = findViewById(R.id.switch_special);
        pg.s = ss.isChecked();
        ss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pg.s = isChecked;
                pg.updateCharset();
            }
        });

        // Switch numbers
        final Switch sn;
        sn = findViewById(R.id.switch_numbers);
        pg.n = sn.isChecked();
        sn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pg.n = isChecked;
                pg.updateCharset();
            }
        });

        // Seekbar pwLength & Textview pwLength
        final TextView tvpl = findViewById(R.id.textView_pwLength);
        final SeekBar sbpwl = findViewById(R.id.seekBar_pwLength);
        sbpwl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvpl.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Button generate
        final Button b;
        b = findViewById(R.id.button_generate);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pg.charsetSelected()) {
                    t.setText(pg.generate(sbpwl.getProgress()));
                } else {
                    new AlertDialog.Builder(b.getContext())
                            .setTitle("Info")
                            .setMessage("you have to select at least one symbolset!")

                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();

                }
            }
        });

        // ImageButton coppy to clipboard
        final ImageButton ibctc;
        ibctc = findViewById(R.id.imageButton_coppyToClip);
        ibctc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);

                ClipData clip = ClipData.newPlainText("simple text", t.getText());
                clipboard.setPrimaryClip(clip);

                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "copied to Clipboard", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
