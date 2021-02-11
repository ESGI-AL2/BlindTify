package com.mvestro.blindtify.Model.Game

import java.io.Serializable

object Game : Serializable {
    var uri: String = ""
    var P1Name: String = "P1"
    var P2Name: String = "P2"
    var P3Name: String = "P3"
    var P4Name: String = "P4"
    var P1pts: Int = 0
    var P2pts: Int = 0
    var P3pts: Int = 0
    var P4pts: Int = 0
}