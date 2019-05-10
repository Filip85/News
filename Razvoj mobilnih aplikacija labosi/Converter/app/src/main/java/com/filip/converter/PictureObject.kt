package com.filip.converter

object PictureObject {
    val pictures: MutableList<Picture> = createPictures()

    private fun createPictures(): MutableList<Picture> {
        return mutableListOf(
                Picture(1, "dsddsds", R.drawable.weightpicture),
                Picture(2, "gtgtgt", R.drawable.temperature),
                Picture(3, "fsdfsdf", R.drawable.distance),
                Picture(4, "orotoptrop", R.drawable.time)
        )
    }

    fun get(id: Int){
        pictures.find { picture -> picture.id == id }
    }
}