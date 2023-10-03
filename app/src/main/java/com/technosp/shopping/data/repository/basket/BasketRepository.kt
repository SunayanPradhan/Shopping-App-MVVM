package com.technosp.shopping.data.repository.basket

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.technosp.shopping.data.model.ProductBasket

interface BasketRepository {

    fun getAllProductsBasket(): CollectionReference

    fun getTargetProductsBasket(productBasket: ProductBasket): DocumentReference

    fun addProductsToBasket(productBasket: ProductBasket): Task<Void>

    fun deleteProducts(productBasket: ProductBasket): Task<Void>

    fun updateProductsPiece(productBasket: ProductBasket): Task<Void>

}