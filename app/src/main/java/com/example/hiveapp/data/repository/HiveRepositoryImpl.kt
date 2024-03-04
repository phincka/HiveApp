package com.example.hiveapp.data.repository

import android.util.Log
import com.example.hiveapp.data.dao.HiveDao
import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.domain.repository.HiveRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Single
class HiveRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFireStore: FirebaseFirestore,
    private val hiveDao: HiveDao,
    private val ioDispatcher: CoroutineDispatcher,
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
                    Log.w("FIREBASE_LOG", "Error getting documents.", exception)
                    continuation.resumeWithException(exception)
                }
        } ?: continuation.resume(hiveList)
    }


    override suspend fun getHiveById(id: String): HiveModel? {
        return hiveDao.getHiveById(id)
    }
    override suspend fun insertHive(hive: HiveModel) = withContext(ioDispatcher) {
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

            firebaseFireStore.collection("hives")
                .document(firebaseAuth.currentUser!!.uid)
                .collection("hives")
                .add(hiveData)
                .addOnSuccessListener { documentReference ->
                    Log.d("FIREBASE_LOG", "Nowy dokument został pomyślnie dodany. ID: ${documentReference.id}")
                }
                .addOnFailureListener { exception ->
                    Log.d("FIREBASE_LOG", "Błąd podczas dodawania nowego dokumentu: ${exception.message}")
                }
        }

    }

    override suspend fun updateHive(hive: HiveModel) = withContext(ioDispatcher) {
        hiveDao.updateHive(hive)
    }

    override suspend fun deleteHives(hives: List<HiveModel>) = withContext(ioDispatcher) {
        hiveDao.deleteHives(hives)
    }

    override suspend fun getHivesLocations(): List<HiveLocationModel> {
        return hiveDao.getHivesLocations()
    }

    override suspend fun getLocationByHiveId(id: Int): List<HiveLocationModel> {
        return hiveDao.getLocationByHiveId(id)
    }

    override suspend fun updateHiveLocation(id: Int, lat: Double, lng: Double) = withContext(ioDispatcher) {
        hiveDao.updateHiveLocation(id, lat, lng)
    }
}