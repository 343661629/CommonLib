package com.common.commonlib.model

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/22
 */
sealed class UiState {
    data object Idle : UiState()
    data object Loading : UiState()
    data class Success(val message: String) : UiState()
    data class Error(val message: String) : UiState()
}