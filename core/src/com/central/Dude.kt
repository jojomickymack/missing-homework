package com.central

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Dude : Actor() {
    var normal = Texture("assets/kelsoe-normal.png")
    var sad = Texture("assets/kelsoe-sad.png")
    var lookLeft = Texture("assets/kelsoe-look-left.png")
    var lookRight = Texture("assets/kelsoe-look-right.png")

    var currentImage = Texture("assets/kelsoe-normal.png")

    private var elapsedTime = 0f
    private var animationPaused = false

    init {

    }

    override fun act(delta: Float) {
        super.act(delta)
        if (!animationPaused)
            elapsedTime += delta
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.setColor(1f, 1f, 1f, this.color.a)
        batch.draw(this.currentImage, this.x, this.y)
    }
}