package com.central

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align

class DialogBox : Table() {
    val dialogLabel = Label("hi, this is my thing ", MyGameObj.labelStyle)
    private val padding = 16f

    private var animationPaused = false

    var bgImage = Image(Texture("assets/dialog-translucent.png"))

    init {
        dialogLabel.setWrap(true)
        dialogLabel.setAlignment(Align.topLeft)
        dialogLabel.setPosition(padding, padding)
        this.setDialogSize(width, height)
        this.addActor(dialogLabel)
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