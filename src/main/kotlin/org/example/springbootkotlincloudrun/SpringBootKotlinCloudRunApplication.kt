package org.example.springbootkotlincloudrun

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinCloudRunApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinCloudRunApplication>(*args)
}
