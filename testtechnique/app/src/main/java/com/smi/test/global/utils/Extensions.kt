package com.smi.test.global.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.SystemClock
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smi.test.R
import com.smi.test.global.enumeration.State
import com.smi.test.global.listener.DataAdapterListener
import com.smi.test.global.listener.PaginationStateListener
import com.squareup.picasso.Picasso
import androidx.databinding.*
import kotlinx.coroutines.CancellationException

/**
 * observe non null live data update
 *
 * @param owner
 * @param observer
 *
 */
fun <T> LiveData<T>.observeOnlyNotNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

/**
 * observe All live data update
 *
 * @param owner
 * @param observer
 *
 */
fun <T> LiveData<T>.observeAll(owner: LifecycleOwner, observer: (t: T?) -> Unit) {
    this.observe(owner, Observer {
        observer(it)
    })
}


/**
 * give default value for the live data
 *
 */
fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }


/**
 * property TAG extension for Loging
 *
 */
val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }





@BindingAdapter(value = ["imageUrl", "placeholder", "picasso"], requireAll = true)
fun setImageUrl(imageView: ImageView, imageUrl: String, placeHolder: Drawable, picasso: Picasso) {
    if (TextUtils.isEmpty(imageUrl)) {
        imageView.setImageDrawable(placeHolder);
    } else {
        when (imageView.scaleType) {
            ImageView.ScaleType.CENTER_CROP -> picasso.load(imageUrl).fit().centerCrop().placeholder(
                    placeHolder
            ).into(imageView)
            ImageView.ScaleType.CENTER_INSIDE -> picasso.load(imageUrl).fit().centerInside().placeholder(
                    placeHolder
            ).into(imageView)
            else -> picasso.load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView)
        }
    }
}

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T?) {
    data?.let {
        if (recyclerView.adapter is DataAdapterListener<*>) {
            (recyclerView.adapter as DataAdapterListener<T>).setData(it)
        }
    }
}


@BindingAdapter("pagedListAdapterState")
fun setRecyclerViewProperties(recyclerView: RecyclerView, state: State?) {
    if (recyclerView.adapter is PaginationStateListener) {
        (recyclerView.adapter as PaginationStateListener).setState(state ?: State.DONE)
    }
}

/**
 * ic_status_validated if user is connected to the Internet
 *
 * @param context Context to get resources and device specific display metrics
 * @return A boolean value
 */
fun Context?.isNetworkAvailable(): Boolean {
    if (this == null) return false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}
/**
 * used for suspend tryCatch
 * @param tryBlock  try block to execute
 * @param catchBlock  catch block to execute
 * @param handleCancellationExceptionManually cancellation exception will manually handled
 *
 */
suspend fun tryCatch(
    tryBlock: suspend () -> Unit,
    catchBlock: suspend (Throwable) -> Unit,
    handleCancellationExceptionManually: Boolean = false
) {
    try {
        tryBlock()
    } catch (e: Throwable) {
        if (e !is CancellationException ||
            handleCancellationExceptionManually
        ) {
            catchBlock(e)
        } else {
            throw e
        }
    }
}


/**
 * check if the target is a valid mail
 *
 * @return A boolean value
 */
fun CharSequence?.isValidEmail(): Boolean {
    return if (TextUtils.isEmpty(this)) {
        false
    } else {
        android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}

object LastClickTimeSingleton {
    var lastClickTime: Long = 0
}

fun View.setClickWithDebounce(action: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {

        override fun onClick(v: View) {
            if (Math.abs(SystemClock.elapsedRealtime() - LastClickTimeSingleton.lastClickTime) < 300L) return
            else action()
            LastClickTimeSingleton.lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

@BindingAdapter("onClickWithDebounce")
fun onClickWithDebounce(view: View, listener: View.OnClickListener) {
    view.setClickWithDebounce {
        listener.onClick(view)
    }
}
