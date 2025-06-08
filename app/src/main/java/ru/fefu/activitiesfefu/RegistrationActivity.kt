package ru.fefu.activitiesfefu

import android.os.Bundle
import android.widget.ArrayAdapter
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import android.text.TextPaint
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitiesfefu.databinding.ActivityRegisterBinding
import androidx.core.graphics.toColorInt

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // возвращаемся по стрелке
        binding.btnBack.setOnClickListener { finish() }

        // выпадающий список «Пол»
        val genders = listOf("Мужской", "Женский")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genders)
        binding.actGender.setAdapter(adapter)

        makeAgreementSpan()

        // обработка кнопки
        binding.btnContinue.setOnClickListener {
            Toast.makeText(this, "Регистрация…", Toast.LENGTH_SHORT).show()
        }
    }

    private fun makeAgreementSpan() {
        val full = getString(R.string.agreement_full)
        val privacy = getString(R.string.privacy)
        val agreement = getString(R.string.agreement)

        val span = SpannableString(full)
        val color = "#6200EE".toColorInt()

        // Политика конфиденциальности
        val startPrivacy = full.indexOf(privacy)
        val endPrivacy = startPrivacy + privacy.length

        val privacySpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@RegistrationActivity, "Политика", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = color
                ds.isUnderlineText = false
            }
        }
        span.setSpan(privacySpan, startPrivacy, endPrivacy, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Пользовательское соглашение
        val startAgreement = full.indexOf(agreement)
        val endAgreement = startAgreement + agreement.length

        val agreementSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@RegistrationActivity, "Соглашение", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = color
                ds.isUnderlineText = false
            }
        }
        span.setSpan(agreementSpan, startAgreement, endAgreement, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvAgreement.text = span
        binding.tvAgreement.movementMethod = LinkMovementMethod.getInstance()
    }

}
