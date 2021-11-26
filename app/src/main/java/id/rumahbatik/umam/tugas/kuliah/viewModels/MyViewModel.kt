package id.rumahbatik.umam.tugas.kuliah.viewModels

import android.graphics.Color
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel(), Observable {

    @Bindable
    var task5 = MutableLiveData<Int>()

    init {
        task5.value = Color.BLACK
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) { }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) { }

}