package com.veryworks.android.properties;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    // 위젯 연결
    EditText editName, editEmail,editPassword;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);

        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);

        loadPreference();

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePref("name", editName.getText().toString());
                savePref("email", editEmail.getText().toString());
                savePref("password", editPassword.getText().toString());
            }
        });
    }

    // 저장해둔 설정값 가져오기
    private void loadPreference(){
        String name = sharedPreferences.getString("name","[none]");
        String email = sharedPreferences.getString("email","[none]");
        String password = sharedPreferences.getString("password","");

        editName.setText(name);
        editEmail.setText(email);
        editPassword.setText(password);
    }

    // 프리퍼런스 저장
    public void savePref(String key, String value){
        // 1. editor 꺼내기
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // 2. editor 를 통해서 키 값을 저장
        editor.putString(key, value);
        // 3. editor 커밋
        editor.commit();
    }


    // 삭제하기
    private void removePreferences(String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    // 전체 삭제하기
    private void removeAllPreferences(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
