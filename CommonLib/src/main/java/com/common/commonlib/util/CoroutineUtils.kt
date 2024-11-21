package com.common.commonlib.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author: Jialin Huang
 * Description:协程
 * Date: 2024/11/21
 */

// 默认的主线程作用域
private val mainScope = CoroutineScope(Dispatchers.Main + Job())

// 在主线程中启动协程
fun launchOnMain(block: suspend CoroutineScope.() -> Unit): Job {
    return mainScope.launch {
        block()
    }
}

// 在IO线程中启动协程
fun launchOnIO(block: suspend CoroutineScope.() -> Unit): Job {
    return mainScope.launch(Dispatchers.IO) {
        block()
    }
}

// 在默认线程池中启动协程
fun launchOnDefault(block: suspend CoroutineScope.() -> Unit): Job {
    return mainScope.launch(Dispatchers.Default) {
        block()
    }
}

// 切换到主线程
suspend fun <T> withMainContext(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.Main) {
        block()
    }
}

// 切换到IO线程
suspend fun <T> withIOContext(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.IO) {
        block()
    }
}

// 切换到默认线程池
suspend fun <T> withDefaultContext(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.Default) {
        block()
    }
}
