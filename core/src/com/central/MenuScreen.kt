package com.central

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class MenuScreen : Screen, InputProcessor {
    protected var uiTable = Table()

    init {
        MyGameObj.mainStage.clear()
        MyGameObj.uiStage.clear()

        val notebook = Image(Texture("assets/notebook.jpg"))
        notebook.setPosition(0f, 0f)
        notebook.setSize(800f, 600f)
        MyGameObj.mainStage.addActor(notebook)

        val title = Image(Texture("assets/missing-homework.png"))
        title.setPosition(0f, 0f)
        MyGameObj.mainStage.addActor(title)

        val startButton = TextButton("Start", MyGameObj.textButtonStyle)

        startButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                MyGameObj.game.screen = StoryScreen()
            }
        })

        val quitButton = TextButton("Quit", MyGameObj.textButtonStyle)

        quitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.app.exit()
            }
        })

        uiTable.add(title).colspan(2)
        uiTable.row()
        uiTable.add(startButton)
        uiTable.add(quitButton)

        uiTable.setFillParent(true)
        MyGameObj.uiStage.addActor(uiTable)
    }

    override fun render(delta: Float) {
        // act methods
        MyGameObj.uiStage.act(delta)
        MyGameObj.mainStage.act(delta)

        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // draw the graphics
        MyGameObj.mainStage.draw()
        MyGameObj.uiStage.draw()
    }

    override fun keyDown(keyCode: Int): Boolean {
        if (Gdx.input.isKeyPressed(Keys.ENTER))
            MyGameObj.game.screen = StoryScreen()

        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit()

        return false
    }

    override fun show() {
        val im = Gdx.input.inputProcessor as InputMultiplexer
        im.addProcessor(this)
        im.addProcessor(MyGameObj.uiStage)
        im.addProcessor(MyGameObj.mainStage)
    }

    override fun hide() {
        val im = Gdx.input.inputProcessor as InputMultiplexer
        im.removeProcessor(this)
        im.removeProcessor(MyGameObj.uiStage)
        im.removeProcessor(MyGameObj.mainStage)
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