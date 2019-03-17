package com.central

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Background : Actor() {
    var hallway = Image(AppObj.hallwayTex)
    var classroom = Image(AppObj.classroomTex)
    var scienceLab = Image(AppObj.scienceLabTex)
    var library = Image(AppObj.libraryTex)

    var currentImage = Image(AppObj.hallwayTex)

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