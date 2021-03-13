package br.iesb.mobile.icard.domain.product

data class Products(
    val id: Int,
    val name: String? = null,
    val imageUrl: String? = null,
    val price: String? = null
)