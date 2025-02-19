package com.ferhatozcelik.jetpackcomposetemplate.ui.create

class MissingFieldException(field: String) :
    Exception("Le champ \"$field\" est nécessaire!")
