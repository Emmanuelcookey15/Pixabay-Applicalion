package com.payback.domain.model

import org.junit.Assert.*
import org.junit.Test

class HitsTest{


    @Test
    fun`test that Hits Model field User EdgeCase is Not Empty`() {
        val hit = Hits(id = 12, user = "spiderman",
            image = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
            imageLarge = "A lot of Data", like = 9, downloads = 8, comments = 9, tags = "fruit")
        assertEquals("Spiderman", hit.getCapitalizeUser())
    }


    @Test
    fun `test that Hits Model User field  EdgeCase is Empty`() {
        val hit = Hits(id = 12, user = "",
            image = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
            imageLarge = "A lot of Data", like = 9, downloads = 8, comments = 9, tags = "fruit")
        assertEquals("", hit.getCapitalizeUser())
    }


    @Test
    fun `test that Hits Model User field  EdgeCase is Null`() {
        val hit = Hits(id = 12, user = null,
            image = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
            imageLarge = "A lot of Data", like = 9, downloads = 8, comments = 9, tags = "fruit")
        assertEquals("No Username", hit.getCapitalizeUser())
    }

}