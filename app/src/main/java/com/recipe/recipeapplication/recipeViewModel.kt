package com.recipe.recipeapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.recipe.recipeapplication.database.NODE_RECIPES
import com.recipe.recipeapplication.database.recipeDatabase

class recipeViewModel: ViewModel() {

    private val _recipes = MutableLiveData<List<recipeDatabase>>()
    val recipe : LiveData<List<recipeDatabase>>
        get() = _recipes


    private val dbRecipes = FirebaseDatabase.getInstance().getReference(NODE_RECIPES)
    private val _result = MutableLiveData<Exception?>()
    val result : LiveData<Exception?>get()=_result

    fun addRecipe(recipes: recipeDatabase){
        //Adding recipes into the database
        recipes.recipeId = dbRecipes.push().key.toString() // Id of the recipes
        dbRecipes.child(recipes.recipeId!!).setValue(recipes).addOnCompleteListener {
            if(it.isSuccessful){
                _result.value = null
            }else{
                _result.value = it.exception
            }
        }
     }

    //Fetching back the data from Firebase
    fun fetchRecipe(){
        dbRecipes.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val recipess = mutableListOf<recipeDatabase>()

                    for (recipeSnapshot in snapshot.children){
                        val recipesSnap = recipeSnapshot.getValue(recipeDatabase::class.java)
                        recipesSnap?.recipeId = recipeSnapshot.key
                        Log.d("recipeIDtag", "the recipeID is " + recipeSnapshot.key)
                        recipesSnap?.let { recipess.add(it) }
                    }

                    _recipes.value = recipess
                }
            }

        })

    }


    //Updating the selected recipes
    fun updateRecipe(recipes : recipeDatabase){
        dbRecipes.child(recipes.recipeId!!).setValue(recipes).addOnCompleteListener {
            if(it.isSuccessful){
                _result.value = null
            }else{
                _result.value = it.exception
            }
        }
    }

}
