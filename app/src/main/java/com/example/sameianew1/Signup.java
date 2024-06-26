package com.example.sameianew1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText idNumET;
    private EditText companyNameET;
    private EditText passowrdET;
    private EditText passowrdConET;
    private EditText phoneNumET;
    private EditText cityNameET;
    private EditText userNameET;
    private Button createAccountBTN;
    private int type; // 0 client  1 company
    private RadioGroup radioGroup;
    private RadioButton clientRadioBtn;
    FirebaseDatabase database;
    DatabaseReference refernce;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        type = 0;
        idNumET = findViewById(R.id.id_num_signup);
        companyNameET = findViewById(R.id.companyName_signup);
        passowrdET = findViewById(R.id.password_signup);
        passowrdConET = findViewById(R.id.passwordcon_signup);
        phoneNumET = findViewById(R.id.phone_num_signup);
        createAccountBTN = findViewById(R.id.createaccount_signup);
        cityNameET = findViewById(R.id.city_name_signup);
        firstNameET = findViewById(R.id.firstName_signup);
        lastNameET = findViewById(R.id.lastName_signup);
        radioGroup = findViewById(R.id.radio_group_signup);
        clientRadioBtn = findViewById(R.id.client_rb);
        userNameET = findViewById(R.id.userName_signup);


        refernce = FirebaseDatabase.getInstance().getReference();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.client_rb) {
                    companyNameET.setVisibility(View.GONE);
                    firstNameET.setVisibility(View.VISIBLE);
                    lastNameET.setVisibility(View.VISIBLE);
                    idNumET.setVisibility(View.VISIBLE);
                    type = 0;
                } else {
                    companyNameET.setVisibility(View.VISIBLE);
                    firstNameET.setVisibility(View.GONE);
                    lastNameET.setVisibility(View.GONE);
                    idNumET.setVisibility(View.GONE);
                    type = 1;
                }
            }
        });


        createAccountBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passowd = passowrdET.getText().toString();
                String passowrdCon = passowrdConET.getText().toString();
                String phonenum = phoneNumET.getText().toString();
                String city = cityNameET.getText().toString();
                String userName = userNameET.getText().toString();
                String firstName = firstNameET.getText().toString();
                String lastName = lastNameET.getText().toString();
                String id = idNumET.getText().toString();
                boolean allValid = true; //false;
                User user = null;
                boolean tr = false;
                int i = 0;
//                if (false && !tr&&i>0) {
//                     i=0;
//                    if (informitionisempty(passowd))
//                        passowrdET.setError("נא למלא את השדה ~סיסמה~!");
//                    if (informitionisempty(passowrdCon))
//                        Toast.makeText(Signup.this, "נא למלא את השדה ~אימות סיסמה~!", Toast.LENGTH_LONG).show();
//                    if (informitionisempty(phonenum))
//                        Toast.makeText(Signup.this, "נא למלא את השדה ~מספר טלפון~ !", Toast.LENGTH_LONG).show();
//                    if (informitionisempty(city))
//                        Toast.makeText(Signup.this, "נא למלא את השדה ~ישוב~!", Toast.LENGTH_LONG).show();
//                    if (informitionisempty(firstName))
//                        Toast.makeText(Signup.this, "נא למלא את השדה ~שם פרטי~!", Toast.LENGTH_LONG).show();
//                    if (informitionisempty(lastName))
//                        Toast.makeText(Signup.this, "נא למלא את השדה ~שם משפחה~!", Toast.LENGTH_LONG).show();
//                    if (informitionisempty(userName))
//                        Toast.makeText(Signup.this, "נא למלא את השדה ~שם משתמש~!", Toast.LENGTH_LONG).show();
//                    if (!informitionisempty(passowd))
//                        tr = true;
//                        else i++;
//                    if (!informitionisempty(passowrdCon))
//                        tr = true;
//                    else i++;
//                    if (!informitionisempty(phonenum))
//                        tr = true;
//                    else i++;
//                    if (!informitionisempty(city))
//                        tr = true;
//                    else i++;
//                    if (!informitionisempty(firstName))
//                        tr = true;
//                    else i++;
//                    if (!informitionisempty(lastName))
//                        tr = true;
//                    else i++;
//                    if (!informitionisempty(userName))
//                        tr = true;
//                    else i++;
//
//                }
//                allValid = false;
//                while (false && !allValid) {
//                    if (!isusernametrue(userName)) {
//                        Toast.makeText(Signup.this, "שם משתמש חייב להתחיל באות אנגלית", Toast.LENGTH_LONG).show();
//                        allValid = false;
//                    } else allValid = true;
//                }
//                allValid = false;
//                while (!allValid){
//                    if(!longusername(userName)){
//                        Toast.makeText(Signup.this, "שם משתמש ארוך יותר מדי", Toast.LENGTH_LONG).show();
//                    }
//                    else allValid = true;;
//                }
//                allValid = false;
//
//                while (!allValid) {
//                    if (!passcheck(passowd, passowrdCon)) {
//                        Toast.makeText(Signup.this, "סיסמאות לא תואמות!", Toast.LENGTH_LONG).show();
//                        allValid = false;
//                    } else allValid = true;
//                }
//                allValid = false;
//                while (!allValid) {
//                    if (isidtrue(id)) {
//                        Toast.makeText(Signup.this, "נא להקיש מספר תעודת זהות בן 9 מספרים", Toast.LENGTH_LONG).show();
//                        allValid = false;
//                    } else allValid = true;
//                }
//                allValid = false;
//                while (!allValid){
//                    if(!cityinput(city))
//                        allValid = false;
//                    else allValid=true;
//                }
//
//
//
                Intent intent = new Intent();
                intent.putExtra("type", type);
                intent.putExtra("userName", userName);
                intent.putExtra("password", passowd);

                if (type == 0) {
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    user = new PersonalUser(userName, passowd, phonenum, firstName, lastName, id, city,type);

                } else {
                    String companyName = companyNameET.getText().toString();
                    intent.putExtra("companyName", companyName);
                    user = new CompanyUser(userName, passowd, phonenum, companyName, city,type);
                }



                if (allValid) {
                    refernce.child("Users").child(user.getUserName()).setValue(user);
                    //refernce.child("UsersType").child(user.getUserName()).setValue("T" + type);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });


    }

    public static boolean passcheck(String fp, String secpass) {
        boolean check = false;
        if (fp.equals(secpass))
            check = true;
        return check;
    }

    //check if the 7kel is empty
    public static boolean informitionisempty(String fp) {
        boolean fp1 = false;
        if (fp.isEmpty())
            fp1 = true;
        return fp1;
    }

    public static boolean isidtrue(String id) {
        boolean tr = true;
        if (id.length() != 8)
            tr = false;
        return tr;
    }

    public static boolean isUserNameValid(String userName) {
        return true;
    }

    public static boolean isusernametrue(String user) {
        boolean tr = true;
        char n = 0;
        int ch = 0;
        for (int i = 0; i < user.length(); i++) {
            n= user.charAt(i);
            ch = n;

            if (ch > 122 || 90 < ch && ch< 97 || 73 > ch) {
                i = user.length();
                tr = false;

            }
        }return tr;

    }
    public static boolean longusername(String user){
        boolean tr = true;
        if (user.length()<10)
            tr =false;
        return tr;
    }
    public static boolean cityinput(String city){
        boolean flag=true;
        for(int i=0;i<city.length()&&flag;i++){
            char ch=city.charAt(i);
            if(ch>'ת'|| ch<'א'){
                flag =false;

            }
        }
        return flag;
    }
}
