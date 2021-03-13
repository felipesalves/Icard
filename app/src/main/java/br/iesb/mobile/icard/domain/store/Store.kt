package br.iesb.mobile.icard.domain.store


data class Store(
    val id: Int,
    val name: String? = null,
    val imageUrl: String? = null,
    val loyaltyPoints: String? = null,
    val latitude: Double,
    val longitude: Double,
    val primaryColor: String? = null,
    val secondColor: String? = null
)