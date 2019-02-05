package com.central

import com.badlogic.gdx.Game

class MyGame : Game() {
    override fun create() {
        MyGameObj.game = this
        MyGameObj.game.screen = MenuScreen()
    }
}