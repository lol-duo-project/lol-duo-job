package com.lolduo.lolduojob.config.secret

interface AwsSecret {
    fun getSecret(secretName: String): Any
}