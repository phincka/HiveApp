package com.example.hiveapp.hiveRepoTests

import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.domain.usecase.hive.GetAllHivesUseCase
import com.example.hiveapp.domain.usecase.hive.GetHiveByIdUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetHivesTest {
    private lateinit var getAllHivesUseCase: GetAllHivesUseCase
    private lateinit var getHiveByIdUseCase: GetHiveByIdUseCase
    private lateinit var fakeHiveRepository: FakeHiveRepository


    @Before
    fun setUp() {
        fakeHiveRepository = FakeHiveRepository()
        getAllHivesUseCase = GetAllHivesUseCase(fakeHiveRepository)
        getHiveByIdUseCase = GetHiveByIdUseCase(fakeHiveRepository)

        val hivesToInsert = mutableListOf<HiveModel>()
        ('a'..'z').forEachIndexed { index, number ->
            hivesToInsert.add(
                HiveModel(
                    id = number.code,
                    uId = number.code,
                    name = number.toString(),
                    type = number.code,
                    familyType = number.code,
                    breed = number.code,
                    line = number.toString(),
                    year = number.code,
                    state = number.code,
                    note = number.toString(),
                    lat = number.code.toDouble(),
                    lng = number.code.toDouble(),
                    created = number.code.toLong(),
                    edited = number.code.toLong()
                )
            )
        }
        hivesToInsert.shuffle()
        runBlocking {
            hivesToInsert.forEach { fakeHiveRepository.insertHive(it) }
        }
    }

    @Test
    fun `Get all hives`() = runBlocking {
        val allHives = getAllHivesUseCase()
        val result: List<HiveModel> = runBlocking { getAllHivesUseCase() }

        assertEquals(allHives, result)
    }

    @Test
    fun `Get hive by id`() = runBlocking {
        val hiveId = 122
        val hiveById = getHiveByIdUseCase(hiveId)

        assertNotNull(hiveById)
    }
}