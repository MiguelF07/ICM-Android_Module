package com.example.homework1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edtPhoneNo;
    Map<String,Contact> speedDials;
    Button sd1;
    Button sd2;
    Button sd3;
    Button delB;
    Gson gson;
    String json;
    SharedPreferences sharedPref;
    SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);


        String sp1 = sharedPref.getString("1",null);
        String sp2 = sharedPref.getString("2",null);
        String sp3 = sharedPref.getString("3",null);

        prefsEditor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(null);

        if(sp1 == (null)) {
            prefsEditor.putString("1", json);
        }
        if(sp2 == (null)) {
            prefsEditor.putString("2", json);
        }
        if(sp3 == (null)) {
            prefsEditor.putString("3", json);
        }
        prefsEditor.commit();

        edtPhoneNo = (EditText) findViewById(R.id.phoneInput);

        sd1 = (Button)findViewById(R.id.speeddial1);
        sd2 = (Button)findViewById(R.id.speeddial2);
        sd3 = (Button)findViewById(R.id.speeddial3);

        delB = (Button)findViewById(R.id.backspace);

        updateDials();

        sd1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog("1");
                return true;
            }
        });

        sd2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog("2");
                return true;
            }
        });

        sd3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog("3");
                return true;
            }
        });

        delB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                edtPhoneNo.setText("");
                return true;
            }
        });
    }

    public void buttonClickEvent(View v) {
        System.out.println("started");
        String phoneNo = edtPhoneNo.getText().toString();
        try {

            switch (v.getId()) {
                case R.id.number0:
                    phoneNo += "0";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.number1:
                    phoneNo += "1";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.number2:
                    phoneNo += "2";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.number3:
                    phoneNo += "3";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.number4:
                    phoneNo += "4";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.number5:
                    phoneNo += "5";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.number6:
                    phoneNo += "6";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.number7:
                    phoneNo += "7";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.number8:
                    phoneNo += "8";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.number9:
                    phoneNo += "9";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.numberhash:
                    phoneNo += "#";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.plusbutton:
                    phoneNo += "+";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.backspace:
                    String copyPhone = phoneNo;
                    StringBuffer sb= new StringBuffer(copyPhone);
                    sb.deleteCharAt(sb.length()-1);
                    edtPhoneNo.setText(sb.toString());
                    break;
                case R.id.call:
                    if (!phoneNo.trim().equals("")) {
                        Boolean isHash = false;
                        if (phoneNo.subSequence(phoneNo.length() - 1, phoneNo.length()).equals("#")) {
                            phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                            String callInfo = "tel:" + phoneNo + Uri.encode("#");
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse(callInfo));
                            startActivity(callIntent);
                        } else {
                            String callInfo = "tel:" + phoneNo;
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse(callInfo));
                            startActivity(callIntent);
                        }
                    }
                    break;
                case R.id.speeddial1:
                    gson = new Gson();
                    json = sharedPref.getString("1", "");
                    Contact obj1 = gson.fromJson(json, Contact.class);

                    if(obj1!=null)
                    {
                        edtPhoneNo.setText(obj1.getNumber());
                    }
                    break;
                case R.id.speeddial2:
                    gson = new Gson();
                    json = sharedPref.getString("2", "");
                    Contact obj2 = gson.fromJson(json, Contact.class);

                    if(obj2!=null)
                    {
                        edtPhoneNo.setText(obj2.getNumber());
                    }
                    break;
                case R.id.speeddial3:
                    gson = new Gson();
                    json = sharedPref.getString("3", "");
                    Contact obj3 = gson.fromJson(json, Contact.class);

                    if(obj3!=null)
                    {
                        edtPhoneNo.setText(obj3.getNumber());
                    }
                    break;
            }

        } catch (Exception ex) {
        }

    }

    public void updateDials() {
        gson = new Gson();
        json = sharedPref.getString("1", "");
        Contact obj1 = gson.fromJson(json, Contact.class);

        gson = new Gson();
        json = sharedPref.getString("2", "");
        Contact obj2 = gson.fromJson(json, Contact.class);

        gson = new Gson();
        json = sharedPref.getString("3", "");
        Contact obj3 = gson.fromJson(json, Contact.class);

        if(obj1!=null) {
            sd1.setText(obj1.getName());
        }
        if(obj2!=null) {
            sd2.setText(obj2.getName());
        }
        if(obj3!=null) {
            sd3.setText(obj3.getName());
        }
    }

    public void showDialog(String option) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        adb.setView(inflater.inflate(R.layout.speed_dial2, null));
        AlertDialog alertDialog = adb.show();
        Button saveButton = (Button)alertDialog.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String name = ((EditText)alertDialog.findViewById(R.id.nameInput2)).getText().toString();
                String number = ((EditText)alertDialog.findViewById(R.id.numberInput2)).getText().toString();

                if(name != "" && number != "") {
                    gson = new Gson();
                    json = gson.toJson(new Contact(name,number));
                    prefsEditor.putString(option, json);
                    prefsEditor.commit();
                    alertDialog.cancel();
                    updateDials();

                }
            }
        });
        Button cancelButton = (Button)alertDialog.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                alertDialog.cancel();
            }
        });
    }


}