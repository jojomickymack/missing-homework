package com.central

import com.badlogic.gdx.Game

class App : Game() {
    override fun create() {
        AppObj.game = this
        AppObj.game.screen = MenuScreen()
    }

    override fun dispose() {
        AppObj.dispose()
        println("all disposable memory freed")
        super.dispose()
    }
}