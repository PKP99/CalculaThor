package com.example.calculathor;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num0, add, sub, mul, div, equa, clear, decimal, del, chk;
    EditText current;
    TextView equation;
    TextView result;
    View ScrollView1;
    Timer timer;
    int[] I;
    double[] A = new double[20];
    char[] op = new char[19];
    int i=0;
    int z =0;
    double res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num0=findViewById(R.id.num0);
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        num3=findViewById(R.id.num3);
        num4=findViewById(R.id.num4);
        num5=findViewById(R.id.num5);
        num6=findViewById(R.id.num6);
        num7=findViewById(R.id.num7);
        num8=findViewById(R.id.num8);
        num9=findViewById(R.id.num9);
        add=findViewById(R.id.add);
        sub=findViewById(R.id.sub);
        div=findViewById(R.id.div);
        mul=findViewById(R.id.mul);
        equa=findViewById(R.id.equal);
        clear=findViewById(R.id.clear);
        decimal=findViewById(R.id.dec);
        del=findViewById(R.id.del);
        chk = findViewById(R.id.chk);
        I = new int[] {R.drawable.light, R.drawable.thor};
        timer = new Timer();

        ScrollView1 = findViewById(R.id.view);

        equation=findViewById(R.id.equation);
        current=findViewById(R.id.curr);
        result=findViewById(R.id.result);

        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s0", current.getText()));
            }
        });
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s1", current.getText()));
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s2", current.getText()));
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s3", current.getText()));
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s4", current.getText()));
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s5", current.getText()));
            }
        });
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s6", current.getText()));
            }
        });
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s7", current.getText()));
            }
        });
        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s8", current.getText()));
            }
        });
        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s9", current.getText()));
            }
        });
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText(String.format("%s.", current.getText()));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        A[i] = Double.parseDouble(current.getText().toString());
                        op[i] = '+';
                        i++;
                        equation.setText(equation.getText().toString() + current.getText().toString() + "+");
                        current.getText().clear();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    A[i] = Double.parseDouble(current.getText().toString());
                    op[i] = '-';
                    i++;
                    equation.setText(equation.getText().toString() + current.getText().toString() + "-");

                    current.getText().clear();
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    A[i] = Double.parseDouble(current.getText().toString());
                    op[i] = '*';
                    i++;
                    equation.setText(equation.getText().toString() + current.getText().toString() + "*");

                    current.getText().clear();
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    A[i] = Double.parseDouble(current.getText().toString());
                    op[i] = '/';
                    i++;
                    equation.setText(equation.getText().toString() + current.getText().toString() + "/");

                    current.getText().clear();
            }
        });
        equa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A[i] = Double.parseDouble(current.getText().toString());
                i++;

                double[] ref = new double[20];
                ref = Arrays.copyOfRange(A, 0, i);
                char[] refop = new char[19];
                refop = Arrays.copyOfRange(op, 0, i-1);
                int[] pos = new int[20];

                    pos = Search('/', refop);
                    if (pos.length > 0) {
                        for (int j = 0; j < pos.length; j++) {
                            double temp = ref[pos[j]] / ref[pos[j] + 1];
                            ref = rmvele(pos[j], ref, temp);
                            refop = rmvop(pos[j], refop);
                            pos = repos(pos, j);
                        }
                        pos = null;
                    }

                    pos = Search('*', refop);
                    if (pos.length > 0) {
                        for (int j = 0; j < pos.length; j++) {
                            double temp = ref[pos[j]] * ref[pos[j] + 1];
                            ref = rmvele(pos[j], ref, temp);
                            refop = rmvop(pos[j], refop);
                            pos = repos(pos, j);
                        }
                        pos = null;
                    }


                    pos = Search('+', refop);
                    if (pos.length > 0) {
                        for (int j = 0; j < pos.length; j++) {

                            double temp = ref[pos[j]] + ref[pos[j] + 1];
                            ref = rmvele(pos[j], ref, temp);
                            refop = rmvop(pos[j], refop);

                            pos = repos(pos, j);

                        }
                        pos = null;
                    }

                    pos = Search('-', refop);
                    if (pos.length > 0) {
                        for (int j = 0; j < pos.length; j++) {
                            double temp = ref[pos[j]] - ref[pos[j] + 1];
                            ref = rmvele(pos[j], ref, temp);
                            refop = rmvop(pos[j], refop);
                            pos = repos(pos, j);
                        }
                        pos = null;
                    }
                    ScrollView1.setBackground(ContextCompat.getDrawable(getApplicationContext(),I[0]));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ScrollView1.setBackground(ContextCompat.getDrawable(getApplicationContext(),I[1]));
                    }
                }, 1000);
                    res = ref[0];
                    String s = String.valueOf(res);
                    result.setText(result.getText().toString() + s);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current.getText().clear();
                result.setText(null);
                equation.setText(null);
                A = Clrele(A, A.length);
                op = Clrop(op, op.length);
                i=0;
            }
        });
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current.getText().length() >0){
                    if(z==A.length){
                        z=0;
                        current.setText(String.valueOf(A[z]));
                        z++;
                    }
                    else{
                        current.setText(String.valueOf(A[z]));
                        z++;
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Enter some values first..", Toast.LENGTH_SHORT).show();
                }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current.length()!=0) {
                    String text = current.getText().toString();
                    current.setText(text.substring(0, text.length() - 1));
                }
                else{
                    Toast.makeText(MainActivity.this, "First write something to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private int[] repos(int[] pos, int j) {
        for(int k=j+1;k<pos.length;k++){
            pos[k]=pos[k]-1;
        }
        return pos;
    }

    private double[] rmvele(int pos, double[] reftemp, double temp) {
        double[] ref= new double[reftemp.length-1];
        reftemp[pos]=temp;
        int k =0;
        for(int j=0; j< reftemp.length; j++){
            if(j==(pos+1)){
                continue;
            }
            ref[k] = reftemp[j];
            k++;
        }
        return ref;
    }
    private char[] rmvop(int pos, char[] refoptemp) {
        char[] ref= new char[refoptemp.length-1];
        int k=0;
        for(int j=0; j< refoptemp.length; j++){
            if(j==pos){
                continue;
            }
            ref[k] = refoptemp[j];
            k++;
        }
        return ref;
    }

    private int[] Search(char x, char[] op) {
        int[] ret= new int[10];
        int z=0;
        for(int si = 0; si<op.length; si++){
            if(op[si]==x){
                ret[z]=si;
                z++;
            }
        }
        int[] slice = new int[z];
        slice = Arrays.copyOfRange(ret, 0, z);
        return slice;
    }
    private char[] Clrop(char[] op, int length) {
        char[] op2 = new char[1];
        op2= op;
        return op2;
    }

    private double[] Clrele(double[] a, int length) {
        double[] b = new double[1];
        b= a;
        return b;
    }
}