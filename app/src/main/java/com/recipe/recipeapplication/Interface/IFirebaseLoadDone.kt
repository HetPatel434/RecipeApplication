package com.recipe.recipeapplication.Interface

import com.recipe.recipeapplication.database.recipeDatabase

interface IFirebaseLoadDone {
    fun onFirebaseLoadSuccess(recipeList: List<recipeDatabase>)
    fun onFirebaseLoadFailure (message: String)
}