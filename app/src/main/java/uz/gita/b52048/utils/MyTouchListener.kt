package uz.gita.b52048.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import uz.gita.b52048.constants.SideEnum
import uz.gita.b52048.constants.SideEnum.*
import kotlin.math.abs

class MyTouchListener(context: Context) : View.OnTouchListener {
    private val myGestureDetector = GestureDetector(context, MyGestureListener())
    private var myMovementSideListener: ((SideEnum) -> Unit)? = null

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        myGestureDetector.onTouchEvent(event)
        return true
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float
        ): Boolean {
            if (abs(e1.x - e2.x) > 100 || abs(e1.y - e2.y) > 100) {
                if (abs(e1.x - e2.x) < abs(e1.y - e2.y)) {
                    if (e2.y > e1.y) myMovementSideListener?.invoke(UP)
                    else myMovementSideListener?.invoke(DOWN)
                } else {
                    if (e2.x > e1.x) myMovementSideListener?.invoke(RIGHT)
                    else myMovementSideListener?.invoke(LEFT)
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }

    fun setMyMovementSideListener(block: (SideEnum) -> Unit) {
        myMovementSideListener = block
    }
}