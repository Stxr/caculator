package com.example.txr.caculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 22166 on 2016/7/22.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et;
    //按钮
    private Button bt_0;
    private Button bt_1;
    private Button bt_2;
    private Button bt_3;
    private Button bt_4;
    private Button bt_5;
    private Button bt_6;
    private Button bt_7;
    private Button bt_8;
    private Button bt_9;
    private Button bt_mul;
    private Button bt_dev;
    private Button bt_sub;
    private Button bt_add;
    private Button bt_equ;
    private Button bt_del;
    private Button bt_dot;
    private Button bt_c;
    private Button bt_hd;;
    boolean clear_flag;//清空标志位
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置editText read_only
        et = (EditText) findViewById(R.id.et_input);
        //et.setKeyListener(null);
        //button
        bt_0 = (Button) findViewById(R.id.bt_0);
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        bt_6 = (Button) findViewById(R.id.bt_6);
        bt_7 = (Button) findViewById(R.id.bt_7);
        bt_8 = (Button) findViewById(R.id.bt_8);
        bt_9 = (Button) findViewById(R.id.bt_9);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_del = (Button) findViewById(R.id.bt_del);
        bt_dev = (Button) findViewById(R.id.bt_dev);
        bt_sub = (Button) findViewById(R.id.bt_sub);
        bt_mul = (Button) findViewById(R.id.bt_mul);
        bt_dot = (Button) findViewById(R.id.bt_dot);
        bt_hd = (Button) findViewById(R.id.bt_hd);
        bt_equ = (Button) findViewById(R.id.bt_equ);
        bt_c = (Button) findViewById(R.id.bt_c);

        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_dev.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_sub.setOnClickListener(this);
        bt_dot.setOnClickListener(this);
        bt_hd.setOnClickListener(this);
        bt_equ.setOnClickListener(this);
        bt_c.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_mul.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String str = et.getText().toString();
        switch (view.getId()){
            case R.id.bt_0 :
            case R.id.bt_1 :
            case R.id.bt_2 :
            case R.id.bt_3 :
            case R.id.bt_4 :
            case R.id.bt_5 :
            case R.id.bt_6 :
            case R.id.bt_7 :
            case R.id.bt_8 :
            case R.id.bt_9 :
            case R.id.bt_dot :
            case R.id.bt_hd :
                if (clear_flag){
                    clear_flag = false;
                    et.setText("");
                }
                et.setText(str+((Button)view).getText());
                break;
            case R.id.bt_add :
            case R.id.bt_sub :
            case R.id.bt_mul :
            case R.id.bt_dev :
                et.setText(str+" "+((Button)view).getText()+" ");
                break;
            case R.id.bt_c :
                clear_flag =false;
                et.setText("");
                break;
            case R.id.bt_del :
                if (clear_flag){
                    clear_flag = false;
                    et.setText("");
                }else if(str!=null&&str.equals("")){
                    et.setText(str.substring(0,str.length()-1));
                }
            case R.id.bt_equ :
                getResult();
                break;
        }
    }
    //运算结果
    private void getResult(){
        String exp = et.getText().toString();
        if(exp == null||exp.equals("")){
            return;
        }
        if (exp.contains(" ")){
            return;
        }
        if(clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;
        double result = 0;
        String s1 = exp.substring(0,exp.indexOf(" "));//运算符前面的字符串
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        String s2 = exp.substring(exp.indexOf(" ")+3); //运算符后面的字符串
        if(!s1.equals("")&&!s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                result = d1 + d2;
            } else if (op.equals("-")) {
                result = d1 - d2;
            } else if (op.equals("×")) {
                result = d1 * d2;
            } else if (op.equals("÷")) {
                if (d2 == 0) ;
                et.setText("error");
            } else {
                result = d1 / d2;
            }
//            et.setText((int)result);
            if (!s1.contains(".") && !s2.contains(".")) {
                int r = (int) result;
                et.setText(r + "");
            } else {
                et.setText(result + "");
            }
        }else if (!s1.equals("")&&s2.equals("")){
            et.setText(exp);
        } else if (s1.equals("")&&!s2.equals("")){
            double d1 = 0;
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")){
                result= d1+d2;
            }else if (op.equals("-")){
                result = d1-d2;
            }else if (op.equals("×")){
                result = d1*d2;
            }else if (op.equals("÷")){
                if(d2==0);
                et.setText("error");
            }else {
                result = d1/d2;
            }
            if (!s2.contains(".")) {
                int r = (int) result;
                et.setText(r + "");
            } else {
                et.setText(result + "");
            }
        } else {
            et.setText("");
        }
    }
}
