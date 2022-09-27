package com.example.chatapplication

class message {
    var mssg: String? = null
    var senderId: String? = null

    constructor()

    constructor(mssg: String?, senderId: String?) {
        this.mssg = mssg
        this.senderId = senderId
    }


}