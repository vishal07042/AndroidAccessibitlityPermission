package com.safemod.accessibiltiyservice





//
//
//import android.accessibilityservice.AccessibilityService
//import android.accessibilityservice.AccessibilityServiceInfo
//import android.text.TextUtils
//import android.util.Log
//import android.view.accessibility.AccessibilityEvent
//import android.view.accessibility.AccessibilityNodeInfo
//import org.json.JSONArray
//import org.json.JSONException
//import org.json.JSONObject
//
//class MyAccessibilityService : AccessibilityService() {
//
//    override fun onAccessibilityEvent(event: AccessibilityEvent) {
//        Log.d(TAG, "onAccessibilityEvent")
//
//        val source = event.source
//        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED && !event.className?.contains("AlertDialog")!!) {
//            return
//        }
//        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED && (source == null || source.className != "android.widget.TextView")) {
//            return
//        }
//        if (source != null) {
//            if (event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED && TextUtils.isEmpty(source.text)) {
//                return
//            }
//        }
//
//        val eventText: List<CharSequence> = if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//            event.text
//        } else {
//            listOf(source?.text ?: "hhello")
//        }
//
//        val text = processUSSDText(eventText)
//
//        if (TextUtils.isEmpty(text)) return
//
//        // Close dialog
//        performGlobalAction(GLOBAL_ACTION_BACK) // This works on 4.1+ only
//
//        if (text != null) {
//            Log.d(TAG, text)
//        }
//        // Handle USSD response here
//
//        // Extract and log the JSON hierarchy of the window
//        if (rootInActiveWindow != null) {
//            val jsonObject = getTextViews(rootInActiveWindow)
//            Log.d(TAG, "TextViews JSON: $jsonObject")
//        }
//    }
//
//    private fun processUSSDText(eventText: List<CharSequence>): String? {
//        for (s in eventText) {
//            val text = s.toString()
//            // Return text if text is the expected ussd response
//            if (true) {
//                return text
//            }
//        }
//        return null
//    }
//
//    private fun getTextViews(info: AccessibilityNodeInfo?): JSONObject? {
//        if (info != null) {
//            val jsonObject = JSONObject()
//            try {
//                jsonObject.put("Class", info.className)
//
//                if (info.className == "android.widget.TextView") {
//                    jsonObject.put("Text", info.text)
//                    Log.d("TextView", "Class: ${info.className}, Text: ${info.text}")
//                    return jsonObject
//                } else {
//                    val array = JSONArray()
//                    for (i in 0 until info.childCount) {
//                        val child = getTextViews(info.getChild(i))
//                        if (child != null) {
//                            array.put(child)
//                        }
//                    }
//                    jsonObject.put("Child", array)
//                }
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//            return jsonObject
//        } else {
//            return null
//        }
//    }
//
//    override fun onInterrupt() {}
//
//    override fun onServiceConnected() {
//        super.onServiceConnected()
//        Log.d(TAG, "onServiceConnected")
//        val info = AccessibilityServiceInfo().apply {
//            flags = AccessibilityServiceInfo.DEFAULT
//            packageNames = arrayOf("com.android.phone")
//            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
//            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
//        }
//        serviceInfo = info
//    }
//
//    companion object {
//        private const val TAG = "MyAccessibilityService"
//    }
//}
//
//
//
//
//
////
//import android.accessibilityservice.AccessibilityService
//import android.view.accessibility.AccessibilityEvent
//import android.util.Log
//import android.view.accessibility.AccessibilityNodeInfo
//
//class MyAccessibilityService : AccessibilityService() {
//
//    companion object {
//        private const val TAG = "MyAccessibilityService"
//    }
//
//    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
//        event?.let {
//            val source: AccessibilityNodeInfo = it.source ?: return
//            Log.d("wwe", "onAccessibilityEvent: $source")
//            Log.d("wwe2", "onAccessibilityEvent: $it")
//        }
//        val source: AccessibilityNodeInfo = event?.source ?: return
//        Log.d(TAG, "onAccessibilityEvent: $source")
////        Log.d(TAG, "onAccessibilityEvent", event.accessibility)
//
//        val eventText: String = when (event.eventType) {
//            AccessibilityEvent.TYPE_VIEW_CLICKED -> "Clicked: "
//            AccessibilityEvent.TYPE_VIEW_FOCUSED -> "Focused: "
//            else -> ""
//        }
//        Log.d("hello",eventText + source.text)
//    }
//
//    override fun onInterrupt() {
//        // Handle interruption
//    }
//}










import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo






class MyAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        fun getChild(info: AccessibilityNodeInfo) {
            val i = info.childCount
            for (p in 0 until i) {
                val n = info.getChild(p)
                if (n != null) {
                    n.viewIdResourceName
                    Log.d("sabkuch", "getChild: $n.viewIdResourceName ")
                    if (n.text != null) {
                        n.text.toString()
                        Log.d("text", n.text.toString())
                    }
                    getChild(n)
                }
            }
        }


        try {
    val parentNodeInfo = event?.source ?: return
            getChild(parentNodeInfo)
            Log.d("parentnodeInfo", parentNodeInfo.toString())

            Log.d("tttag", "Event Name : " + AccessibilityEvent.eventTypeToString(event.getEventType()));


}catch(e:Exception){
    Log.d(e.toString(),"hello")

}}







    override fun onInterrupt() {
        // Handle service interruption if needed
    }
}
