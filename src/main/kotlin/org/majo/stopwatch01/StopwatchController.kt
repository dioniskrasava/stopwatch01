package org.majo.stopwatch01

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.util.Duration

class StopwatchController {
    @FXML
    private lateinit var timeLabel: Label

    private var secondsElapsed = 0
    private lateinit var timeline: Timeline

    @FXML
    fun initialize() {
        timeline = Timeline(
            KeyFrame(
                Duration.seconds(1.0),
                {
                    secondsElapsed++
                    updateTimeLabel()
                }
            )
        ).apply {
            cycleCount = Timeline.INDEFINITE
        }
    }

    @FXML
    private fun onPlayButtonClick() {
        timeline.play()
    }

    @FXML
    private fun onPauseButtonClick() {
        timeline.pause()
    }

    @FXML
    private fun onResetButtonClick() {
        timeline.stop()
        secondsElapsed = 0
        updateTimeLabel()
    }

    private fun updateTimeLabel() {
        val hours = secondsElapsed / 3600
        val minutes = (secondsElapsed % 3600) / 60
        val seconds = secondsElapsed % 60

        timeLabel.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}