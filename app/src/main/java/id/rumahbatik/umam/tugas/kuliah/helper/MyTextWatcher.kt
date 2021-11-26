package id.rumahbatik.umam.tugas.kuliah.helper

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher

abstract class MyTextWatcher : TextWatcher {

    private var delay: Long = 500
    private lateinit var editable: Editable
    private var debounce: Boolean = true
    private var handler: Handler = Handler()
    private lateinit var runnable: Runnable

    init {
        runnable = Runnable {
            task(editable)
            handler.removeCallbacks(runnable)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        handler.removeCallbacks(runnable)
    }

    override fun afterTextChanged(s: Editable?) {
        if ( debounce && s != null ) {
            this.editable = s
            handler.postDelayed(runnable, delay)
        }
    }

    abstract fun task(e: Editable?)
}