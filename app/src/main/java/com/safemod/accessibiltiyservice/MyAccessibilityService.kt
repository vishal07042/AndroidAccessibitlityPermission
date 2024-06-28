package com.safemod.accessibiltiyservice

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import java.util.Locale

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
//        Log.d("MyAccessibilityServicefunkeandhar", "onAccessibilityEvent triggered onAccessibilityEvent")

        val source: AccessibilityNodeInfo = event?.source ?: return
//        Log.d("source", source.toString())


   val rootNode = rootInActiveWindow ?: return

//       Log.d("rootNode", rootNode.toString())
       rootNode::class.simpleName?.let { Log.d("typeOf rootNode", it) }
     val screenContent = StringBuilder()
        getChild(rootNode, screenContent)
        val packageNamme = rootNode.packageName
        val cclassNamme = rootNode.className
        Log.d("ClassName", cclassNamme.toString())
        Log.d("packageName", packageNamme.toString())
       Log.d("ScreenContent", screenContent.toString())
        val screenContentStr = screenContent.toString().lowercase(Locale.ROOT)

        if (packageNamme == "com.android.chrome") {
            Log.d("MyAccessibilityService", "Event from Chrome detected")
            performGlobalAction(GLOBAL_ACTION_HOME)

            // Get the root node of the active window
            val rootNode = rootInActiveWindow ?: return

            // Traverse the node tree to find the search bar
            if (isSearchBar(rootNode)) {
                Log.d("MyAccessibilityService", "Search bar detected")
            }
        }
        if(packageNamme == "com.google.android.youtube" ){
            Log.d("inside yooutbe", "onAccessibilityEvent: ")
            if(screenContent.toString() =="dhruv" || screenContent.toString().lowercase(Locale.ROOT) =="dhruv rathee"){
                performGlobalAction(GLOBAL_ACTION_HOME)
            }
        }

        if (packageNamme == "com.google.android.youtube") {
            Log.d("Inside YouTube", "onAccessibilityEvent: ")
            if (screenContentStr.contains("dhruv") || screenContentStr.contains("dhruv rathee")) {
                Log.d("MyAccessibilityService", "Found 'dhruv' or 'dhruv rathee', performing back action")
                performGlobalAction(GLOBAL_ACTION_HOME)
            }
        }


       if(packageNamme == "com.google.android.youtube"){
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Log.d("windowStateChanged", "onAccessibilityEvent: ")

            val arr = listOf("akshay","kumar");
            arr.map {
                if(screenContent.contains(it)){
                    Log.d("detactedWordFromArrary", "onAccessibilityEvent: ")
                    performGlobalAction(GLOBAL_ACTION_HOME)
                }
            }
        }}
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


    private fun isSearchBar(node: AccessibilityNodeInfo): Boolean {
        // Check if the node is the search bar
        if (node.className == "android.widget.EditText") {
            val contentDescription = node.contentDescription?.toString()
            val hintText = node.text?.toString()

            // You may need to refine this condition based on actual properties of the search bar
            if (contentDescription == "Search" || hintText == "Search or type web address") {
                return true
            }
        }

        // Recursively check child nodes
        for (i in 0 until node.childCount) {
            val child = node.getChild(i) ?: continue
            if (isSearchBar(child)) {
                return true
            }
        }

        return false
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