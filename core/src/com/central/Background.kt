package com.central

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Background : Actor() {
    var hallway = Image(Texture("assets/bg-hallway.jpg"))
    var classroom = Image(Texture("assets/bg-classroom.jpg"))
    var scienceLab = Image(Texture("assets/bg-science-lab.jpg"))
    var library = Image(Texture("assets/bg-library.jpg"))

    var currentImage = Image(Texture("assets/bg-science-lab.jpg"))

    private var animationPaused = false

    init {
        setSize(800f, 600f)
    }

    override fun act(delta: Float) {
        if (!animationPaused) this.currentImage.act(delta)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        this.currentImage.draw(batch, parentAlpha)
    }
}