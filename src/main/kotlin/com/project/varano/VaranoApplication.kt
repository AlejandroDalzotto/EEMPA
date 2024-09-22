package com.project.varano

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VaranoApplication

fun main(args: Array<String>) {
	runApplication<VaranoApplication>(*args)
}
