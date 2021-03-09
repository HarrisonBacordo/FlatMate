package com.harrisonbacordo.flatmate.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.harrisonbacordo.flatmate.data.models.Grocery
import com.harrisonbacordo.flatmate.data.models.GroceryList
import com.harrisonbacordo.flatmate.util.Keys
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GroceryApiImpl @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore): GroceryApi {
    override suspend fun addGroceryList(groceryList: GroceryList): GroceryList {
        return try {
            val sanitizedGroceryList = groceryList.copy(ownerId = firebaseAuth.currentUser!!.uid)
            val flatId = firestore.collection(Keys.Firestore.User.firestoreCollection)
                .document(firebaseAuth.currentUser!!.uid)
                .get()
                .await()
                .get(Keys.Firestore.User.flatId) as String
            firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document(flatId)
                .collection(Keys.Firestore.Flat.groceriesCollection)
                .document(groceryList.id.toString())
                .set(groceryList.toApiMap())
                .await()
            groceryList
        } catch (e: Exception) {
            TODO()
        }
    }

    override suspend fun updateGroceryList(groceryList: GroceryList): GroceryList {
        return try {
            val flatId = firestore.collection(Keys.Firestore.User.firestoreCollection)
                .document(firebaseAuth.currentUser!!.uid)
                .get()
                .await()
                .get(Keys.Firestore.User.flatId) as String
            firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document(flatId)
                .collection(Keys.Firestore.Flat.groceriesCollection)
                .document(groceryList.id.toString())
                .update(groceryList.toApiMap())
                .await()
            groceryList
        } catch (e: Exception) {
            TODO()
        }
    }

    override suspend fun deleteGroceryList(groceryList: GroceryList) {
        TODO("Not yet implemented")
    }

    override suspend fun addGrocery(grocery: Grocery, groceryList: GroceryList) {
        try {
            val flatId = firestore.collection(Keys.Firestore.User.firestoreCollection)
                .document(firebaseAuth.currentUser!!.uid)
                .get()
                .await()
                .get(Keys.Firestore.User.flatId) as String
            firestore
                .collection(Keys.Firestore.Flat.firestoreCollection)
                .document(flatId)
                .collection(Keys.Firestore.Flat.groceriesCollection)
                .document(groceryList.id.toString())
                .update(Keys.Firestore.GroceryList.groceries, FieldValue.arrayUnion(grocery))
                .await()
            return
        } catch (e: Exception) {
            TODO()
        }
    }

    override suspend fun updateGrocery(grocery: Grocery, groceryList: GroceryList): Grocery {
        TODO("Not yet implemented")
    }

    override suspend fun removeGrocery(grocery: Grocery, groceryList: GroceryList) {
        TODO("Not yet implemented")
    }
}

interface GroceryApi {
    suspend fun addGroceryList(groceryList: GroceryList): GroceryList
    suspend fun updateGroceryList(groceryList: GroceryList): GroceryList
    suspend fun deleteGroceryList(groceryList: GroceryList)
    suspend fun addGrocery(grocery: Grocery, groceryList: GroceryList)
    suspend fun updateGrocery(grocery: Grocery, groceryList: GroceryList): Grocery
    suspend fun removeGrocery(grocery: Grocery, groceryList: GroceryList)
}