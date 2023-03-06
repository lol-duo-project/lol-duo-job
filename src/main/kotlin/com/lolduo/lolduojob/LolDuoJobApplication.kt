package com.lolduo.lolduojob

import com.lolduo.lolduojob.jobs.ChampionInfoJob
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LolDuoJobApplication

fun main(args: Array<String>) {

    val context = runApplication<LolDuoJobApplication>(*args)
    val championInfoJob = context.getBean(ChampionInfoJob::class.java)
    championInfoJob.run()
}
