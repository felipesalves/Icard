package br.iesb.mobile.icard.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.room.*
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.domain.LoginLocalData.Person
import br.iesb.mobile.icard.repository.local.Dao.PersonDao
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
