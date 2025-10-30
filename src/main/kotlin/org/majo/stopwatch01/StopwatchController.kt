package org.majo.stopwatch01

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.util.Duration

class StopwatchController {
    @FXML
    private lateinit var timeLabel: Label
    @FXML
    private lateinit var logArea: TextArea
    @FXML
    private lateinit var mainBorderPane: BorderPane

    private var secondsElapsed = 0
    private var lapCount = 0
    private lateinit var timeline: Timeline
    private var isDarkTheme = false
    private var showLogs = false

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

        // Инициализируем лог область
        logArea.isVisible = false
        logArea.isManaged = false
    }

    @FXML
    private fun onPlayButtonClick() {
        timeline.play()
        addLog("Секундомер запущен")
    }

    @FXML
    private fun onPauseButtonClick() {
        timeline.pause()
        addLog("Секундомер приостановлен")
    }

    @FXML
    private fun onResetButtonClick() {
        timeline.stop()
        secondsElapsed = 0
        lapCount = 0
        updateTimeLabel()
        logArea.clear()
        addLog("Секундомер сброшен")
    }

    @FXML
    private fun onLapButtonClick() {
        if (timeline.status == javafx.animation.Animation.Status.RUNNING) {
            lapCount++
            val lapTime = formatTime(secondsElapsed)
            addLog("Круг $lapCount: $lapTime")
        }
    }

    @FXML
    private fun onToggleTheme() {
        isDarkTheme = !isDarkTheme

        if (isDarkTheme) {
            // Темная тема
            mainBorderPane.style = """
                -fx-background-color: #2b2b2b;
            """.trimIndent()
            timeLabel.style = """
                -fx-font-size: 32px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
            """.trimIndent()
            addLog("Включена темная тема")
        } else {
            // Светлая тема
            mainBorderPane.style = """
                -fx-background-color: white;
            """.trimIndent()
            timeLabel.style = """
                -fx-font-size: 32px;
                -fx-font-weight: bold;
                -fx-text-fill: black;
            """.trimIndent()
            addLog("Включена светлая тема")
        }
    }

    @FXML
    private fun onToggleLogs() {
        showLogs = !showLogs
        logArea.isVisible = showLogs
        logArea.isManaged = showLogs
        addLog("Логи ${if (showLogs) "включены" else "выключены"}")
    }

    @FXML
    private fun onToggleNotes() {
        addLog("Функция заметок будет реализована в будущем")
    }

    private fun updateTimeLabel() {
        timeLabel.text = formatTime(secondsElapsed)
    }

    private fun formatTime(totalSeconds: Int): String {
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    private fun addLog(message: String) {
        val timestamp = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"))
        logArea.appendText("[$timestamp] $message\n")

        // Прокручиваем к нижней части
        logArea.positionCaret(logArea.length)
    }
}