package com.central

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Background : Actor() {
    var hallway = Image(Texture("bg-hallway.jpg"))
    var classroom = Image(Texture("bg-classroom.jpg"))
    var scienceLab = Image(Texture("bg-science-lab.jpg"))
    var library = Image(Texture("bg-library.jpg"))

    var currentImage = Image(Texture("bg-science-lab.jpg"))

    private var animationPaused = false

    init {
        hallway.setSize(AppObj.mainStage.width, AppObj.mainStage.height)
        classroom.setSize(AppObj.mainStage.width, AppObj.mainStage.height)
        scienceLab.setSize(AppObj.mainStage.width, AppObj.mainStage.height)
        library.setSize(AppObj.mainStage.width, AppObj.mainStage.height)
    }

    override fun act(delta: Float) {
        if (!animationPaused) currentImage.act(delta)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        currentImage.draw(batch, parentAlpha)
    }
}