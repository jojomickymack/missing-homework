package com.central

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable
import com.badlogic.gdx.utils.viewport.FitViewport

object MyGameObj {

    lateinit var game: Game

    val labelStyle = LabelStyle()
    val textButtonStyle = TextButtonStyle()
    val im = InputMultiplexer()

    val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("assets/OpenSans.ttf"))
    val fontParameters = FreeTypeFontGenerator.FreeTypeFontParameter()
    val customFont = fontGenerator.generateFont(fontParameters)

    val buttonTex = Texture(Gdx.files.internal("assets/button.png"))
    val buttonPatch = NinePatch(buttonTex, 24, 24, 24, 24)

    val width = Gdx.graphics.width.toFloat()
    val height = Gdx.graphics.height.toFloat()

    val sb = SpriteBatch()
    val cam = OrthographicCamera(width, height)
    val hud = OrthographicCamera(width, height)
    val view = FitViewport(width, height, cam)
    val hudView = FitViewport(width, height, hud)
    val mainStage = Stage(view, sb)
    val uiStage = Stage(hudView, sb)

    init {
        Gdx.input.inputProcessor = im
        fontParameters.size = 24
        fontParameters.color = Color.WHITE
        fontParameters.borderWidth = 2f
        fontParameters.borderColor = Color.BLACK
        fontParameters.borderStraight = true
        fontParameters.minFilter = TextureFilter.Linear
        fontParameters.magFilter = TextureFilter.Linear

        labelStyle.font = customFont

        textButtonStyle.up = NinePatchDrawable(buttonPatch)
        textButtonStyle.font = customFont
        textButtonStyle.fontColor = Color.GRAY
    }
}