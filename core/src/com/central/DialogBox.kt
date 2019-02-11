package com.central

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import ktx.actors.plusAssign

class DialogBox : Table() {
    val dialogLabel = Label("hi, this is my thing ", AppObj.labelStyle)
    private val padding = 16f

    private var animationPaused = false

    var bgImage = Image(Texture("dialog-translucent.png"))

    init {
        bgImage.setSize(AppObj.mainStage.width, AppObj.mainStage.height / 3)
        dialogLabel.setWrap(true)
        dialogLabel.setAlignment(Align.topLeft)
        dialogLabel.setPosition(padding, padding)
        this.setDialogSize(width, height)
        this += dialogLabel
    }

    override fun act(delta: Float) {
        if (!animationPaused) {
            bgImage.act(delta)
            dialogLabel.act(delta)
        }
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        bgImage.draw(batch, parentAlpha)
        dialogLabel.draw(batch, parentAlpha)
    }

    fun setDialogSize(width: Float, height: Float) {
        this.setSize(width, height)
        dialogLabel.width = width - 2 * padding
        dialogLabel.height = height - 2 * padding
    }

    fun setText(text: String) {
        dialogLabel.setText(text)
    }

    fun setBackgroundColor(color: Color) {
        this.color = color
    }
}