package com.example.chatapplication

class User {
    var name: String? = null
    var email: String? = null
    var id:String? = null

    constructor()
    constructor(name: String?, email: String?, id: String?) {
        this.name = name
        this.email = email
        this.id = id
    }


}