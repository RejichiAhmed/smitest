package com.smi.test.data.repository.imp

import android.util.Log
import androidx.annotation.WorkerThread
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.smi.test.base.BaseRepository
import com.smi.test.data.model.Brands
import com.smi.test.data.model.Purchase
import com.smi.test.data.model.Statistic
import com.smi.test.data.repository.abs.BrandsRepository
import com.smi.test.global.helper.SharedPreferences
import com.smi.test.global.utils.TAG
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class BrandsRepositoryImp @Inject constructor(
    sharedPreferences: SharedPreferences
) : BaseRepository(sharedPreferences), BrandsRepository {

    private val database = FirebaseDatabase.getInstance()

    @WorkerThread
    override suspend fun getPurchase(userId: Long)= suspendCoroutine<Statistic> { continuation ->
        database.getReference("conversions").child("purchase").addValueEventListener(object :ValueEventListener {
            val post:ArrayList<Purchase> = ArrayList()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val pur = postSnapshot.getValue(Purchase::class.java)!!
                    post.add(pur)
                }
                var commission : Double = 0.0
                var amount : Double = 0.0
                var count : Int = 0
                var currency: String = "EUR"
                for (p in post) {
                    if (p.offerId == userId) {
                       if(p.commission != null ) commission += p.commission!!.toDouble()
                       if(p.amount?.toInt() != 0) amount += p.amount?.toDouble()!!
                        count ++
                        if(p.currency.isNullOrBlank()) currency = p.currency!!
                    }
                }
                continuation.resume(Statistic((Math.floor(amount * 100) / 100),(Math.floor(commission * 100) / 100),currency,count))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                continuation.resumeWithException(Exception(databaseError.toException()))
            }
        } )
    }


    @WorkerThread
    override suspend fun getAllBrands()= suspendCoroutine< ArrayList<Brands>> { continuation ->
        val post:ArrayList<Brands> = ArrayList()
        val brandsListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val brands = postSnapshot.getValue(
                        Brands::class.java
                    )
                    post.add(brands!!)

                }
                continuation.resume(post)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                continuation.resumeWithException(Exception(databaseError.toException()))
            }
        }
        database.getReference("brands").addValueEventListener(brandsListener)
    }

    @WorkerThread
    override suspend fun getPremiumBrands()= suspendCoroutine< ArrayList<Brands>> {continuation ->
        val post:ArrayList<Brands> = ArrayList()
        val brandsListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val brands = postSnapshot.getValue(
                        Brands::class.java
                    )
                    if(brands?.premium!!){
                        post.add(brands)
                    }

                }
                continuation.resume(post)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                continuation.resumeWithException(Exception(databaseError.toException()))
            }
        }
        database.getReference("brands").addValueEventListener(brandsListener)

    }

}