package com.safemod.accessibiltiyservice

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

//class MyAccessibilityService : AccessibilityService() {
//
//    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
//        Log.d("MyAccessibilityService", "onAccessibilityEvent triggered")
//
//        val rootNode = rootInActiveWindow ?: return
//        val screenContent = StringBuilder()
//        getChild(rootNode, screenContent)
//        Log.d("ScreenContent", screenContent.toString())
//    }
//
//    private fun getChild(info: AccessibilityNodeInfo, content: StringBuilder) {
//        if (info.text != null) {
//            content.append(info.text.toString()).append("\n")
//        }
//        val childCount = info.childCount
//        for (i in 0 until childCount) {
//            val childNode = info.getChild(i)
//            if (childNode != null) {
//                getChild(childNode, content)
//                childNode.recycle()  // Recycle node to prevent memory leaks
//            }
//        }
//    }
//
//    override fun onInterrupt() {
//        // Handle service interruption if needed
//    }
//}




class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("MyAccessibilityServicefunkeandhar", "onAccessibilityEvent triggered onAccessibilityEvent")

//        val source: AccessibilityNodeInfo = event?.source ?: return
//        Log.d("source", source.toString())


   val rootNode = rootInActiveWindow ?: return

       Log.d("rootNode", rootNode.toString())
       rootNode::class.simpleName?.let { Log.d("typeOf rootNode", it) }
     val screenContent = StringBuilder()
        getChild(rootNode, screenContent)
       Log.d("ScreenContent", screenContent.toString())
    }

    private fun getChild(info: AccessibilityNodeInfo, content: StringBuilder) {
        if (info.text != null) {
            content.append(info.text.toString()).append("\n")
        }
        val childCount = info.childCount
        for (i in 0 until childCount) {
            val childNode = info.getChild(i)
            if (childNode != null) {
                getChild(childNode, content)
                childNode.recycle()  // Recycle node to prevent memory leaks
            }
        }
    }

    override fun onInterrupt() {
        // Handle service interruption if needed
        Log.d("MyAccessibilityServiceinnterupt", "onInterrupt triggered")
        Log.i("MyAccessibilityServiceinnterupt", "onInterrupt triggered")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("MyAccessibilityServicestart", "onServiceConnected triggered")
        Log.i("MyAccessibilityServicestart", "onServiceConnected triggered")
    }
}