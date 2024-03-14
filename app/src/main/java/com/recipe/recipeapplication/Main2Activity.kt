package com.recipe.recipeapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.recipe.recipeapplication.fragments.AddRecipes
import com.recipe.recipeapplication.fragments.EditPostedRecipes
import com.recipe.recipeapplication.fragments.ViewAllRecipes
import recipe.recipeapplication.R

class Main2Activity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val addRecipes = AddRecipes()
    private val editPostedRecipes = EditPostedRecipes()
    private val viewAllRecipes = ViewAllRecipes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_main)

        val addBtn = findViewById<Button>(R.id.addRecipes)
        val viewBtn = findViewById<Button>(R.id.viewRecipes)
        val editBtn = findViewById<Button>(R.id.editRecipes)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, viewAllRecipes)
        fragmentTransaction.commit()

        viewBtn.setOnClickListener() {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.myFragment, viewAllRecipes)
            fragmentTransaction.commit()
        } //main fragment to view all the recipes

        addBtn.setOnClickListener() {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.myFragment, addRecipes)
            fragmentTransaction.commit()
        } // to add recipes
        editBtn.setOnClickListener() {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.myFragment, editPostedRecipes)
            fragmentTransaction.commit()
        } // Listed recipes where you can go edit them

    }


}
