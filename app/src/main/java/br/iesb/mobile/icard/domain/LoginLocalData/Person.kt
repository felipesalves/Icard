package br.iesb.mobile.icard.domain.LoginLocalData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey
    var id: Int = 0,
    var email: String = "",
    var isLogin: Boolean
)