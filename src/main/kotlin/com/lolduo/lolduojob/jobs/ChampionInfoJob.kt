package com.lolduo.lolduojob.jobs

import com.google.gson.Gson
import com.lolduo.lolduojob.module.ChampionInfo
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class ChampionInfoJob(
    private val mongoTemplate: MongoTemplate
) {

    fun run() {
        val versionUrl = "http://localhost:8080/version"
        val localesUrl = "http://localhost:8080/locales"
        val versionList = getResponse<Array<String>>(versionUrl)
        val localeList = getResponse<Array<String>>(localesUrl)

        for(locale in localeList) {
            val championUrl = "http://localhost:8080/champions?version=${versionList[0]}&locale=$locale"
            try {
                val championList = getResponse<Array<ChampionInfo>>(championUrl)
                for(champion in championList) {
                    champion.locale = locale
                    champion.uniqueId = "${champion.id}_${champion.locale}"
                    mongoTemplate.save(champion, versionList[0])
                }
            } catch (e: Exception) {
                println("Error response from Riot API$championUrl")
            }
        }
    }

    private inline fun <reified T> getResponse(url: String): T {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        try{
            val response = okHttpClient.newCall(request).execute()
            return Gson().fromJson(response.body?.string(), T::class.java)
        } catch (e: Exception) {
            throw Exception("Error response from Riot API")
        }
    }
}