package com.example.myapplication1;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity  implements android.view.View.OnClickListener {
      private android.widget.TextView tvUsername;
      private EditText etRealname;
      private RadioGroup sexGroup;
      private CheckBox cbjava, cbAndroid, cbDatabase;
      private RadioButton rbMale, rnFemale;

      @Override
      protected void onCreate(android.os.Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_info);
            //初始化界面控件
            //获取控件对象
            tvUsername = findViewById(R.id.tv_username);
            etRealname = findViewById(R.id.etrealname);
            sexGroup = findViewById(R.id.group_sex);
            cbjava = findViewById(R.id.cb_java);
            cbAndroid = findViewById(R.id.cb_android);
            cbDatabase = findViewById(R.id.cb_sql);
            rbMale = findViewById(R.id.rbtn_male);
            rnFemale = findViewById(R.id.rbtn_female);
            Intent intent = getIntent();
            if (intent != null) {
                  String name = intent.getStringExtra("username");
                  tvUsername.setText(name);

            }
            Button btnConfirm = findViewById(R.id.btn_confirm);
            btnConfirm.setOnClickListener(this);
            etRealname.setOnKeyListener(new android.view.View.OnKeyListener() {
                  @Override
                  public boolean onKey(android.view.View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                              InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                              if (imm != null && imm.isActive()) {
                                    imm.hideSoftInputFromWindow((v.getApplicationWindowToken()), 0);
                              }
                              return true;
                        }
                        return false;
                  }
            });
      }
      @Override
      public void onClick(android.view.View v) {
            if (v.getId() == R.id.btn_confirm) {
                  getInfo();
            }
      }
      public void getInfo() {
      String Username = tvUsername.getText().toString().trim();
      String realname= etRealname.getText().toString().trim();
            String sex="男";
            String favorite= "";
            int id= sexGroup.getCheckedRadioButtonId();
            if(id == R.id.rbtn_male){
                  sex ="男";
            }else {
                  sex = "女";

            }
            if(cbjava.isChecked()){
                  favorite +=cbjava.getText().toString() + ",";
            }
            if(cbAndroid.isChecked()){
                  favorite +=cbAndroid.getText().toString() + ",";
            }
            if(cbDatabase.isChecked()){
                  favorite +=cbDatabase.getText().toString() + ",";
            }
            String info ="用户名："+ Username +"\n姓名："
             +realname+"\n 性别" +sex+"\n喜欢的课程：" +favorite.trim().substring(0,favorite.trim().length() - 1);
            Toast.makeText(InfoActivity.this,info,Toast.LENGTH_LONG).show();
            //跳转到主页面
            Intent intent=new Intent( InfoActivity.this,MainActivity.class);
            startActivity(intent);
      }
}
