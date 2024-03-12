package com.example.hiveapp.data.repository

import android.content.Context
import com.example.hiveapp.R
import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.domain.repository.HiveRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import org.koin.core.annotation.Single
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Single
class HiveRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFireStore: FirebaseFirestore,
    private val context: Context
): HiveRepository {
    override suspend fun getAllHives(): List<HiveModel> = suspendCoroutine { continuation ->
        val hiveList = mutableListOf<HiveModel>()

        firebaseAuth.currentUser?.let { currentUser ->
            firebaseFireStore.collection("hives")
                .document(currentUser.uid)
                .collection("hives")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val hiveData = document.data

                        hiveData?.let { data ->
                            val hive = HiveModel(
                                id = document.id,
                                uId = data["uId"] as? String ?: "",
                                name = data["name"] as? String ?: "",
                                type = (data["type"] as Long).toInt(),
                                familyType = (data["familyType"] as Long).toInt(),
                                breed = (data["breed"] as Long).toInt(),
                                line = data["line"] as? String ?: "",
                                year = (data["year"] as Long).toInt(),
                                state = (data["state"] as Long).toInt(),
                                note = data["note"] as? String ?: "",
                                lat = (data["lat"] as Double),
                                lng = (data["lng"] as Double),
                                created = (data["created"] as Long),
                                edited = (data["edited"] as Long)
                            )
                            hiveList.add(hive)
                        }
                    }
                    continuation.resume(hiveList)
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        } ?: continuation.resume(hiveList)
    }

    override suspend fun getHiveById(id: String): HiveModel? = suspendCancellableCoroutine { continuation ->
        firebaseAuth.currentUser?.let { currentUser ->
            firebaseFireStore.collection("hives")
                .document(currentUser.uid)
                .collection("hives")
                .document(id)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val hiveData = querySnapshot.data

                    hiveData?.let { data ->
                        val hive = HiveModel(
                            id = querySnapshot.id,
                            uId = currentUser.uid,
                            name = data["name"] as? String ?: "",
                            type = (data["type"] as Long).toInt(),
                            familyType = (data["familyType"] as Long).toInt(),
                            breed = (data["breed"] as Long).toInt(),
                            line = data["line"] as? String ?: "",
                            year = (data["year"] as Long).toInt(),
                            state = (data["state"] as Long).toInt(),
                            note = data["note"] as? String ?: "",
                            lat = (data["lat"] as Double),
                            lng = (data["lng"] as Double),
                            created = (data["created"] as Long),
                            edited = (data["edited"] as Long)
                        )
                        continuation.resume(hive)
                    }
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        } ?: continuation.resume(null)
    }

    override suspend fun insertHive(hive: HiveModel): AuthState = suspendCancellableCoroutine { continuation ->
        if (firebaseAuth.currentUser != null) {
            val hiveData = HiveModel(
                id = "",
                uId = firebaseAuth.currentUser!!.uid,
                name = hive.name,
                type = hive.type,
                familyType = hive.familyType,
                breed = hive.breed,
                line = hive.line,
                year = hive.year,
                state = hive.state,
                note = hive.note,
                lat = hive.lat,
                lng = hive.lng,
                created = hive.created,
                edited = hive.edited,
            )


            val docRef = firebaseFireStore.collection("hives").document(firebaseAuth.currentUser!!.uid).collection("hives").document()
            val id = docRef.id
            docRef.set(hiveData)

            continuation.resume(AuthState.Success(true, message = id))

//            firebaseFireStore.collection("hives")
//                .document(firebaseAuth.currentUser!!.uid)
//                .collection("hives")
//                .add(hiveData)
//                .addOnSuccessListener { documentReference ->
//                    val id = documentReference.id
//                    println("===========================")
//                    println("===========================")
//                    Log.d("DUPA", "STWORZONO UL: $id")
//                    println("===========================")
//                    println("===========================")
//                    continuation.resume(AuthState.Success(true, message = id))
//                }
//                .addOnFailureListener { exception ->
//                    continuation.resume(
//                        AuthState.Error("${context.getString(R.string.hive_state_creating_error)} $exception")
//                    )
//                }
        } else {
            continuation.resume(AuthState.Error(context.getString(R.string.hive_state_no_user)))
        }
    }

    override suspend fun deleteHive(id: String): AuthState = suspendCancellableCoroutine { continuation ->
        if (firebaseAuth.currentUser != null) {
            firebaseFireStore.collection("hives")
                .document(firebaseAuth.currentUser!!.uid)
                .collection("hives")
                .document(id)
                .delete()
                continuation.resume(AuthState.Success(true))

//                .addOnSuccessListener { documentReference ->
//                    continuation.resume(AuthState.Success(true))
//                }
//                .addOnFailureListener { exception ->
//                    continuation.resume(
//                        AuthState.Error("${context.getString(R.string.hive_state_creating_error)} $exception")
//                    )
//                }
        } else {
            continuation.resume(AuthState.Error(context.getString(R.string.hive_state_no_user)))
        }
    }

    override suspend fun getHivesLocations(): List<HiveLocationModel> = suspendCoroutine { continuation ->
        val hiveList = mutableListOf<HiveLocationModel>()

        firebaseAuth.currentUser?.let { currentUser ->
            firebaseFireStore.collection("hives")
                .document(currentUser.uid)
                .collection("hives")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val hiveData = document.data

                        hiveData?.let { data ->
                            val hive = HiveLocationModel(
                                id = document.id,
                                name = data["name"] as? String ?: "",
                                lat = (data["lat"] as Double),
                                lng = (data["lng"] as Double),
                            )
                            hiveList.add(hive)
                        }
                    }
                    continuation.resume(hiveList)
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        } ?: continuation.resume(hiveList)
    }

    override suspend fun updateHiveLocation (
        id: String,
        lat: Double,
        lng: Double
    ): AuthState = suspendCancellableCoroutine { continuation ->
        if (firebaseAuth.currentUser != null) {
            firebaseFireStore.collection("hives")
                .document(firebaseAuth.currentUser!!.uid)
                .collection("hives")
                .document(id)
                .update(
                    mapOf(
                        "lat" to lat,
                        "lng" to lng,
                    )
                )
                .addOnSuccessListener { documentReference ->
                    continuation.resume(AuthState.Success(true, message = "Zaktualizowano"))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(
                        AuthState.Error("${context.getString(R.string.hive_state_creating_error)} $exception")
                    )
                }
        } else {
            continuation.resume(AuthState.Error(context.getString(R.string.hive_state_no_user)))
        }
    }
}