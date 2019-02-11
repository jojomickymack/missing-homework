package com.central

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Dude : Actor() {
    var normal = Texture("kelsoe-normal.png")
    var sad = Texture("kelsoe-sad.png")
    var lookLeft = Texture("kelsoe-look-left.png")
    var lookRight = Texture("kelsoe-look-right.png")

    var currentImage = normal

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
        batch.draw(this.currentImage, this.x, this.y, AppObj.mainStage.width / 2, AppObj.mainStage.height)
    }
}