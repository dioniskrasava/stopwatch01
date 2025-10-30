package org.majo.stopwatch01

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class StopwatchApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(StopwatchApplication::class.java.getResource("stopwatch-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 250.0, 120.0)
        stage.title = "Stopwatch"
        stage.scene = scene
        stage.minWidth = 250.0
        stage.minHeight = 120.0
        stage.show()
    }
}