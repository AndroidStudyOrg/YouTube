package org.shop.youtube

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout

class ExoPlayerMotionLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MotionLayout(context, attributeSet, defStyleAttr) {

    // targetView를 먼저 바깥에서 넘겨주고 그 담에 터치가 일어날 때에 gestureDetector가 만들어 질 수 있도록
    var touchTargetView: View? = null

    // VideoContainerView를 외부에서 받아와서 그 내부 영역에서 터치가 일어났을 때에만 touchInterceptor를 true로 리턴
    private val gestureDetector by lazy {
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

            /**
             * e1에서 e2 스크롤(onScroll이 일어날 때 ACTION_MOVE의 첫번째 지점부터 두번째 지점)
             * e1일 때가 targetView 내부에 있는지 확인
             * targetView의 left나 right, top, bottom을 e1에 있는 x, y 좌표랑 비교해서 4개 좌표안에 있는지 확인
             */
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                Log.e(
                    "ExoPlayerMotionLayout_onScroll",
                    "호출됨 e1_x: ${e1?.x}, e1_y: ${e1?.y}, return: ${
                        touchTargetView?.containTouchArea(
                            e1!!.x.toInt(),
                            e1!!.y.toInt()
                        )
                    }"
                )
                return touchTargetView?.containTouchArea(e1!!.x.toInt(), e1!!.y.toInt()) ?: false
            }
        })
    }

    // ExoPlayer의 터치이벤트를 가로채서 MotionLayout에 전달.
    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        // super 메소드 대신에 Gesture 이벤트를 받아서 스크롤 할 때에만 return true
        event?.let {
            return gestureDetector.onTouchEvent(event)
        } ?: return false
    }

    private fun View.containTouchArea(x: Int, y: Int): Boolean {
        Log.e(
            "Exo_containTouchArea",
            "left: ${this.left}, right: ${this.right}, top: ${this.top}, bottom: ${this.bottom}"
        )
        Log.e(
            "Exo_containTouchArea_return",
            "${(x in this.left..this.right && y in this.top..this.bottom)}"
        )
        return (x in this.left..this.right && y in this.top..this.bottom)
    }
}