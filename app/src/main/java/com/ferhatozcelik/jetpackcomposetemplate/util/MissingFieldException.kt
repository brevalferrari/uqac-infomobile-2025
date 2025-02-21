package com.ferhatozcelik.jetpackcomposetemplate.util

class MissingFieldException(field: String) :
    Exception("Le champ \"$field\" est nécessaire!")
