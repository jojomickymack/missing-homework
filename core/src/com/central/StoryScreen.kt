package com.central

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import ktx.actors.alpha
import ktx.actors.plusAssign


class StoryScreen : Screen, InputProcessor {
    private var uiTable = Table()

    private var background = Background()
    private var dude = Dude()
    private var continueKey = Image(AppObj.cButtonTex)
    private var buttonTable = Table()
    private var theEnd = Image(AppObj.endTex)

    var dialogBox = DialogBox()

    init {
        dialogBox.setDialogSize(AppObj.mainStage.width, AppObj.mainStage.height / 3)
        dialogBox.bgImage.color = Color(0.25f, 0.25f, 0.25f, 1f)

        buttonTable.isVisible = false

        continueKey.setSize(32f, 32f)
        continueKey.setPosition(dialogBox.width - continueKey.width, 0f)

        with(uiTable) {
            add(buttonTable)
            row()
            row().expandY()
            add(dialogBox)
            setFillParent(true)
        }

        with(theEnd) {
            setSize(AppObj.mainStage.width, AppObj.mainStage.height / 3)
            setPosition(AppObj.mainStage.width / 2 - width / 2, AppObj.mainStage.height / 2 - height / 2)
            alpha = 0f
        }

        with(AppObj) {
            mainStage.clear()
            uiStage.clear()

            mainStage += background
            mainStage += dude
            mainStage += theEnd

            uiStage += uiTable
            uiStage += continueKey
        }

        hallway()
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

    fun hallway() {
        background.currentImage = background.hallway
        background.currentImage.alpha = 0f
        background.currentImage.setPosition(0f, 0f)
        background.currentImage += fadeIn(2f)

        dude.x = -50f

        // is it weird to have the dialogbox's label be driving all of the events? Kind of, but it doesn't really matter
        dialogBox.dialogLabel += sequence(
                Actions.run {
                    dude += moveToAligned(AppObj.mainStage.width / 2, 0f, Align.bottom, 2f)
                },
                TypewriterAction("My name is Kelsoe Kismet. I am a student at Aureus Ludus Academy."),
                Actions.run {
                    dude.currentImage = AppObj.sadTex
                },
                TypewriterAction("I pooped, gotta go."),
                Actions.run {
                    dude.currentImage = AppObj.normalTex
                    delay(2f)
                    moveToAligned(AppObj.mainStage.width / 2 - dude.currentImage.width / 2, 0f, Align.bottom, 2f)
                    delay(2f)
                    classroom()
                }
        )
    }

    fun classroom() {
        background.currentImage = background.classroom
        background.currentImage.alpha = 0f
        dialogBox.setText("")
        dude += moveToAligned(0f, 0f, Align.bottomLeft, 0f)

        dude += moveToAligned(AppObj.mainStage.width / 2, 0f, Align.bottom, 2f)
        background.currentImage += fadeIn(2f)

        dialogBox.dialogLabel += sequence(
                TypewriterAction("This is my classroom. My homework isn't here, though."),
                Actions.run {
                    dude.currentImage = AppObj.sadTex
                },
                TypewriterAction("Where should I look for my homework next?"),
                Actions.run {
                    dude.currentImage = AppObj.normalTex
                    buttonTable += Actions.show()
                }
        )

        val scienceLabButton = TextButton("Look in the Science Lab", AppObj.textButtonStyle)

        scienceLabButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                buttonTable += Actions.hide()
                dialogBox.dialogLabel += sequence(
                        TypewriterAction("That's a great idea. I'll check the science lab."),
                        Actions.run {
                            background += fadeOut(1f)
                        },
                        Actions.run {
                            scienceLab()
                        }
                )
            }
        })

        val libraryButton = TextButton("Look in the Library", AppObj.textButtonStyle)

        libraryButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                buttonTable += Actions.hide()
                dialogBox.dialogLabel += sequence(
                        TypewriterAction("That's a great idea. Maybe I left it in the library."),
                        Actions.run {
                            background.addAction(Actions.fadeOut(1f))
                        },
                        Actions.run {
                            library()
                        }
                )
            }
        })

        buttonTable.clearChildren()
        buttonTable.add(scienceLabButton)
        buttonTable.row()
        buttonTable.add(libraryButton)
    }

    fun scienceLab() {
        background.currentImage = background.scienceLab
        background.currentImage.color.a = 0f
        dialogBox.setText("")
        dude += moveToAligned(0f, 0f, Align.bottomLeft, 0f)

        background.currentImage += fadeIn(2f)
        dude += moveToAligned(AppObj.mainStage.width / 2, 0f, Align.bottom, 2f)

        dialogBox.dialogLabel += sequence(
                TypewriterAction("This is the science lab."),
                TypewriterAction("My homework isn't here, though."),
                Actions.run {
                    dude.currentImage = AppObj.sadTex
                },
                Actions.run {
                    dude.currentImage = AppObj.normalTex
                },
                TypewriterAction("Now where should I go?"),
                Actions.run {
                    buttonTable += Actions.show()
                }
        )

        val classroomButton = TextButton("Return to the Classroom", AppObj.textButtonStyle)

        classroomButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                buttonTable += Actions.hide()
                dialogBox.dialogLabel += sequence(
                        TypewriterAction("Maybe someone found it and put it in the classroom. I'll go check."),
                        Actions.run {
                            background.addAction(Actions.fadeOut(1f))
                        },
                        Actions.run {
                            classroom()
                        }
                )
            }
        })

        val libraryButton = TextButton("Look in the Library", AppObj.textButtonStyle)

        libraryButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                buttonTable += Actions.hide()
                dialogBox.dialogLabel += sequence(
                        TypewriterAction("That's a great idea. Maybe I left it in the library."),
                        Actions.run {
                            background.addAction(fadeOut(1f))
                        },
                        Actions.run {
                            library()
                        }
                )
            }
        })

        buttonTable.clearChildren()
        buttonTable.add(classroomButton)
        buttonTable.row()
        buttonTable.add(libraryButton)
    }

    fun library() {
        background.currentImage = background.library
        background.currentImage.alpha = 0f
        dialogBox.setText("")
        dude += moveToAligned(0f, 0f, Align.bottomLeft, 0f)

        background.currentImage += fadeIn(2f)
        dude += moveToAligned(AppObj.mainStage.width / 2, 0f, Align.bottom, 2f)

        dialogBox.dialogLabel += sequence(
                TypewriterAction("This is the library."),
                TypewriterAction("Let me check the table where I was working earlier..."),
                Actions.run {
                    dude.currentImage = AppObj.lookRightTex
                },
                Actions.run {
                    dude += moveToAligned(AppObj.mainStage.width, 0f, Align.bottomRight, 2f)
                },
                Actions.run {
                    dude.currentImage = AppObj.normalTex
                },
                TypewriterAction("Aha! Here it is!"),
                Actions.run {
                    dude += moveToAligned(AppObj.mainStage.width / 2, 0f, Align.bottom, 0.5f)
                },
                TypewriterAction("Thanks for helping me find it!"),
                Actions.run {
                    uiTable += fadeOut(2f)
                    continueKey += fadeOut(2f)
                    theEnd += fadeIn(2f)
                },
                delay(5f),
                Actions.run {
                    AppObj.game.screen = MenuScreen()
                }
        )
    }

    override fun keyDown(keyCode: Int): Boolean {
        if (dialogBox.dialogLabel.actions.size > 0) {
            val seqAction = dialogBox.dialogLabel.actions.first() as SequenceAction
            val actions = seqAction.actions
            for (i in 0 until actions.size) {
                if (actions[i] is TypewriterAction) {
                    val myAct = actions[i] as TypewriterAction
                    if (myAct.completed) {
                        seqAction.actions.removeIndex(i)
                        break
                    } else actions[i].act(100f)
                }
            }
        }

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
        if (dialogBox.dialogLabel.actions.size > 0) {
            val seqAction = dialogBox.dialogLabel.actions.first() as SequenceAction
            val actions = seqAction.actions
            for (i in 0 until actions.size) {
                if (actions[i] is TypewriterAction) {
                    val myAct = actions[i] as TypewriterAction
                    if (myAct.completed) {
                        seqAction.actions.removeIndex(i)
                        break
                    } else actions[i].act(100f)
                }
            }
        }
        return false
    }
}