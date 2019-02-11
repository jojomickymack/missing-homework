package com.central

import com.badlogic.gdx.Game

class App : Game() {
    override fun create() {
        AppObj.game = this
        AppObj.game.screen = MenuScreen()
    }
}