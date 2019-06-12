package com.jwsharpe.kanyequest.controller

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.text.TextUtils
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

class VolleyController : Application() {
    private var mRequestQueue: RequestQueue? = null

    val requestQueue: RequestQueue?
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }

            return mRequestQueue
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        requestQueue!!.add(req)
    }

    companion object {
        val TAG = VolleyController::class.java
            .simpleName
        @get:Synchronized //        if (mInstance == null) {
        //            mInstance = new VolleyController();
        //        }//added this myself
        var instance: VolleyController? = null
            private set
    }
}

