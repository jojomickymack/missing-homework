package com.central

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color.*
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
import com.badlogic.gdx.utils.viewport.StretchViewport


object AppObj {

    lateinit var game: Game

    val labelStyle = LabelStyle()
    val textButtonStyle = TextButtonStyle()
    val im = InputMultiplexer()

    val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"))
    val fontParameters = FreeTypeFontGenerator.FreeTypeFontParameter()
    val customFont = fontGenerator.generateFont(fontParameters)

    val buttonTex = Texture(Gdx.files.internal("button.png"))
    val buttonPatch = NinePatch(buttonTex, 24, 24, 24, 24)

    var width = Gdx.graphics.height.toFloat()
    var height = Gdx.graphics.width.toFloat()

    val sb = SpriteBatch()
    val cam = OrthographicCamera(width, height)
    val hud = OrthographicCamera(width, height)
    val view = StretchViewport(480f, 360f, cam)
    val hudView = StretchViewport(480f, 360f, hud)
    val mainStage = Stage(view, sb)
    val uiStage = Stage(hudView, sb)

    /**
     * all this right here where textures are loaded and disposed one by one is why AssetManager is provided
     * it would achieve the same thing and require one call to dispose() on the AssetManager itself
     */
    // Background textures
    val hallwayTex = Texture("bg-hallway.jpg")
    val classroomTex = Texture("bg-classroom.jpg")
    val scienceLabTex = Texture("bg-science-lab.jpg")
    val libraryTex = Texture("bg-library.jpg")

    // Dialog Box Textures
    val boxTex = Texture("dialog-translucent.png")
    val cButtonTex = Texture("key-C.png")
    val endTex = Texture("the-end.png")

    // Dude textures
    val normalTex = Texture("kelsoe-normal.png")
    val sadTex = Texture("kelsoe-sad.png")
    val lookLeftTex = Texture("kelsoe-look-left.png")
    val lookRightTex = Texture("kelsoe-look-right.png")

    // Menu textures
    val notebookTex = Texture("notebook.jpg")
    val titleTex = Texture("missing-homework.png")


    init {
        Gdx.input.inputProcessor = im

        with(fontParameters) {
            size = 24
            color = WHITE
            borderWidth = 2f
            borderColor = BLACK
            borderStraight = true
            minFilter = TextureFilter.Linear
            magFilter = TextureFilter.Linear
        }

        labelStyle.font = customFont

        with(textButtonStyle) {
            up = NinePatchDrawable(buttonPatch)
            font = customFont
            fontColor = GRAY
        }
    }

    fun dispose() {
        this.fontGenerator.dispose()
        this.customFont.dispose()

        this.buttonTex.dispose()

        this.sb.dispose()
        this.mainStage.dispose()
        this.uiStage.dispose()

        this.hallwayTex.dispose()
        this.classroomTex.dispose()
        this.scienceLabTex.dispose()

        this.boxTex.dispose()
        this.cButtonTex.dispose()
        this.endTex.dispose()

        this.normalTex.dispose()
        this.sadTex.dispose()
        this.lookLeftTex.dispose()
        this.lookRightTex.dispose()

        this.notebookTex.dispose()
        this.titleTex.dispose()
    }
}