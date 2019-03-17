package com.central

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import ktx.actors.plusAssign

class MenuScreen : Screen, InputProcessor {
    private var uiTable = Table()

    init {
        val notebook = Image(AppObj.notebookTex)
        notebook.setPosition(0f, 0f)
        notebook.setSize(800f, 600f)

        val title = Image(AppObj.titleTex)
        title.setPosition(0f, 0f)

        val startButton = TextButton("Start", AppObj.textButtonStyle)

        startButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                AppObj.game.screen = StoryScreen()
            }
        })

        val quitButton = TextButton("Quit", AppObj.textButtonStyle)

        quitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.app.exit()
            }
        })

        with(uiTable) {
            add(title).colspan(2)
            row()
            add(startButton)
            add(quitButton)
            setFillParent(true)
        }

        with(AppObj) {
            mainStage.clear()
            uiStage.clear()

            mainStage += notebook
            uiStage += uiTable
        }
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        with(AppObj) {
            uiStage.act(delta)
            mainStage.act(delta)

            mainStage.draw()
            uiStage.draw()
        }
    }

    override fun keyDown(keyCode: Int): Boolean {
        if (Gdx.input.isKeyPressed(Keys.ENTER))
            AppObj.game.screen = StoryScreen()

        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit()

        return false
    }

    override fun show() {
        val im = Gdx.input.inputProcessor as InputMultiplexer
        im.addProcessor(this)
        im.addProcessor(AppObj.uiStage)
        im.addProcessor(AppObj.mainStage)
    }

    override fun hide() {
        val im = Gdx.input.inputProcessor as InputMultiplexer
        im.removeProcessor(this)
        im.removeProcessor(AppObj.uiStage)
        im.removeProcessor(AppObj.mainStage)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }
}