package org.girsang.pos

import javafx.application.Application
import org.girsang.pos.controller.MainController
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["org.girsang.pos"])
open class MainApplication

fun main(args: Array<String>) {
    Application.launch(MainController::class.java, *args)
}