package id.rumahbatik.umam.tugas.kuliah

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.rumahbatik.umam.tugas.kuliah.databinding.ActivityTugas5Binding
import id.rumahbatik.umam.tugas.kuliah.helper.Constants
import id.rumahbatik.umam.tugas.kuliah.helper.MyTextWatcher
import java.util.*

class Tugas5Activity : AppCompatActivity() {

    private var errorCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTugas5Binding = ActivityTugas5Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.toolbar.title = resources.getString(R.string.title_tugas_5)
        setSupportActionBar(binding.toolbar)

        val dialogAvailableColors = AlertDialog.Builder(this)
            .setTitle(R.string.dialog_available_color_title)
            .setCancelable(true).setSingleChoiceItems(Constants.AVAILABLE_COLORS, -1) { d, p ->
                run {
                    binding.includeContent.textViewTheText.setTextColor(Color.parseColor(Constants.AVAILABLE_COLORS[p]))
                    binding.includeContent.editTextInputColorName.setText(Constants.AVAILABLE_COLORS[p])
                    binding.includeContent.editTextInputColorName.setSelection(binding.includeContent.editTextInputColorName.text?.length ?: 1)
                    d.dismiss()
                }
            }

        val dialogErrorColor = AlertDialog.Builder(this)
            .setTitle(R.string.dialog_error_color_title)
            .setMessage(R.string.dialog_error_color_message)
            .setCancelable(true)
            .setPositiveButton("OK") { d, _ -> d.dismiss() }

        binding.includeContent.editTextInputColorName.addTextChangedListener(object : MyTextWatcher() {
            override fun task(e: Editable?) {
                if ( e.isNullOrBlank() || e.isNullOrEmpty() ) {
                    return
                }
                val i: Int = Constants.AVAILABLE_COLORS.indexOf(e.toString().toLowerCase(Locale.ROOT))
                if ( i > -1 ) {
                    binding.includeContent.textViewTheText.setTextColor(Color.parseColor(Constants.AVAILABLE_COLORS[i]))
                } else {
                    if ( errorCount > 2 ) {
                        dialogAvailableColors.show()
                        errorCount = 0
                    } else {
                        dialogErrorColor.show()
                        errorCount++
                    }
                }
            }
        })
    }

}