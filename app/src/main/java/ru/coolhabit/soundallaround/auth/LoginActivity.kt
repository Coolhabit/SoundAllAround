package ru.coolhabit.soundallaround.auth

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.coolhabit.soundallaround.MainActivity
import ru.coolhabit.soundallaround.R

private const val SIGN = "Войти"
private const val MESSAGE_SIGN = "Введите данные для входа"

class LoginActivity : AppCompatActivity() {

    private lateinit var buttonSign: Button
    private lateinit var buttonReg: Button
    private lateinit var root: ConstraintLayout
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonSign = findViewById(R.id.sign_btn)
        buttonReg = findViewById(R.id.reg_btn)
        root = findViewById(R.id.root_element)

        database = Firebase.database.reference

        buttonReg.setOnClickListener {
            showRegWindow()
        }

        buttonSign.setOnClickListener {
            showSignUpWindow()
        }
    }

    private fun showRegWindow() {

        val reg_name: EditText = findViewById(R.id.name_field)
        val reg_surname: EditText = findViewById(R.id.surname_field)
        val reg_age: EditText = findViewById(R.id.age_field)
        val reg_phone: EditText = findViewById(R.id.phone_field)
        val reg_email: EditText = findViewById(R.id.email_field)
        val reg_password: EditText = findViewById(R.id.password_field)

            if (checkAllFields()) {
            //регистрация пользователя
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(
                    reg_email.text.toString(),
                    reg_password.text.toString()
                )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        writeNewUser(
                            firebaseUser.uid,
                            reg_name.text.toString(),
                            reg_surname.text.toString(),
                            reg_age.text.toString(),
                            reg_phone.text.toString(),
                            reg_email.text.toString(),
                            reg_password.text.toString()
                        )
                        Snackbar.make(root, "Пользователь добавлен", Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(root, "Не удалось зарегистрироваться", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }

    private fun showSignUpWindow() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(SIGN)
        builder.setMessage(MESSAGE_SIGN)

        val inflater = LayoutInflater.from(this)
        val signInWindow = inflater.inflate(R.layout.sign_window, null)
        builder.setView(signInWindow)

        val email: EditText = signInWindow.findViewById(R.id.email_field)
        val password: EditText = signInWindow.findViewById(R.id.password_field)

        builder.setNegativeButton("Отменить", DialogInterface.OnClickListener { builder, id ->
            builder.dismiss()
        })
        builder.setPositiveButton("Войти") { dialog, which ->
            if (TextUtils.isEmpty(email.text.toString())) {
                Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            if (TextUtils.isEmpty(password.text.toString())) {
                Snackbar.make(root, "Введите пароль", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Snackbar.make(root, "Не удалось войти", Snackbar.LENGTH_SHORT).show()
                    }
                }
        }
        builder.show()
    }

    private fun writeNewUser(
        userId: String,
        name: String,
        surname: String,
        age: String,
        phone: String,
        email: String,
        pass: String
    ) {
        val user = User(userId, name, surname, age, phone, email, pass)

        database.child("users").child(userId).setValue(user)
    }

    private fun checkAllFields(): Boolean {

        val reg_name: EditText = findViewById(R.id.name_field)
        val reg_surname: EditText = findViewById(R.id.surname_field)
        val reg_age: EditText = findViewById(R.id.age_field)
        val reg_phone: EditText = findViewById(R.id.phone_field)
        val reg_email: EditText = findViewById(R.id.email_field)
        val reg_password: EditText = findViewById(R.id.password_field)

        if(reg_name.length() == 0) {
            reg_name.error = "Name is needed!"
            return false
        }
        if (reg_surname.length() == 0) {
            reg_name.error = "Surname is needed!"
            return false
        }
        if (reg_age.length() == 0) {
            reg_name.error = "Age is needed"
            return false
        } else if (reg_age.text.toString().toInt() < 18) {
            reg_name.error = "Age is under 18!"
            return false
        }
        if (reg_phone.length() == 0) {
            reg_name.error = "Phone number is needed!"
            return false
        }
        if (reg_email.length() == 0) {
            reg_name.error = "Email is needed!"
            return false
        }
        if (reg_password.length() == 0) {
            reg_name.error = "Password is needed!"
            return false
        } else if (reg_password.length() < 6) {
            reg_name.error = "Minimum 6 character password!"
            return false
        } else if (!reg_password.text.toString().matches(".*[A-Z].*".toRegex())) {
            reg_name.error = "Password must contain 1 Upper-case character!"
            return false
        } else if (!reg_password.text.toString().matches(".*[a-z].*".toRegex())) {
            reg_name.error = "Password must contain 1 Lower-case character!"
            return false
        } else if (!reg_password.text.toString().matches(".*[0-9].*".toRegex())) {
            reg_name.error = "Password must contain 1 Number!"
            return false
        }

        return true
    }
}