package com.example.gallery

class GalleryItem() {
    private var id: Int = 0
    private var description: String = ""
    private var rating: Float = 4f
    constructor(imageNumber: Int) : this() {
        when (imageNumber) {
            0 -> {
                id = R.drawable.item_mona_lisa
                description = "Mona Lisa"
            }
            1 -> {
                id = R.drawable.item_lady_with_an_ermine
                description = "Dama z gronostajem"
            }
            2 -> {
                id = R.drawable.item_vitruvian_man
                description = "Człowiek witruwiański"
            }
            3 -> {
                id = R.drawable.item_last_supper
                description = "Ostatnia wieczerza"
            }
            4 -> {
                id = R.drawable.item_salvator_mundi
                description = "Salvator Mundi"
            }
            5 -> {
                id = R.drawable.item_ginevra_de_benci
                description = "Ginevra de' Benci"
            }
            6 -> {
                id = R.drawable.item_virgin_of_the_rocks
                description = "Madonna w grocie"
            }
        }
    }
    constructor(id: Int, description: String, rating: Float) : this() {
        this.id = id
        this.description = description
        this.rating = rating
    }
    companion object {
        fun getSize(): Int {
            return 7
        }
    }
    fun getId(): Int {
        return id
    }
    fun getDescription(): String {
        return description
    }
    fun getRating(): Float {
        return rating
    }
    fun setRating(newRating: Float) {
        rating = newRating
    }
}