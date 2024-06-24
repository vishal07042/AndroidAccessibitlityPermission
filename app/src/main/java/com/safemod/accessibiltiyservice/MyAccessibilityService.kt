package com.safemod.accessibiltiyservice

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.util.Log

class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.let {
            val source: AccessibilityNodeInfo? = event.source
            source?.let {
                val text = getTextFromNode(it)
                Log.d("AccessibilityService", "Text: $text")
            }
        }
    }

    override fun onInterrupt() {
        // Required method for the service, but you can leave it empty
    }

    private fun getTextFromNode(node: AccessibilityNodeInfo): String {
        val stringBuilder = StringBuilder()
        if (node.text != null) {
            stringBuilder.append(node.text)
        }
        for (i in 0 until node.childCount) {
            val child = node.getChild(i)
            stringBuilder.append(getTextFromNode(child))
        }
        return stringBuilder.toString()
    }
}