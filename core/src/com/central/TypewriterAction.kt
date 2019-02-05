package com.central

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table

class TypewriterAction(t: String) : Action() {
    private var elapsedTime: Float = 0.toFloat()
    private val charactersPerSecond: Float
    private var textToDisplay = t
    var completed = false

    init {
        elapsedTime = 0f
        charactersPerSecond = 30f
    }

    override fun act(delta: Float): Boolean {
        elapsedTime += delta
        var numberOfCharacters = (elapsedTime * charactersPerSecond).toInt()
        if (numberOfCharacters > textToDisplay.length)
            numberOfCharacters = textToDisplay.length
        val partialText = textToDisplay.substring(0, numberOfCharacters)
        val db = target as Label
        db.setText(partialText)
        this.completed = numberOfCharacters >= textToDisplay.length
        return false
    }
}