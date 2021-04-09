package com.harrisonbacordo.flatmate.network

sealed class FlatmateNetworkResult

object FlatmateSuccessNetworkResult: FlatmateNetworkResult()
data class FlatmateErrorNetworkResult(private val errorMessage: String): FlatmateNetworkResult()