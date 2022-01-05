package pam.latihan.uas_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper dbhelper;
    Button buttonsave;
    EditText nama, telp, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dbhelper = new DBHelper(this);
        nama = findViewById(R.id.namakontak);
        telp = findViewById(R.id.notelp);
        email = findViewById(R.id.email);
        buttonsave = findViewById(R.id.buttonsave);

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kontak WHERE nama = '" +
                getIntent().getStringExtra("nama")+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            telp.setText(cursor.getString(1).toString());
            email.setText(cursor.getString(2).toString());
        }

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.execSQL("UPDATE kontak SET nama='" +
                        nama.getText().toString() +"', telp='" +
                        telp.getText().toString() +"', email='" +
                        email.getText().toString()+"' WHERE nama = '" +
                        getIntent().getStringExtra("nama")+"'");
                Toast.makeText(UpdateActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                MainActivity.mainact.RefreshList();
                finish();
            }
        });

    }
}
